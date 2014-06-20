package com.wb.paliver.data;

import java.math.BigInteger;
import java.util.Random;

public class SubjectInfo {
	
	public long subject_id = -1;
	public String subject = "";
	public String info = "";
	
	public long topic_id = -1;
	
	public boolean compareTo(SubjectInfo other) {
		boolean isEqual = true;

		//isEqual &= (this.subject_id == other.subject_id);
		isEqual &= (this.subject == other.subject);
		isEqual &= (this.info == other.info);
		
		isEqual &= (this.topic_id == other.topic_id);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		//this.subject_id = random.nextLong();
		this.subject = tester;
		this.info = tester;
		
		this.topic_id = random.nextLong();
	}
}
