package com.planning_example.lesson9.Factory;

public class LaptopComputer extends Computer{
	public LaptopComputer(String brand, String processor, String ramSize, String storageSize) {
		super(brand, processor, ramSize, storageSize);
	}

	@Override
	public void displayConfiguration() {
		System.out.println("Laptop Configuration:");
		System.out.println("Brand: " + getBrand());
		System.out.println("Processor: " + getProcessor());
		System.out.println("RAM Size: " + getRamSize());
		System.out.println("Storage Size: " + getStorageSize());
	}
	
}
