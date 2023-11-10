package com.planning_example.lesson9.abstractFactory;

public class SimpleCalculator implements Calculator{

	@Override
	public int calculate(int a, int b) {
		return a + b;
	}
	
}
