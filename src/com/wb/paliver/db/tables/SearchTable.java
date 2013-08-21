package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.SearchDbApi;
import com.wb.paliver.data.SearchResult;

public class SearchTable {

	public final static String TABLE_NAME = "search";
	
	public static void createTable(SearchDbApi db) {
		ST st = db.getInstanceOf("search_create");
		TableUtils.createTable(db, TABLE_NAME, st);
	}
	
	public static void createIndexes(SearchDbApi db) {
		
	}
	
	public static void dropTable(SearchDbApi db) {
		TableUtils.dropTable(db, TABLE_NAME);
	}
	
	public static void saveEntry(SearchDbApi db, SearchResult sr) {
		if (db.isOpen()) {
			ST st = db.getInstanceOf("search_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = db.getConnection().prepareStatement(st.render());
				stmt.setString(i++, sr.subject);
				stmt.setLong(i++, sr.subject_id);
				stmt.setString(i++, sr.topic);
				stmt.setLong(i++, sr.topic_id);
				
				stmt.setTimestamp(i++, sr.time);
				stmt.setDouble(i++, sr.assoc);
				
				stmt.setDouble(i++, sr.love);
				stmt.setDouble(i++, sr.hate);
				stmt.setDouble(i++, sr.ambiv);
				
				stmt.setDouble(i++, sr.want);
				stmt.setDouble(i++, sr.need);
				stmt.setDouble(i++, sr.subsist);
				
				stmt.setDouble(i++, sr.good);
				stmt.setDouble(i++, sr.evil);
				stmt.setDouble(i++, sr.demean);
				
				stmt.execute();
				stmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public static SearchResult getEntry(SearchDbApi db, String query) {		
		SearchResult sr = null;
		List<SearchResult> srList = getEntries(db, query);
		if (srList != null) {
			if (srList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			sr = srList.get(0);
		}
		return sr;
	}
	
	public static List<SearchResult> getEntries(SearchDbApi db, String query) {
		List<SearchResult> srList = null;
		if (db.isOpen()) {
			try {
				PreparedStatement stmt = db.getConnection().prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				SearchResult sr = new SearchResult();
				srList = new ArrayList<SearchResult>();
				while (rs.next()) {							
					sr.subject = rs.getString("subject");
					sr.subject_id = rs.getLong("subject_id");
					
					sr.topic = rs.getString("topic");
					sr.topic_id = rs.getLong("topic_id");
					
					sr.time = rs.getTimestamp("time");
					sr.assoc = rs.getDouble("assoc"); 
				
					sr.love = rs.getDouble("love");
					sr.hate = rs.getDouble("hate");
					sr.ambiv = rs.getDouble("ambiv");
					
					sr.want = rs.getDouble("want");
					sr.need = rs.getDouble("need");
					sr.subsist = rs.getDouble("subsist");
					
					sr.good = rs.getDouble("good");
					sr.evil = rs.getDouble("evil");
					sr.demean = rs.getDouble("demean");
					
					srList.add(sr);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return srList;
	}
	
}
