# SQL Interview Notes

Quick reference: syntax, joins, NULLs, aggregates, windows, classic queries, and performance.

---

## 1. Language groups

| Group | Purpose | Examples |
|---|---|---|
| **DDL** | Structure | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` |
| **DML** | Data | `SELECT`, `INSERT`, `UPDATE`, `DELETE` |
| **TCL** | Transactions | `COMMIT`, `ROLLBACK`, `SAVEPOINT` |
| **DCL** | Permissions | `GRANT`, `REVOKE` |

Some interviews call `SELECT` **DQL**. Either is fine.

---

## 2. Keys and constraints

| Constraint | Meaning |
|---|---|
| **PRIMARY KEY** | Unique + NOT NULL; one per table |
| **UNIQUE** | Unique; usually allows one NULL |
| **FOREIGN KEY** | Must match PK/UNIQUE in parent |
| **NOT NULL / CHECK / DEFAULT** | Column rules |

---

## 3. DELETE vs TRUNCATE vs DROP

| | DELETE | TRUNCATE | DROP |
|---|---|---|---|
| Removes | Rows (optional `WHERE`) | All rows | Table itself |
| Rollback | Yes (in a transaction) | Often no / limited | No |
| Identity reset | No | Usually yes | N/A |
| Triggers | Fire | Usually do not | N/A |

---

## 4. SELECT skeleton

```sql
SELECT columns | DISTINCT | aggregates
FROM table
JOIN ...
WHERE   -- row filter (before group)
GROUP BY
HAVING  -- group filter (after group)
ORDER BY
LIMIT / OFFSET   -- or FETCH / TOP (SQL Server)
```

**WHERE vs HAVING:** `WHERE` filters rows; `HAVING` filters groups.

**UNION vs UNION ALL:** `UNION` deduplicates; `UNION ALL` keeps duplicates (faster).

**CHAR vs VARCHAR:** fixed vs variable length.

---

## 5. Joins

```
INNER  — matching rows only
LEFT   — all left + matching right (NULL if no match)
RIGHT  — all right + matching left
FULL   — all from both
CROSS  — Cartesian product
SELF   — table joined to itself
```

### Trap

`LEFT JOIN` + `WHERE right.col = 'x'` becomes an **inner join**. Put that predicate in `ON`, or keep `WHERE right.col IS NULL` for “no match”.

### Find rows with no child (anti-join)

```sql
SELECT e.id, e.age
FROM Employee e
LEFT JOIN EmployeeFamily f ON e.id = f.empid
WHERE f.empid IS NULL;
```

Same idea with `NOT EXISTS` (often preferred):

```sql
SELECT e.id, e.age
FROM Employee e
WHERE NOT EXISTS (
  SELECT 1 FROM EmployeeFamily f WHERE f.empid = e.id
);
```

### Self join (employee + manager)

```sql
SELECT e.name AS employee, m.name AS manager
FROM Employee e
LEFT JOIN Employee m ON e.manager_id = m.id;
```

---

## 6. NULL

- `NULL = NULL` is **unknown**, not true → use `IS NULL` / `IS NOT NULL`
- `COUNT(col)` skips NULLs; `COUNT(*)` counts rows
- `NOT IN (subquery)` if the subquery has **NULL** can return **zero rows** → use `NOT EXISTS`
- `COALESCE(col, 0)` / `IFNULL` / `NVL` for defaults

---

## 7. Aggregation

```sql
SELECT dept_id, COUNT(*) AS cnt, AVG(salary) AS avg_sal
FROM Employee
GROUP BY dept_id
HAVING COUNT(*) > 5;
```

Selected non-aggregated columns **must** appear in `GROUP BY`.

---

## 8. Subquery, EXISTS, CTE

```sql
-- IN / scalar
SELECT * FROM Employee
WHERE dept_id IN (SELECT id FROM Department WHERE loc = 'Pune');

-- EXISTS (correlated; stop at first match)
SELECT e.* FROM Employee e
WHERE EXISTS (
  SELECT 1 FROM EmployeeFamily f
  WHERE f.empid = e.id AND f.marriageStatus = 'Married'
);

WITH high_paid AS (
  SELECT * FROM Employee WHERE salary > 100000
)
SELECT * FROM high_paid;
```

Prefer **EXISTS** over **IN** when the list is large or may contain NULL.

---

## 9. Window functions

```sql
ROW_NUMBER()  -- 1,2,3 even if ties
RANK()        -- 1,1,3
DENSE_RANK()  -- 1,1,2

LAG(col) / LEAD(col)
SUM(col) OVER (PARTITION BY dept ORDER BY date)
```

`PARTITION BY` restarts per group. `ORDER BY` inside `OVER` is window order, not the result `ORDER BY`.

```sql
SELECT id, salary,
       ROW_NUMBER() OVER (PARTITION BY dept_id ORDER BY salary DESC) AS rn
FROM Employee;
```

---

## 10. Classic interview queries

### 10.1 Age > 35 and unmarried

Schema: `Employee(id, age)`, `EmployeeFamily(empid, marriageStatus, SpauousNmae, fname)`

```sql
SELECT e.id, e.age, f.marriageStatus, f.SpauousNmae, f.fname
FROM Employee e
INNER JOIN EmployeeFamily f ON e.id = f.empid
WHERE e.age > 35
  AND f.marriageStatus IN ('Unmarried', 'Single', 'N');
