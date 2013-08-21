package com.wb.paliver.db.tables;

import java.sql.SQLException;
import java.sql.Statement;

import org.stringtemplate.v4.ST;

import com.wb.paliver.SearchDbApi;

public class TableUtils {

	public static void createTable(SearchDbApi db, String tableName, ST st) {
		if (db.isOpen()) {
			String[] tables = db.showTables();
			for (int i = 0; i < tables.length; i++) {
				int match = tables[i].compareToIgnoreCase(tableName);
				if (match == 0) {
					System.out.println("Table: '" + tableName + "' already exists in db!");
					return;
				}
			}
			
			try {
				Statement stmt = db.getConnection().createStatement();
				stmt.execute(st.render());
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void createIndexes(SearchDbApi db) {
		
	}
	
	public static void dropTable(SearchDbApi db, String tableName) {
		if (db.isOpen()) {
			boolean foundTable = false;
			String[] tables = db.showTables();
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
				Statement stmt = db.getConnection().createStatement();
				stmt.execute("drop table " + tableName);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
}
