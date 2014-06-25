package com.wb.paliver.data;

public class DataStrategy_Diff implements DataStrategy {

	@Override
	public double execute(double a, double b) {
		double c = 0;
		if (a > 0 && b > 0) {
			c = Math.abs(a - b) / ((Math.abs(a) + Math.abs(b)) / 2);
		}
		return c;
	}

	
	
}
