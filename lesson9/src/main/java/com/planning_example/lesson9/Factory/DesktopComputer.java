package com.planning_example.lesson9.Factory;

public class DesktopComputer extends Computer{

	public DesktopComputer(String brand, String processor, String ramSize, String storageSize) {
		super(brand, processor, ramSize, storageSize);
	}

	@Override
	public void displayConfiguration() {
		System.out.println("PC Configuration:");
		System.out.println("Brand: " + getBrand());
		System.out.println("Processor: " + getProcessor());
		System.out.println("RAM Size: " + getRamSize());
		System.out.println("Storage Size: " + getStorageSize());
		throw new UnsupportedOperationException("Unimplemented method 'displayConfiguration'");
	}
	
}
