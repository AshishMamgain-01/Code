package com.learn.coding.designpattern.creational.builder;

// ComputerBuilder.java
public class ComputerBuilder {
    private Computer computer = new Computer();

    public ComputerBuilder addCPU(String cpu) {
        computer.setCPU(cpu);
        return this;
    }

    public ComputerBuilder addRAM(String ram) {
        computer.setRAM(ram);
        return this;
    }

    public ComputerBuilder addStorage(String storage) {
        computer.setStorage(storage);
        return this;
    }

    public ComputerBuilder addGraphicsCard(String gpu) {
        computer.setGraphicsCard(gpu);
        return this;
    }

    public Computer build() {
        return computer;
    }
}
