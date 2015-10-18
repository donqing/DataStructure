package com.datastructure.demo;

import java.util.ArrayList;
import java.util.List;


public class Demo {
	public static void main(String[] args) {
		List<Animal> list1= new ArrayList<Animal>();
		for(int i=1;i<5;i++)
			list1.add(new Animal(i));
		List<Dog> list2 = new ArrayList<Dog>();
		for(int i=8;i<12;i++)
			list2.add(new Dog(i));
		print(list1);
		System.out.println();
		print((List)list2);
		list1 = (List)list2;
		print(list1);
		
	}
	
	public static void print(List<Animal> animal){
		for (Animal animal2 : animal) {
			System.out.print(animal2+" ");
		}
		System.out.println();
	}
	
	
}

class Animal {
	private int age;
	public Animal(int age){
		this.age = age;
	}
	public String toString(){
		return age+"";
	}
}

class Dog extends Animal{
	public Dog(int age){
		super(age);
	}
}