package com.wb.paliver.data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Random;

public class SearchResult {
	
	public String subject = "";
	public long subject_id = -1;
	
	public String topic = "";
	public long topic_id = -1;

	public Timestamp time = null;
	public double assoc = -1;

	public double love = -1;
	public double hate = -1; 
	public double ambiv = -1;

	public double want = -1;
	public double need = -1;
	public double subsist = -1;

	public double good = -1;
	public double evil = -1;
	public double demean = -1;
	
	
	
	public boolean compareTo(SearchResult other) {
		boolean isEqual = true;
				
		isEqual &= (this.subject == other.subject);
		isEqual &= (this.subject_id == other.subject_id);
		
		isEqual &= (this.topic == other.topic);
		isEqual &= (this.topic_id == other.topic_id);

		isEqual &= (this.time == other.time);
		isEqual &= (this.assoc == other.assoc);

		isEqual &= (this.love == other.love);
		isEqual &= (this.hate == other.hate);
		isEqual &= (this.ambiv == other.ambiv);

		isEqual &= (this.want == other.want);
		isEqual &= (this.need == other.need);
		isEqual &= (this.subsist == other.subsist);

		isEqual &= (this.good == other.good);
		isEqual &= (this.evil == other.evil);
		isEqual &= (this.demean == other.demean);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		
		String tester = new BigInteger(64, random).toString();
		this.subject = tester;
		this.subject_id = random.nextLong();
		
		this.topic = tester;
		this.topic_id = random.nextLong();
		
		this.time = new Timestamp(100000);
		this.assoc = Math.random();
		
		this.love = Math.random();
		this.hate = Math.random();
		this.ambiv = Math.random();
		
		this.want = Math.random();
		this.need = Math.random();
		this.subsist = Math.random();
		
		this.good = Math.random();
		this.evil = Math.random();
		this.demean = Math.random();
	}
	
}
