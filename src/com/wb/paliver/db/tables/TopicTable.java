package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.SearchDbApi;
import com.wb.paliver.data.TopicInfo;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;

public class TopicTable {

	public final static String TABLE_NAME = "topic";
	
	public static void createTable(SearchDbApi db) {
		ST st;
		if (db.getDbType().equals(DbImpl_MySql.dbType)) {
			st = db.getInstanceOf("topic_create");
		} else if (db.getDbType().equals(DbImpl_DerbyEmbedded.dbType)) {
			st = db.getInstanceOf("topic_create_derby");
		} else {
			// todo: handle this error case better
			st = null;
		}
		TableUtils.createTable(db, TABLE_NAME, st);
	}
	
	public static void createIndexes(SearchDbApi db) {
		
	}
	
	public static void dropTable(SearchDbApi db) {
		TableUtils.dropTable(db, TABLE_NAME);
	}
	
	public static void saveEntry(SearchDbApi db, TopicInfo ti) {
		if (db.isOpen()) {
			ST st = db.getInstanceOf("topic_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = db.getConnection().prepareStatement(st.render());
				//stmt.setLong(i++, ti.topic_id);
				stmt.setString(i++, ti.topic);
				stmt.setString(i++, ti.info);
				
				stmt.execute();
				stmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public static TopicInfo getEntry(SearchDbApi db, String query) {		
		TopicInfo ti = null;
		List<TopicInfo> tiList = getEntries(db, query);
		if (tiList != null) {
			if (tiList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			ti = tiList.get(0);
		}
		return ti;
	}
	
	public static List<TopicInfo> getEntries(SearchDbApi db, String query) {
		List<TopicInfo> tiList = null;
		if (db.isOpen()) {
			try {
				PreparedStatement stmt = db.getConnection().prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				TopicInfo ti = new TopicInfo();
				tiList = new ArrayList<TopicInfo>();
				while (rs.next()) {				
					//ti.topic_id = rs.getLong("topic_id");
					ti.topic = rs.getString("topic");
					ti.info = rs.getString("info");
					
					tiList.add(ti);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return tiList;
	}
	
}
