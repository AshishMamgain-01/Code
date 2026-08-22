package com.learn.coding.dsa.epam;

public class sda {
	
	/*
	Problem statement
	You are given ‘q’ queries, where each query can be of the following types:

	(1) 1 x -> this means to push the element ‘x’ in the queue.
	(2) 2 -> This means deleting the front element of the queue and returning it. If there’s no element in the queue, return -1.
	Your task is to implement a queue that supports these two queries.



	You must write an algorithm whose time complexity is O(1), and whose space complexity is O(1).



	Note:
	In the output, you will see the output of queries of type 2. 
	In the queries, there will be at least one type 2 query.
	You will be given two functions, ‘push’, and ‘pop’. 
	Your task is to implement the two functions.


	Example:
	Input: ‘q’ = 5
	‘queries’ = [[1 3], [2], [1 4], [2], [2]]

	Output: 3 4 -1

	Explanation: After the first query, the queue is {3}. After the second query, the queue is {}, and we returned 3. After the third query, the queue is {4}. After the fourth query, the queue is {}, and we returned 4. After the fifth query, the queue is {}, and we returned -1(since there was no element at the time of the pop statement).
	Detailed explanation ( Input/output format, Notes, Images )
	Sample Input 1:
	5
	1 3
	2
	1 4
	2
	2
	Sample Output 1 :
	3 4 -1
	Explanation Of Sample Input 1:
	Input: ‘q’ = 5
	‘queries’ = [[1 3], [2], [1 4], [2], [2]]

	Output: 3 4 -1

	Explanation: After the first query, the queue is {3}. After the second query, the queue is {}, and we returned 3. After the third query, the queue is {4}. After the fourth query, the queue is {}, and we returned 4. After the fifth query, the queue is {}, and we returned -1(since there was no element at the time of the pop statement).
	Sample Input 2:
	2
	2
	1 1
	Sample Output 2:
	-1
	Explanation Of Sample Input 2:
	Input: ‘q’ = 2
	‘queries’ = [[2], [1 1]]

	Output: -1

	Explanation: After the first query, the queue remains {}, and we returned -1. After the second query, the queue is {1}.
	Constraints:
	2 <= q <= 10^4
	1 <= Element ‘x’ in the push function <= 10^5

	Time Limit: 1-sec
	
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
