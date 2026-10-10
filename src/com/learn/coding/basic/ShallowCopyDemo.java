package com.learn.coding.basic;
class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // only copies references
    }
}

public class ShallowCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Bengaluru");
        Person p1 = new Person("Ashish", addr);

        Person p2 = (Person) p1.clone(); // shallow copy

        p2.address.city = "Mumbai"; // modifies nested object

        System.out.println("p1 city: " + p1.address.city);
        System.out.println("p2 city: " + p2.address.city);
    }
}
