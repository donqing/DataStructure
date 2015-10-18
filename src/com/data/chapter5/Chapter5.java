package com.data.chapter5;

import java.util.Observable;
import java.util.Observer;

public class Chapter5 {
	public static void main(String[] args) {
		String str1 = "ab";
		System.out.println(str1.hashCode());
		String str2 = "ba";
		System.out.println(str2.hashCode());
	}
	
}
class MySubject extends Observable{
	private String content;
	
	public void setContent(String content){
		this.content = content;
		this.setChanged();
		this.notifyObservers(content);
	}

	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}


}
class MyObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Recieve from : "+ o.getClass().getName()+" : "+arg);
		
	}
	
}


