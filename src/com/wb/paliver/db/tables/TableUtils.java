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
