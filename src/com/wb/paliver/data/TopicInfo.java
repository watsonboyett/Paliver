package com.wb.paliver.data;

import java.math.BigInteger;
import java.util.Random;

public class TopicInfo {
	
	public long topic_id = -1;
	public String topic = "";
	public String info = "";
	
	
	public boolean compareTo(TopicInfo other) {
		boolean isEqual = true;
		
		//isEqual &= (this.topic_id == other.topic_id);
		isEqual &= (this.topic == other.topic);
		isEqual &= (this.info == other.info);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		//this.topic_id = random.nextLong();
		this.topic = tester;
		this.info = tester;
	}
}
