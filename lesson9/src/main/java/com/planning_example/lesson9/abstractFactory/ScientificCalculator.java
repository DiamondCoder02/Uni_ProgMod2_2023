package com.planning_example.lesson9.abstractFactory;

public class ScientificCalculator implements Calculator {
	
	@Override
	public int calculate(int a, int b) {
		return (int) Math.pow(a, b);
	}
}
