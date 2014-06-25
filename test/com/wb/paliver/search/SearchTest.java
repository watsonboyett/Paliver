package com.wb.paliver.search;

import java.io.IOException;

public class SearchTest {

	public static void main(String[] args) throws InterruptedException {
		SearchInterface searchApi = new SearchImpl_Google();

		String query = "test";
		long pageCount = -1;
		try {
			pageCount = searchApi.getPageCount(query);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Hit count: " + pageCount);
		Thread.sleep(1000);

	}
}
