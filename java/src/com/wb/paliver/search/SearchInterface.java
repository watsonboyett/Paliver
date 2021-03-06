/******************************************************************************
*
* This class provides an interface to different search engines and methods.
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
