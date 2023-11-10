package com.planning_example.lesson9;

import com.planning_example.lesson9.Factory.Computer;
import com.planning_example.lesson9.Factory.ComputerFactory;
import com.planning_example.lesson9.Singleton.ComputerSingleton;
import com.planning_example.lesson9.abstractFactory.Calculator;
import com.planning_example.lesson9.abstractFactory.CalculatorFactory;
import com.planning_example.lesson9.abstractFactory.ScientificCalculatorFactory;
import com.planning_example.lesson9.abstractFactory.SimpleCalculatorFactory;

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

	private static void testAbstractFactory() {
		try {
			CalculatorFactory simpleCock = new SimpleCalculatorFactory();
			Calculator simpleCalc = simpleCock.createCalculator();
			System.out.println("Simple Calculator: " + simpleCalc.calculate(5, 3));

			CalculatorFactory scientificCock = new ScientificCalculatorFactory();
			Calculator scientificCalc = scientificCock.createCalculator();
			System.out.println("Scientific Calculator: " + scientificCalc.calculate(5, 3));

		} catch (IllegalArgumentException e) {
			System.out.println("Caught IllegalArgumentException: " + e.getMessage());
		}
	}

	private static void testComputerSingleton() {
		ComputerSingleton computer1 = ComputerSingleton.getInstance();
		computer1.setRam("16GB");
		computer1.setHdd("1TB");
		computer1.setGraphicsCardEnabled(true);
		computer1.setBluetoothEnabled(true);
		System.out.println("Computer 1: \n" + 
			"RAM: " + computer1.getRam() + "\n" +
			"HDD: " + computer1.getHdd() + "\n" +
			"Graphics Card Enabled: " + computer1.isGraphicsCardEnabled() + "\n" +
			"Bluetooth Enabled: " + computer1.isBluetoothEnabled()
		);
		System.out.println("\nLOL\n");

		ComputerSingleton computer2 = ComputerSingleton.getInstance();
		System.out.println("Computer 2: \n" + 
			"RAM: " + computer2.getRam() + "\n" +
			"HDD: " + computer2.getHdd() + "\n" +
			"Graphics Card Enabled: " + computer2.isGraphicsCardEnabled() + "\n" +
			"Bluetooth Enabled: " + computer2.isBluetoothEnabled()
		);

		System.out.println("\nComputer 1 and Computer 2 are the same instance: " + (computer1 == computer2));
	}

	public static void main(String[] args) {
		int var = 2;
		switch (var) {
			case 0: testComputerFactory(); break;
			case 1: testAbstractFactory(); break;
			case 2: testComputerSingleton(); break;
			default: 
				System.out.println("Invalid input");
				break;
		}
	}

}
