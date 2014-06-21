package com.wb.paliver.search;

import java.sql.Timestamp;
import java.util.Random;


public class SearchResult {

	public long search_id = -1;
	public String query = "";

	public Timestamp time = null;

	public long subject_id = -1;
	public long pageCount = -1;
	
	
	public boolean compareTo(SearchResult other) {
		boolean isEqual = true;
		
		isEqual &= (this.query == other.query);
		isEqual &= (this.time == other.time);
		
		isEqual &= (this.subject_id == other.subject_id);
		isEqual &= (this.pageCount == other.pageCount);

		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		
		this.subject_id = random.nextLong();
		
		this.time = new Timestamp(System.currentTimeMillis());

	}
}
