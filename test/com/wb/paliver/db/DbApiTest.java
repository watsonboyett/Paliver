package com.wb.paliver.db;

import java.io.IOException;

import com.wb.paliver.SearchDbApi;
import com.wb.paliver.data.SearchResult;
import com.wb.paliver.data.SubjectInfo;
import com.wb.paliver.data.TopicInfo;

public class DbApiTest {

	public static void main(String[] args) {
		SearchDbApi db = new SearchDbApi();
		
		try {
			db.openDb("//localhost/testdb?user=root");

			db.createSearchTable();
			testSearchTable(db);
			db.dropSearchTable();
	
			db.createSubjectTable();
			testSubjectTable(db);
			db.dropSubjectTable();
			
			db.createTopicTable();
			testTopicTable(db);
			db.dropTopicTable();
			
			db.closeDb();
		} catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	ex.printStackTrace();
		}
	}
	
	
	public static void testSearchTable(SearchDbApi db) throws IOException {
		System.out.println("Running Db write/read test on Search table...");
				
		int numRows = 3333;
		for (int i = 0; i < numRows; i++) {
			SearchResult sr = new SearchResult();
			sr.randomData();
		
			db.saveSearchResult(sr);
			SearchResult srFetch = db.getSearchResult("select * from search where subject_id = '" + sr.subject_id + "'");			
			if (srFetch.compareTo(sr)) {
				System.out.println("fetched result does not match inserted one.");
			}
			
			String progress = "\r progress: " + (i/(float)numRows) * 100 + "%"; 
			System.out.write(progress.getBytes());
		}
		
		System.out.println("\nDone.");
	}

	public static void testSubjectTable(SearchDbApi db) throws IOException {
		System.out.println("Running Db write/read test on Subject table...");
				
		int numRows = 3333;
		for (int i = 0; i < numRows; i++) {
			SubjectInfo si = new SubjectInfo();
			si.randomData();

			db.saveSubjectInfo(si);
			SubjectInfo siFetch = db.getSubjectInfo("select * from subject where subject_id = '" + si.subject_id + "'");			
			if (siFetch.compareTo(si)) {
				System.out.println("fetched result does not match inserted one.");
			}
			
			String progress = "\r progress: " + (i/(float)numRows) * 100 + "%"; 
			System.out.write(progress.getBytes());
		}
		
		System.out.println("\nDone.");
	}
	
	public static void testTopicTable(SearchDbApi db) throws IOException {
		System.out.println("Running Db write/read test of Topic table...");
				
		int numRows = 3333;
		for (int i = 0; i < numRows; i++) {
			TopicInfo ti = new TopicInfo();
			ti.randomData();
		
			db.saveTopicInfo(ti);
			TopicInfo tiFetch = db.getTopicInfo("select * from topic where topic_id = '" + ti.topic_id + "'");			
			if (tiFetch.compareTo(ti)) {
				System.out.println("fetched result does not match inserted one.");
			}
			
			String progress = "\r progress: " + (i/(float)numRows) * 100 + "%"; 
			System.out.write(progress.getBytes());
		}
		
		System.out.println("\nDone.");
	}

}
