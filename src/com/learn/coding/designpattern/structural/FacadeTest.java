package com.learn.coding.designpattern.structural;

// Subsystems
class DVDPlayer { void on() { System.out.println("DVD Player ON"); } }
class Projector { void on() { System.out.println("Projector ON"); } }
class SoundSystem { void on() { System.out.println("Sound System ON"); } }

// Facade
class HomeTheaterFacade {
    private DVDPlayer dvd;
    private Projector projector;
    private SoundSystem sound;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector, SoundSystem sound) {
        this.dvd = dvd;
        this.projector = projector;
        this.sound = sound;
    }

    public void watchMovie() {
        dvd.on();
        projector.on();
        sound.on();
        System.out.println("Movie started!");
    }
}

// Test
public class FacadeTest {
    public static void main(String[] args) {
        HomeTheaterFacade theater = new HomeTheaterFacade(
                new DVDPlayer(),
                new Projector(),
                new SoundSystem()
        );
        theater.watchMovie();
    }
}
