package com.wb.paliver.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DbImpl_MySql implements DbInterface {

	private String protocol = "jdbc:mysql:";
	private String dbName = "";
	
	private Connection conn = null;
	
	public DbImpl_MySql() {
		try {
            // The newInstance() call is a work around for some broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        }
	}

	// ---------------------------------------------------------------- //
	
	@Override
	public boolean openDb(String dbName) {
		this.dbName = dbName;
		
		boolean isOpen = false;
		try {
			conn = DriverManager.getConnection(protocol + this.dbName);
			isOpen = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return isOpen;
	}
	
	@Override
	public boolean closeDb() {
		boolean isClosed = false;
		try {
			if (conn != null) {
				conn.close();
				isClosed = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return isClosed;
	}
	
	@Override
	public boolean isOpen() {
		boolean isOpen = false;
		try {
			if (conn != null) {
				isOpen = conn.isValid(1);
				isOpen = isOpen && !conn.isClosed();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return isOpen;
	}
	
	@Override
	public Connection getConnection() {
		return conn;
	}

	@Override
	public String[] showTables() {
		String[] tables = null; 
		try {
			PreparedStatement stmt = conn.prepareStatement("show tables;");
			ResultSet rs = stmt.executeQuery();
			
			List<String> resList = new ArrayList<String>();
			while (rs.next()) {
				resList.add(rs.getString(1));
			}
			
			tables = new String[resList.size()];
			for (int i = 0; i < resList.size(); i++) {
				tables[i] = resList.get(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return tables;
	}
}