```

If no family row means unmarried:

```sql
SELECT e.id, e.age
FROM Employee e
LEFT JOIN EmployeeFamily f ON e.id = f.empid
WHERE e.age > 35
  AND (f.empid IS NULL
       OR f.marriageStatus IN ('Unmarried', 'Single', 'N'));
```

### 10.2 Second highest salary

```sql
SELECT MAX(salary)
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);

-- or
SELECT salary FROM (
  SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
  FROM Employee
) t WHERE rnk = 2;
```

### 10.3 Highest salary per department

```sql
SELECT * FROM (
  SELECT e.*,
         ROW_NUMBER() OVER (PARTITION BY dept_id ORDER BY salary DESC) AS rn
  FROM Employee e
) t WHERE rn = 1;
```

Use `RANK`/`DENSE_RANK` if ties should all appear.

### 10.4 Duplicates

```sql
SELECT email, COUNT(*)
FROM Employee
GROUP BY email
HAVING COUNT(*) > 1;
```

Keep one row, delete extra:

```sql
WITH d AS (
  SELECT id,
         ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) AS rn
  FROM Employee
)
DELETE FROM Employee
WHERE id IN (SELECT id FROM d WHERE rn > 1);
```

(Syntax varies by DB; some use `DELETE FROM d WHERE rn > 1`.)

### 10.5 Nth highest salary

```sql
SELECT salary FROM (
  SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
  FROM Employee
) t WHERE rnk = :n;
```

### 10.6 Employees who earn more than their manager

```sql
SELECT e.name, e.salary, m.name AS manager, m.salary AS mgr_sal
FROM Employee e
JOIN Employee m ON e.manager_id = m.id
WHERE e.salary > m.salary;
```

### 10.7 Running total

```sql
SELECT order_date, amount,
       SUM(amount) OVER (ORDER BY order_date) AS running_total
FROM Orders;
```

---

## 11. INSERT / UPDATE / MERGE

```sql
INSERT INTO Employee (id, age) VALUES (1, 36);

UPDATE EmployeeFamily
SET marriageStatus = 'Married'
WHERE empid = 1;

-- PostgreSQL upsert
INSERT INTO Employee (id, age) VALUES (1, 36)
ON CONFLICT (id) DO UPDATE SET age = EXCLUDED.age;
```

**POST + existing row:** SQL unique constraint → duplicate key. API should map to **409 Conflict**, not a second insert. HTTP POST is not idempotent unless you design it that way.

---

## 12. Indexes and performance

- Index helps `WHERE`, `JOIN`, `ORDER BY`; extra cost on `INSERT`/`UPDATE`/`DELETE`
- **Clustered:** table stored in key order (often PK). **Non-clustered:** separate structure pointing at rows
- Avoid wrapping indexed columns: `WHERE YEAR(hire_date) = 2024` → prefer range on `hire_date`
- Leading `%` in `LIKE '%abc'` usually **cannot** use a B-tree index
- `SELECT *` pulls unused columns
- Use `EXPLAIN` / execution plan

---

## 13. Transactions (ACID)

| | Meaning |
|---|---|
| **A**tomic | All or nothing |
| **C**onsistent | Constraints remain true |
| **I**solated | Concurrent sessions don’t clobber each other |
| **D**urable | After commit, data survives crash |

Isolation (weaker → stronger): Read Uncommitted → Read Committed → Repeatable Read → Serializable.

Problems: dirty read, non-repeatable read, phantom read. Deadlock: two sessions wait on each other’s locks.

```sql
BEGIN;
UPDATE Account SET bal = bal - 100 WHERE id = 1;
UPDATE Account SET bal = bal + 100 WHERE id = 2;
COMMIT;  -- or ROLLBACK
```

---

## 14. Normalization (short)

| Form | Rule of thumb |
|---|---|
| 1NF | Atomic cells, no repeating groups |
| 2NF | 1NF + no partial dependency on part of a composite key |
| 3NF | 2NF + no transitive dependency (non-key → non-key) |

Denormalize on purpose for read-heavy reporting.

---

## 15. Views vs procedures

| | View | Stored procedure / function |
|---|---|---|
| What | Saved query | Procedural logic |
| Use | Reuse SELECT, security | Multi-step, parameters, transactions |

Materialized view: stored result, refresh needed.

OLTP = many small writes. OLAP = large analytical reads.

---

## 16. Cheat sheet

```
JOIN match          INNER JOIN
Keep left           LEFT JOIN
No child rows       LEFT JOIN ... WHERE child.id IS NULL
                      or NOT EXISTS
Filter rows         WHERE
Filter groups       HAVING
Dedup sets          UNION  (not UNION ALL)
2nd max             MAX where < max  or DENSE_RANK = 2
Top per group       ROW_NUMBER PARTITION BY ... then rn = 1
Duplicates          GROUP BY HAVING COUNT(*) > 1
NULL safe missing   IS NULL / NOT EXISTS (not NOT IN)
```

---

## 17. Practice order

1. Inner / left join + NULL filter  
2. GROUP BY + HAVING  
3. Second highest salary  
4. Top salary per department  
5. Find / delete duplicates  
6. NOT EXISTS vs NOT IN  
7. ROW_NUMBER / RANK / DENSE_RANK  
8. Self join (manager)  
9. One EXPLAIN conversation  
