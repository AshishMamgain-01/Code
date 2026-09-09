package com.learn.coding.designpattern.structural;

// Subject
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    public ProxyImage(String filename) { this.filename = filename; }
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // load only once
        }
        realImage.display();
    }
}

// Test
public class ProxyTest {
    public static void main(String[] args) {
        Image img = new ProxyImage("photo.png");
        img.display(); // loads + displays
        img.display(); // only displays (no reload)
    }
}
