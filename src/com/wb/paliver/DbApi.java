/*******************************************************************************
*
* This API provides a convenient facade to the underlying worker classes and methods.
*
*******************************************************************************/

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
	public DbApi() {
		stGroup = new STGroupDir("templates");
		
		db = new DbImpl_DerbyEmbedded();
	}
	
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
	
	
	public ST getInstanceOf(String template) {
		return stGroup.getInstanceOf(template);
	}
	
	// ---------------------------------------------------------------- //
	// DbInterface methods
	// ---------------------------------------------------------------- //
	
	public void createDb(String dbName) {
		db.createDb(dbName);
		
		createSearchResultTable();
		createSubjectDataTable();
		createSubjectInfoTable();
		createTopicInfoTable();
	}
	
	
	public boolean openDb(String dbName) {
		return db.openDb(dbName);
	}
	
	public boolean closeDb() {
		return db.closeDb();
	}
	
	public boolean isOpen() {
		return db.isOpen();
	}
	
	public Connection getConnection() {
		Connection conn = null;
		if (db.isOpen()) {
			conn = db.getConnection();
		}
		return conn;
	}

	public String[] showTables() {
		return db.showTables();
	}
	
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
