package com.wb.paliver.data;

public class DataStrategy_Log implements DataStrategy {

	@Override
	public double execute(double a, double b) {
		double c = 0;
		if (a > 0 && b > 0) {
			c = Math.log(a / b);
		}
		return c;
	}

	
	
}
