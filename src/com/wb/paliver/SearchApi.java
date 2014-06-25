package com.wb.paliver;


import java.io.IOException;

import com.wb.paliver.search.SearchImpl_Google;
import com.wb.paliver.search.SearchImpl_Random;
import com.wb.paliver.search.SearchInterface;

public class SearchApi {

	SearchInterface si;
	
	public SearchApi() {
		
		si = new SearchImpl_Google();
	}
	
	public SearchApi(String searchType) {
		
		// TODO: make this a factory class/method
		if (searchType.equals(SearchImpl_Google.searchType)) {
			si = new SearchImpl_Google();
		} else if (searchType.equals(SearchImpl_Random.searchType)) {
				si = new SearchImpl_Random();
		} else {
			System.out.println("Invalid search type!");
		}
	}
	
	
	
	public String getPage(String query) throws IOException {
		return si.getPage(query);
	}
	
	public long getPageCount(String query) throws IOException {
		return si.getPageCount(query);
	}
}
