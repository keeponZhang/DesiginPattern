package com.ciwong.designpattern.templatemethod.barista;

public class BeverageTestDrive {
	public static void main(String[] args) {
 
		Tea tea = new Tea();
		Coffee coffee = new Coffee();
 
		System.out.println("\nMaking tea...");
		tea.prepareRecipe();
 
		System.out.println("\nMaking coffee...");
		coffee.prepareRecipe();

 
		com.ciwong.designpattern.templatemethod.barista.TeaWithHook teaHook = new com.ciwong.designpattern.templatemethod.barista.TeaWithHook();
		com.ciwong.designpattern.templatemethod.barista.CoffeeWithHook coffeeHook = new com.ciwong.designpattern.templatemethod.barista.CoffeeWithHook();
 
		System.out.println("\nMaking tea...");
		teaHook.prepareRecipe();
 
		System.out.println("\nMaking coffee...");
		coffeeHook.prepareRecipe();
	}
}
