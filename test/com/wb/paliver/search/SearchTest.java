package com.wb.paliver.search;

import java.io.IOException;

public class SearchTest {

	public static void main(String[] args) throws InterruptedException {
		SearchInterface searchApi = new SearchImpl_Google();
		
		int test = -1;
		try {
			test = searchApi.getPageCount("test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Hit count: " + test);
		Thread.sleep(10);
	}
}
