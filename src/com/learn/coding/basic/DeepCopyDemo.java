package com.learn.coding.basic;

class PersonDeep implements Cloneable {
    String name;
    Address address;

    PersonDeep(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Deep copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        PersonDeep cloned = (PersonDeep) super.clone();
        cloned.address = new Address(this.address.city); // new Address object
        return cloned;
    }
}

public class DeepCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Bengaluru");
        PersonDeep p1 = new PersonDeep("Ashish", addr);

        PersonDeep p2 = (PersonDeep) p1.clone(); // deep copy

        p2.address.city = "Mumbai"; // modifies nested object

        System.out.println("p1 city: " + p1.address.city);
        System.out.println("p2 city: " + p2.address.city);
    }
}
