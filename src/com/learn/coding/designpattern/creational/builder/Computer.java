package com.learn.coding.designpattern.creational.builder;

// Computer.java
public class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String graphicsCard;

    // setters
    public void setCPU(String CPU) { this.CPU = CPU; }
    public void setRAM(String RAM) { this.RAM = RAM; }
    public void setStorage(String storage) { this.storage = storage; }
    public void setGraphicsCard(String graphicsCard) { this.graphicsCard = graphicsCard; }

    public void showConfig() {
        System.out.println("Computer Config:");
        System.out.println("CPU: " + CPU);
        System.out.println("RAM: " + RAM);
        System.out.println("Storage: " + storage);
        System.out.println("Graphics Card: " + graphicsCard);
    }
}

