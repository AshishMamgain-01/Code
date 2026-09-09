package com.learn.coding.designpattern.behavioral;

import java.util.*;

// Observer.java
interface Observer {
    void update(String message);
}

// Concrete Observer
class User implements Observer {
    private String name;
    public User(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Subject
class Channel {
    private List<Observer> subscribers = new ArrayList<>();
    public void subscribe(Observer o) { subscribers.add(o); }
    public void unsubscribe(Observer o) { subscribers.remove(o); }
    public void notifyObservers(String msg) {
        for (Observer o : subscribers) o.update(msg);
    }
}

// Test
public class ObserverTest {
    public static void main(String[] args) {
        Channel channel = new Channel();
        Observer u1 = new User("Ashish");
        Observer u2 = new User("Rahul");

        channel.subscribe(u1);
        channel.subscribe(u2);

        channel.notifyObservers("New video uploaded!");
    }
}
