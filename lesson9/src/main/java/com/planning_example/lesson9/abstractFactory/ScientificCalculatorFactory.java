package com.planning_example.lesson9.abstractFactory;

public class ScientificCalculatorFactory implements CalculatorFactory{
	@Override
	public Calculator createCalculator() {
		return new ScientificCalculator();
	}
}
