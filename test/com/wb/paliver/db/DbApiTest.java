package com.wb.paliver.db;

import java.io.IOException;

import com.wb.paliver.DbApi;
import com.wb.paliver.data.SearchResult;
import com.wb.paliver.data.SubjectData;
import com.wb.paliver.data.SubjectInfo;
import com.wb.paliver.data.TopicInfo;

public class DbApiTest {

	public static void main(String[] args) {
		DbApi dbApi = new DbApi();
		
		try {
			String dbType = "Derby"; 
			String dbName;
			
			if (dbType.equalsIgnoreCase("MySQL")) {
				dbName = "//localhost/db_test?user=root";
			} else {
				dbName = "db_test";
			}
			
			
			dbApi.createDb(dbName);

			dbApi.createSearchResultTable();
			testSearchResultTable(dbApi);
			dbApi.dropSearchResultTable();
			
			dbApi.createSubjectDataTable();
			testSubjectDataTable(dbApi);
			dbApi.dropSubjectDataTable();
	
			dbApi.createSubjectInfoTable();
			testSubjectTable(dbApi);
			dbApi.dropSubjectInfoTable();
			
			dbApi.createTopicInfoTable();
			testTopicTable(dbApi);
			dbApi.dropTopicInfoTable();
			
			dbApi.closeDb();
		} catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	ex.printStackTrace();
		}
	}
	
	
	public static void testSearchResultTable(DbApi dbApi) throws IOException, InterruptedException {
		System.out.println("Running Db write/read test on searchResult table...");
				
		int numRows = 100;
		for (int i = 0; i < numRows; i++) {
			SearchResult sr = new SearchResult();
			sr.randomData();
		
			dbApi.saveSearchResult(sr);
			SearchResult srFetch = dbApi.getSearchResult("select * from searchResult where query = '" + sr.query + "' and time = '" + sr.time + "'");			
			if (srFetch.compareTo(sr)) {
				System.out.println("fetched result does not match inserted one.");
				Thread.sleep(3000);
			}
			
			System.out.print("\r progress: " + (i/(float)numRows) * 100 + "%");
		}
		
		System.out.println("\nDone.");
	}
	
	public static void testSubjectDataTable(DbApi dbApi) throws IOException, InterruptedException {
		System.out.println("Running Db write/read test on subjectData table...");
				
		int numRows = 100;
		for (int i = 0; i < numRows; i++) {
			SubjectData sd = new SubjectData();
			sd.randomData();
		
			dbApi.saveSubjectData(sd);
			SubjectData srFetch = dbApi.getSubjectData("select * from subjectData where subject_id = " + sd.subject_id + " and time = '" + sd.time + "'");			
			if (srFetch.compareTo(sd)) {
				System.out.println("fetched result does not match inserted one.");
				Thread.sleep(3000);
			}
			
			System.out.print("\r progress: " + (i/(float)numRows) * 100 + "%");
		}
		
		System.out.println("\nDone.");
	}

	public static void testSubjectTable(DbApi dbApi) throws IOException, InterruptedException {
		System.out.println("Running Db write/read test on subjectInfo table...");
				
		int numRows = 100;
		for (int i = 0; i < numRows; i++) {
			SubjectInfo si = new SubjectInfo();
			si.randomData();

			dbApi.saveSubjectInfo(si);
			SubjectInfo siFetch = dbApi.getSubjectInfo("select * from subjectInfo where subject = '" + si.subject + "'");			
			if (siFetch.compareTo(si)) {
				System.out.println("fetched result does not match inserted one.");
				Thread.sleep(3000);
			}
			
			String progress = "\r progress: " + (i/(float)numRows) * 100 + "%"; 
			System.out.write(progress.getBytes());
		}
		
		System.out.println("\nDone.");
	}
	
	public static void testTopicTable(DbApi dbApi) throws IOException, InterruptedException {
		System.out.println("Running Db write/read test of topicInfo table...");
				
		int numRows = 100;
		for (int i = 0; i < numRows; i++) {
			TopicInfo ti = new TopicInfo();
			ti.randomData();
		
			dbApi.saveTopicInfo(ti);
			TopicInfo tiFetch = dbApi.getTopicInfo("select * from topicInfo where topic = '" + ti.topic + "'");			
			if (tiFetch.compareTo(ti)) {
				System.out.println("fetched result does not match inserted one.");
				Thread.sleep(3000);
			}
			
			String progress = "\r progress: " + (i/(float)numRows) * 100 + "%"; 
			System.out.write(progress.getBytes());
		}
		
		System.out.println("\nDone.");
	}

}
