package com.wb.paliver.search;

import java.io.IOException;

public interface SearchInterface {

	/**
	 * Gets the HTML page for the provided query
	 *
	 * @param query - a string representing the desired search query
	 * @return A string containing the HTML of the query result
	 */
	public String getPage(String query) throws IOException;
	
	/**
	 * Gets the search result page count for the provided query
	 *
	 * @param query - a string representing the desired search query
	 * @return The number of results pages for the query
	 */
	public long getPageCount(String query) throws IOException;
}
