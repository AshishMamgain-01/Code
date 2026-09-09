package com.learn.coding.designpattern.structural;

// Component
interface FileSystem {
    void showDetails();
}

// Leaf
class File implements FileSystem {
    private String name;
    public File(String name) { this.name = name; }
    public void showDetails() { System.out.println("File: " + name); }
}

// Composite
class Folder implements FileSystem {
    private String name;
    private java.util.List<FileSystem> children = new java.util.ArrayList<>();

    public Folder(String name) { this.name = name; }

    public void add(FileSystem fs) { children.add(fs); }

    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystem fs : children) {
            fs.showDetails();
        }
    }
}

// Test
public class CompositePatternTest {
    public static void main(String[] args) {
        File f1 = new File("Resume.docx");
        File f2 = new File("Photo.png");

        Folder folder = new Folder("My Documents");
        folder.add(f1);
        folder.add(f2);

        folder.showDetails();
    }
}
