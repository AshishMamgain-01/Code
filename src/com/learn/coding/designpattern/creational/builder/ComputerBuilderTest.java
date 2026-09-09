package com.learn.coding.designpattern.creational.builder;

// Test.java
public class ComputerBuilderTest {
    public static void main(String[] args) {
        Computer gamingPC = new ComputerBuilder()
                .addCPU("Intel i9")
                .addRAM("32GB DDR5")
                .addStorage("1TB SSD")
                .addGraphicsCard("NVIDIA RTX 4090")
                .build();

        gamingPC.showConfig();

        System.out.println("######################################");


        Computer officePC = new ComputerBuilder()
                .addCPU("Intel i5")
                .addRAM("16GB DDR4")
                .addStorage("512GB SSD")
                .build();

        officePC.showConfig();
    }
}
