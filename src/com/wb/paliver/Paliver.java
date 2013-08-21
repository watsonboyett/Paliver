package com.wb.paliver;


import com.wb.paliver.data.SearchResult;

public class Paliver {

	public static void main(String[] args) {
		SearchDbApi db = new SearchDbApi();
		
		try {
			db.openDb("//localhost/searchpop?user=root");
			
			db.createSearchTable();
			db.createSubjectTable();
			db.createTopicTable();
			
			
			
			
			
			db.closeDb();
		} catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	ex.printStackTrace();
		}
	}
	


}
