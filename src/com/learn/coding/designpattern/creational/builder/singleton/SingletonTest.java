// Singleton.java
class mySingleton {
    private static mySingleton instance;

    private mySingleton() {} // private constructor

    public static mySingleton getInstance() {
        if (instance == null) {
            instance = new mySingleton(); // lazy initialization
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Test.java
public class SingletonTest {
    public static void main(String[] args) {
        mySingleton obj1 = mySingleton.getInstance();
        mySingleton obj2 = mySingleton.getInstance();
        System.out.println(obj1 == obj2); // true
        obj1.showMessage();
    }
}
