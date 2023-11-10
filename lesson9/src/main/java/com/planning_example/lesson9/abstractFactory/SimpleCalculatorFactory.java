package com.planning_example.lesson9.abstractFactory;

public class SimpleCalculatorFactory implements CalculatorFactory{
	
	@Override
	public Calculator createCalculator() {
		return new SimpleCalculator();
	}
}
