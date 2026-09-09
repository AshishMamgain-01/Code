package com.learn.coding.designpattern.creational.builder;

// Shape.java
interface Shape {
    void draw();
}

// Circle.java
class Circle implements Shape {
    public void draw() { System.out.println("Drawing Circle"); }
}

// Square.java
class Square implements Shape {
    public void draw() { System.out.println("Drawing Square"); }
}

// ShapeFactory.java
class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE")) return new Circle();
        if (type.equalsIgnoreCase("SQUARE")) return new Square();
        return null;
    }
}

public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape s1 = factory.getShape("CIRCLE");
        Shape s2 = factory.getShape("SQUARE");
        s1.draw();
        s2.draw();
    }
}
