package com.ciwong.designpattern.combining.composite;

public class RubberDuck implements Quackable {
 
	public void quack() {
		System.out.println("Squeak");
	}
  
	public String toString() {
		return "Rubber Duck";
	}
}
