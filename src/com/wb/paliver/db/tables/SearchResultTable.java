package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.DbApi;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;
import com.wb.paliver.search.SearchResult;

public class SearchResultTable {

	public final static String TABLE_NAME = "searchResult";
	
	public static void createTable(DbApi dbApi) {
		ST st;
		if (dbApi.getDbType().equals(DbImpl_MySql.dbType)) {
			st = dbApi.getInstanceOf(TABLE_NAME + "_create");
		} else if (dbApi.getDbType().equals(DbImpl_DerbyEmbedded.dbType)) {
			st = dbApi.getInstanceOf(TABLE_NAME + "_create_derby");
		} else {
			// todo: handle this error case better
			st = null;
		}
		
		TableUtils.createTable(dbApi, TABLE_NAME, st);
	}
	
	public static void createIndexes(DbApi dbApi) {
		
	}
	
	public static void dropTable(DbApi dbApi) {
		TableUtils.dropTable(dbApi, TABLE_NAME);
	}
	
	public static void saveEntry(DbApi dbApi, SearchResult sr) {
		if (dbApi.isOpen()) {
			ST st = dbApi.getInstanceOf(TABLE_NAME + "_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(st.render());
				stmt.setString(i++, sr.query);
				stmt.setTimestamp(i++, sr.time);
				
				stmt.setLong(i++, sr.subject_id);
				stmt.setLong(i++, sr.pageCount);
				
				stmt.execute();
				stmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public static SearchResult getEntry(DbApi dbApi, String query) {		
		SearchResult sr = null;
		List<SearchResult> srList = getEntries(dbApi, query);
		if (srList != null) {
			if (srList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			sr = srList.get(0);
		}
		return sr;
	}
	
	public static List<SearchResult> getEntries(DbApi dbApi, String query) {
		List<SearchResult> srList = null;
		if (dbApi.isOpen()) {
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				SearchResult sr = new SearchResult();
				srList = new ArrayList<SearchResult>();
				while (rs.next()) {							
					
					sr.query = rs.getString("query");
					sr.time = rs.getTimestamp("time");
					
					sr.subject_id = rs.getLong("subject_id");
					sr.pageCount = rs.getLong("pageCount"); 

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
