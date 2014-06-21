package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.DbApi;
import com.wb.paliver.data.SubjectData;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;

public class SubjectDataTable {

	public final static String TABLE_NAME = "subjectData";
	
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
	
	public static void saveEntry(DbApi dbApi, SubjectData sr) {
		if (dbApi.isOpen()) {
			ST st = dbApi.getInstanceOf(TABLE_NAME + "_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(st.render());
				stmt.setLong(i++, sr.subject_id);
				
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
	
	public static SubjectData getEntry(DbApi dbApi, String query) {		
		SubjectData sr = null;
		List<SubjectData> srList = getEntries(dbApi, query);
		if (srList != null) {
			if (srList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			sr = srList.get(0);
		}
		return sr;
	}
	
	public static List<SubjectData> getEntries(DbApi dbApi, String query) {
		List<SubjectData> srList = null;
		if (dbApi.isOpen()) {
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				SubjectData sr = new SubjectData();
				srList = new ArrayList<SubjectData>();
				while (rs.next()) {							
					sr.subject_id = rs.getLong("subject_id");
					
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
