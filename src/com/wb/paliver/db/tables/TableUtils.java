/******************************************************************************
*
* This class implements basic table utility operations for database tables.
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

import java.sql.SQLException;
import java.sql.Statement;

import org.stringtemplate.v4.ST;

import com.wb.paliver.DbApi;

public class TableUtils {

	public static void createTable(DbApi dbApi, String tableName, ST st) {
		if (dbApi.isOpen()) {
			String[] tables = dbApi.showTables();
			for (int i = 0; i < tables.length; i++) {
				int match = tables[i].compareToIgnoreCase(tableName);
				if (match == 0) {
					System.out.println("Table: '" + tableName + "' already exists in db!");
					return;
				}
			}
			
			try {
				Statement stmt = dbApi.getConnection().createStatement();
				stmt.execute(st.render());
				stmt.close();
				System.out.println("Created table: " + tableName);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void createIndexes(DbApi dbApi) {
		
	}
	
	public static void dropTable(DbApi dbApi, String tableName) {
		if (dbApi.isOpen()) {
			boolean foundTable = false;
			String[] tables = dbApi.showTables();
			for (int i = 0; i < tables.length; i++) {
				int match = tables[i].compareToIgnoreCase(tableName);
				if (match == 0) {
					foundTable = true;
					break;
				}
			}
			if (!foundTable) {
				System.out.println("Table: '" + tableName + "' not found in db!");
				return;
			}
			
			try {
				Statement stmt = dbApi.getConnection().createStatement();
				stmt.execute("drop table " + tableName);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
}
