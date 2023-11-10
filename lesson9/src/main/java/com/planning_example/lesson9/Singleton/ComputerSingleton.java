package com.planning_example.lesson9.Singleton;

public class ComputerSingleton {
	private String ram;
	private String hdd;

	private boolean isGraphicsCardEnabled;
	private boolean isBluetoothEnabled;

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public boolean isGraphicsCardEnabled() {
		return isGraphicsCardEnabled;
	}

	public void setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
		this.isGraphicsCardEnabled = isGraphicsCardEnabled;
	}

	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}

	public void setBluetoothEnabled(boolean isBluetoothEnabled) {
		this.isBluetoothEnabled = isBluetoothEnabled;
	}

	private static ComputerSingleton instance;

	public static void setInstance(ComputerSingleton instance) {
		ComputerSingleton.instance = instance;
	}

	private ComputerSingleton() {

	}

	public static ComputerSingleton getInstance() {
		if (instance == null) {
			instance = new ComputerSingleton();
		}
		return instance;
	}
}
