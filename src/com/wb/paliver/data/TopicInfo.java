package com.wb.paliver.data;

import java.math.BigInteger;
import java.util.Random;

public class TopicInfo {
	
	public String topic = "";
	public long topic_id = -1;
	
	public String descrip = "";
	
	
	public boolean compareTo(TopicInfo other) {
		boolean isEqual = true;
		
		isEqual &= (this.topic == other.topic);
		isEqual &= (this.topic_id == other.topic_id);
		
		isEqual &= (this.descrip == other.descrip);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		this.topic = tester;
		this.topic_id = random.nextLong();
		
		this.descrip = tester;
	}
}
