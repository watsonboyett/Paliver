package com.wb.paliver.db;

import java.sql.Connection;

public interface DbInterface {
	
	public boolean createDb(String dbName);
	
	public boolean openDb(String dbName);
	
	public boolean closeDb();
	
	public boolean isOpen();
	
	public Connection getConnection();
	
	public String[] showTables();
	
	public String getDbType();

}
