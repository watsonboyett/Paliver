/******************************************************************************
 *
 * This API provides a convenient facade to the underlying database functionality.
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

package com.wb.paliver;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.wb.paliver.data.SearchResult;
import com.wb.paliver.data.SubjectData;
import com.wb.paliver.data.SubjectInfo;
import com.wb.paliver.data.TopicInfo;
import com.wb.paliver.db.DbImpl_DerbyEmbedded;
import com.wb.paliver.db.DbImpl_MySql;
import com.wb.paliver.db.DbInterface;
import com.wb.paliver.db.tables.SearchResultTable;
import com.wb.paliver.db.tables.SubjectDataTable;
import com.wb.paliver.db.tables.SubjectInfoTable;
import com.wb.paliver.db.tables.TopicInfoTable;

import java.sql.Connection;
import java.util.List;

public class DbApi {

	DbInterface db;

	STGroup stGroup;

	/**
	 * Default constructor for the Database API
	 */
	public DbApi() {
		stGroup = new STGroupDir("templates");

		db = new DbImpl_DerbyEmbedded();
	}

	/**
	 * Overloaded constructor for the Database API
	 * 
	 * @param dbType
	 *            - a string representing the desired backing database (e.g.
	 *            MySql, Derby, etc.)
	 */
	public DbApi(String dbType) {
		stGroup = new STGroupDir("templates");

		// TODO: make this a factory class/method
		if (dbType.equals(DbImpl_MySql.dbType)) {
			db = new DbImpl_MySql();
		} else if (dbType.equals(DbImpl_DerbyEmbedded.dbType)) {
			db = new DbImpl_DerbyEmbedded();
		} else {
			System.out.println("Invalid Db type!");
		}
	}

	/**
	 * Get the desired string template
	 * 
	 * @param template
	 *            - a string of the name of the desired template
	 */
	public ST getInstanceOf(String template) {
		return stGroup.getInstanceOf(template);
	}

	// ---------------------------------------------------------------- //
	// DbInterface methods
	// ---------------------------------------------------------------- //

	/**
	 * Create a new database
	 * 
	 * @param dbName
	 *            - a string representing the name of the database to be create
	 */
	public void createDb(String dbName) {
		db.createDb(dbName);

		createSearchResultTable();
		createSubjectDataTable();
		createSubjectInfoTable();
		createTopicInfoTable();
	}

	/**
	 * Open an existing database
	 * 
	 * @param dbName
	 *            - a string representing the name of the database to be opened
	 * @return True if the database successfully opened
	 */
	public boolean openDb(String dbName) {
		return db.openDb(dbName);
	}

	/**
	 * Close an open database connection
	 * 
	 * @return True if the connection successfully closed
	 */
	public boolean closeDb() {
		return db.closeDb();
	}

	/**
	 * Determine if a database connection is already open
	 * 
	 * @return True if the connection is open
	 */
	public boolean isOpen() {
		return db.isOpen();
	}

	/**
	 * Gets the current database connect
	 * 
	 * @return The connection to the current database (if open; otherwise, null)
	 */
	public Connection getConnection() {
		Connection conn = null;
		if (db.isOpen()) {
			conn = db.getConnection();
		}
		return conn;
	}

	/**
	 * Gets the list of table names in the database
	 * 
	 * @return A string array containing the names of all tables
	 */
	public String[] showTables() {
		return db.showTables();
	}

	/**
	 * Gets the type of the current database implementation
	 * 
	 * @return A string containing the database type
	 */
	public String getDbType() {
		return db.getDbType();
	}

	// ---------------------------------------------------------------- //
	// SearchResultTable method wrappers
	// ---------------------------------------------------------------- //

	public void createSearchResultTable() {
		SearchResultTable.createTable(this);
	}

	public void createSearchResultTableIndexes() {
		SearchResultTable.createIndexes(this);
	}

	public void dropSearchResultTable() {
		SearchResultTable.dropTable(this);
	}

	public void saveSearchResult(SearchResult sr) {
		SearchResultTable.saveEntry(this, sr);
	}

	public SearchResult getSearchResult(String query) {
		return SearchResultTable.getEntry(this, query);
	}

	public List<SearchResult> getSearchResultList(String query) {
		return SearchResultTable.getEntries(this, query);
	}

	// ---------------------------------------------------------------- //
	// SubjectDataTable method wrappers
	// ---------------------------------------------------------------- //

	public void createSubjectDataTable() {
		SubjectDataTable.createTable(this);
	}

	public void createSubjectDataTableIndexes() {
		SubjectDataTable.createIndexes(this);
	}

	public void dropSubjectDataTable() {
		SubjectDataTable.dropTable(this);
	}

	public void saveSubjectData(SubjectData sr) {
		SubjectDataTable.saveEntry(this, sr);
	}

	public SubjectData getSubjectData(String query) {
		return SubjectDataTable.getEntry(this, query);
	}

	public List<SubjectData> getSubjectDataList(String query) {
		return SubjectDataTable.getEntries(this, query);
	}

	// ---------------------------------------------------------------- //
	// SubjectInfoTable method wrappers
	// ---------------------------------------------------------------- //

	public void createSubjectInfoTable() {
		SubjectInfoTable.createTable(this);
	}

	public void createSubjectInfoTableIndexes() {
		SubjectInfoTable.createIndexes(this);
	}

	public void dropSubjectInfoTable() {
		SubjectInfoTable.dropTable(this);
	}

	public void saveSubjectInfo(SubjectInfo si) {
		SubjectInfoTable.saveEntry(this, si);
	}

	public SubjectInfo getSubjectInfo(String query) {
		return SubjectInfoTable.getEntry(this, query);
	}

	public List<SubjectInfo> getSubjectInfoList(String query) {
		return SubjectInfoTable.getEntries(this, query);
	}

	// ---------------------------------------------------------------- //
	// TopicInfoTable method wrappers
	// ---------------------------------------------------------------- //

	public void createTopicInfoTable() {
		TopicInfoTable.createTable(this);
	}

	public void createTopicInfoTableIndexes() {
		TopicInfoTable.createIndexes(this);
	}

	public void dropTopicInfoTable() {
		TopicInfoTable.dropTable(this);
	}

	public void saveTopicInfo(TopicInfo ti) {
		TopicInfoTable.saveEntry(this, ti);
	}

	public TopicInfo getTopicInfo(String query) {
		return TopicInfoTable.getEntry(this, query);
	}

	public List<TopicInfo> getTopicInfoList(String query) {
		return TopicInfoTable.getEntries(this, query);
	}
}
