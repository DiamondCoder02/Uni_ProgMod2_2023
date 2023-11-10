package com.planning_example.lesson9.Factory;

public abstract class Computer {
	private final String brand;
	private final String processor;
	private final String ramSize;
	private final String storageSize;

	public Computer(String brand, String processor, String ramSize, String storageSize) {
		this.brand = brand;
		this.processor = processor;
		this.ramSize = ramSize;
		this.storageSize = storageSize;
	}

	public String getBrand() {
		return brand;
	}
	public String getProcessor() {
		return processor;
	}
	public String getRamSize() {
		return ramSize;
	}
	public String getStorageSize() {
		return storageSize;
	}

	public abstract void displayConfiguration();
}
