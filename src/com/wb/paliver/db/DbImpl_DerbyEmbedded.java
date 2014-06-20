package com.wb.paliver.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.jdbc.EmbeddedDriver;

public class DbImpl_DerbyEmbedded implements DbInterface {

	public static final String dbType = "DerbyEmbedded";
	//private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String protocol = "jdbc:derby:";
	private String dbName = "";
	
	private Connection conn = null;
	
	public DbImpl_DerbyEmbedded() {
		try {
            // The newInstance() call is a work around for some broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        }
	}

	// ---------------------------------------------------------------- //
	
	@Override
	public boolean createDb(String dbName) {
		this.dbName = dbName;
				
		boolean isCreated = false;
		try {
			Driver driver = new EmbeddedDriver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(protocol + this.dbName + ";create=true");
			isCreated = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return isCreated;
	}
	
	@Override
	public boolean openDb(String dbName) {
		this.dbName = dbName;
		
		boolean isOpen = isOpen();
		if (!isOpen) {
			try {
				Driver driver = new EmbeddedDriver();
				DriverManager.registerDriver(driver);
				conn = DriverManager.getConnection(protocol + this.dbName);
				isOpen = true;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

		return isOpen;
	}
	
	@Override
	public boolean closeDb() {
		boolean isClosed = false;
		try {
			if (conn != null) {
				DriverManager.getConnection(protocol + dbName + ";shutdown=true"); 
				conn.close();
				isClosed = true;
			}
		} catch (SQLNonTransientConnectionException e) {
			// derby throws an exception to shutdown the Db, so catch it and show the "confirmation" message
			//e.printStackTrace();
			System.out.println(e.getMessage());
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
			PreparedStatement stmt = conn.prepareStatement("SELECT TABLENAME FROM SYS.SYSTABLES");
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
	
	public String getDbType() {
		return DbImpl_DerbyEmbedded.dbType;
	}
}

