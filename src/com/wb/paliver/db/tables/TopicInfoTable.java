package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.DbApi;
import com.wb.paliver.data.TopicInfo;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;

public class TopicInfoTable {

	public final static String TABLE_NAME = "topicInfo";
	
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
	
	public static void saveEntry(DbApi dbApi, TopicInfo ti) {
		if (dbApi.isOpen()) {
			ST st = dbApi.getInstanceOf(TABLE_NAME + "_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(st.render());
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
	
	public static TopicInfo getEntry(DbApi dbApi, String query) {		
		TopicInfo ti = null;
		List<TopicInfo> tiList = getEntries(dbApi, query);
		if (tiList != null) {
			if (tiList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			ti = tiList.get(0);
		}
		return ti;
	}
	
	public static List<TopicInfo> getEntries(DbApi dbApi, String query) {
		List<TopicInfo> tiList = null;
		if (dbApi.isOpen()) {
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(query);
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
