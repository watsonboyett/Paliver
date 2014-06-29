/******************************************************************************
*
* This class implements basic table operations for the subjectInfo database table.
*
* Copyright (C) 2014  Paliver
* 
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 3 of the License, or
* (at your option) any later version.
*   
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software Foundation,
* Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
******************************************************************************/

package com.wb.paliver.db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import com.wb.paliver.DbApi;
import com.wb.paliver.data.SubjectInfo;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;

public class SubjectInfoTable {

	public final static String TABLE_NAME = "subjectInfo";
	
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
	
	public static void saveEntry(DbApi dbApi, SubjectInfo si) {
		if (dbApi.isOpen()) {
			ST st = dbApi.getInstanceOf(TABLE_NAME + "_insert");
			
			int i = 1;
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(st.render());
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
	
	public static SubjectInfo getEntry(DbApi dbApi, String query) {		
		SubjectInfo si = null;
		List<SubjectInfo> srList = getEntries(dbApi, query);
		if (srList != null) {
			if (srList.size() > 1) {
				System.out.println("More than one row was found! Returning first row only.");
			}
			si = srList.get(0);
		}
		return si;
	}
	
	public static List<SubjectInfo> getEntries(DbApi dbApi, String query) {
		List<SubjectInfo> siList = null;
		if (dbApi.isOpen()) {
			try {
				PreparedStatement stmt = dbApi.getConnection().prepareStatement(query);
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
