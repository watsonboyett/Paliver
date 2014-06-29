/******************************************************************************
*
* This class is the interface to different database implementations.
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
