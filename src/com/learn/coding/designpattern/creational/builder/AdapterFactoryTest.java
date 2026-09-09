package com.learn.coding.designpattern.creational.builder;

// Product Interfaces
interface Button { void paint(); }
interface Checkbox { void paint(); }

// Concrete Products - Windows
class WindowsButton implements Button {
    public void paint() { System.out.println("Windows Button"); }
}
class WindowsCheckbox implements Checkbox {
    public void paint() { System.out.println("Windows Checkbox"); }
}

// Concrete Products - Mac
class MacButton implements Button {
    public void paint() { System.out.println("Mac Button"); }
}
class MacCheckbox implements Checkbox {
    public void paint() { System.out.println("Mac Checkbox"); }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}
class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Test
public class AdapterFactoryTest {
    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory(); // switch to MacFactory for Mac
        Button btn = factory.createButton();
        Checkbox chk = factory.createCheckbox();
        btn.paint();
        chk.paint();
    }
}

