package com.learn.coding.designpattern;

import java.util.ArrayList;
import java.util.List;

interface Observer {
	void update(String news);
}

class NewsChannel implements Observer {

	private String name;

	public NewsChannel(String name) {
		this.name = name;

	}

	@Override
	public void update(String news) {
		// TODO Auto-generated method stub
		System.out.println(name + " received news :" + news);
	}

}

class NewsAgency {
	private List<Observer> observers = new ArrayList<>();
	private String news;

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void setNews(String news) {
		this.news = news;
		notifyAllObservers();
	}

	private void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update(this.news);
		}

	}

}

public class ObserverPatternExample {

	public static void main(String[] args) {
		NewsAgency agency = new NewsAgency();

		NewsChannel cnn = new NewsChannel("CNN");
		NewsChannel bbc = new NewsChannel("BBC");
		
		agency.addObserver(cnn);
		agency.addObserver(bbc);
		
		agency.setNews("Breaking News :  New Observer Pattern Implemented!");

		agency.removeObserver(cnn);
		agency.setNews("Update: CNN has been unsubscribed");

	}

}
