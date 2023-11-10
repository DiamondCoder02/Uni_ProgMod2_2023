package com.planning_example.lesson9;

import com.planning_example.lesson9.Factory.Computer;
import com.planning_example.lesson9.Factory.ComputerFactory;

public class Lesson9Application {
	private static void testComputerFactory() {
		try {
			Computer desktop = ComputerFactory.createComputer("desktop", "Dell", "Intel Core i7", "16GB", "1TB");
			desktop.displayConfiguration();
			Computer laptop = ComputerFactory.createComputer("laptop", "HP", "Intel Core i5", "8GB", "512GB");
			laptop.displayConfiguration();
		} catch (IllegalArgumentException e) {
			System.out.println("Caught IllegalArgumentException: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		testComputerFactory();
	}

}
