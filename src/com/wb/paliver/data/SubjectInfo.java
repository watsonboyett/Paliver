package com.wb.paliver.data;

import java.math.BigInteger;
import java.util.Random;

public class SubjectInfo {
	
	public String subject = "";
	public long subject_id = -1;
	
	public String descrip = "";
	
	
	public boolean compareTo(SubjectInfo other) {
		boolean isEqual = true;

		isEqual &= (this.subject == other.subject);
		isEqual &= (this.subject_id == other.subject_id);

		isEqual &= (this.descrip == other.descrip);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		this.subject = tester;
		this.subject_id = random.nextLong();
		
		this.descrip = tester;
	}
}
