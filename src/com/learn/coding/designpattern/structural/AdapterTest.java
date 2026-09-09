package com.learn.coding.designpattern.structural;

// Target interface
interface ComputerPort {
    void connect();
}

// Adaptee
class MemoryCard {
    public void insert() {
        System.out.println("Memory Card inserted.");
    }
}

// Adapter
class CardReader implements ComputerPort {
    private MemoryCard card;

    public CardReader(MemoryCard card) {
        this.card = card;
    }

    public void connect() {
        card.insert();
        System.out.println("Card Reader connected to Computer.");
    }
}

// Test
public class AdapterTest {
    public static void main(String[] args) {
        MemoryCard card = new MemoryCard();
        ComputerPort port = new CardReader(card);
        port.connect();
    }
}

