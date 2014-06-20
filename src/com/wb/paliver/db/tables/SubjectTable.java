package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.SearchDbApi;
import com.wb.paliver.data.SubjectInfo;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;

public class SubjectTable {

	public final static String TABLE_NAME = "subject";
	
	public static void createTable(SearchDbApi db) {
		ST st;
		if (db.getDbType().equals(DbImpl_MySql.dbType)) {
			st = db.getInstanceOf("subject_create");
		} else if (db.getDbType().equals(DbImpl_DerbyEmbedded.dbType)) {
			st = db.getInstanceOf("subject_create_derby");
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
	
	public static void saveEntry(SearchDbApi db, SubjectInfo si) {
		if (db.isOpen()) {
			ST st = db.getInstanceOf("subject_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = db.getConnection().prepareStatement(st.render());
				//stmt.setLong(i++, si.subject_id);
				stmt.setString(i++, si.subject);
				stmt.setString(i++, si.info);
				stmt.setLong(i++, si.topic_id);
				
				stmt.execute();
				stmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public static SubjectInfo getEntry(SearchDbApi db, String query) {		
		SubjectInfo si = null;
		List<SubjectInfo> srList = getEntries(db, query);
		if (srList != null) {
			if (srList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			si = srList.get(0);
		}
		return si;
	}
	
	public static List<SubjectInfo> getEntries(SearchDbApi db, String query) {
		List<SubjectInfo> siList = null;
		if (db.isOpen()) {
			try {
				PreparedStatement stmt = db.getConnection().prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				SubjectInfo si = new SubjectInfo();
				siList = new ArrayList<SubjectInfo>();
				while (rs.next()) {							
					si.subject_id = rs.getLong("subject_id");
					si.subject = rs.getString("subject");
					si.info = rs.getString("info");
					
					si.topic_id = rs.getLong("topic_id");
					
					siList.add(si);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return siList;
	}
	
}
