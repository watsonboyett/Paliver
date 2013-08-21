package com.wb.paliver;


import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.wb.paliver.data.SearchResult;
import com.wb.paliver.data.SubjectInfo;
import com.wb.paliver.data.TopicInfo;
import com.wb.paliver.db.DbImpl_MySql;
import com.wb.paliver.db.DbInterface;
import com.wb.paliver.db.tables.SearchTable;
import com.wb.paliver.db.tables.SubjectTable;
import com.wb.paliver.db.tables.TopicTable;

import java.sql.Connection;
import java.util.List;


public class SearchDbApi {

	DbInterface db = new DbImpl_MySql();
	
	STGroup stGroup;
	public SearchDbApi() {
		stGroup = new STGroupDir("templates");
	}
	
	public ST getInstanceOf(String template) {
		return stGroup.getInstanceOf(template);
	}
	
	// ---------------------------------------------------------------- //
	// DbInterface methods
	// ---------------------------------------------------------------- //
	
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
	
	// ---------------------------------------------------------------- //
	// SearchTable method wrappers
	// ---------------------------------------------------------------- //
	
	public void createSearchTable() {
		SearchTable.createTable(this);
	}

	public void createSearchIndexes() {
		SearchTable.createIndexes(this);
	}
	
	public void dropSearchTable() {
		SearchTable.dropTable(this);
	}
	
	public void saveSearchResult(SearchResult sr) {
		SearchTable.saveEntry(this, sr);
	}
	
	public SearchResult getSearchResult(String query) {
		return SearchTable.getEntry(this, query);
	}
	
	public List<SearchResult> getSearchResults(String query) {
		return SearchTable.getEntries(this, query);
	}
	
	// ---------------------------------------------------------------- //
	// SubjectTable method wrappers
	// ---------------------------------------------------------------- //
	
	public void createSubjectTable() {
		SubjectTable.createTable(this);
	}

	public void createSubjectIndexes() {
		SubjectTable.createIndexes(this);
	}
	
	public void dropSubjectTable() {
		SubjectTable.dropTable(this);
	}
	
	public void saveSubjectInfo(SubjectInfo si) {
		SubjectTable.saveEntry(this, si);
	}
	
	public SubjectInfo getSubjectInfo(String query) {
		return SubjectTable.getEntry(this, query);
	}
	
	public List<SubjectInfo> getSubjectInfos(String query) {
		return SubjectTable.getEntries(this, query);
	}
	
	// ---------------------------------------------------------------- //
	// TopicTable method wrappers
	// ---------------------------------------------------------------- //
	
	public void createTopicTable() {
		TopicTable.createTable(this);
	}

	public void createTopicIndexes() {
		TopicTable.createIndexes(this);
	}
	
	public void dropTopicTable() {
		TopicTable.dropTable(this);
	}
	
	public void saveTopicInfo(TopicInfo ti) {
		TopicTable.saveEntry(this, ti);
	}
	
	public TopicInfo getTopicInfo(String query) {
		return TopicTable.getEntry(this, query);
	}
	
	public List<TopicInfo> getTopicInfos(String query) {
		return TopicTable.getEntries(this, query);
	}
}
