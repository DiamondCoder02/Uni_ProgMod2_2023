package com.planning_example.lesson9.Factory;

public class ComputerFactory {
	public static Computer createComputer(String type, String brand, String processor, String ramSize, String storageSize) {
		if (type.equalsIgnoreCase("laptop")) {
			return new LaptopComputer(brand, processor, ramSize, storageSize);
		} else if (type.equalsIgnoreCase("desktop")) {
			return new DesktopComputer(brand, processor, ramSize, storageSize);
		} else {
			throw new IllegalArgumentException("Invalid computer type: " + type);
		}
	}
}
