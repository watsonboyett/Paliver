/******************************************************************************
*
* This API provides a convenient facade to the underlying search functionality.
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


import java.io.IOException;

import com.wb.paliver.search.SearchImpl_Google;
import com.wb.paliver.search.SearchImpl_Random;
import com.wb.paliver.search.SearchInterface;

public class SearchApi {

	SearchInterface si;
	
	/**
	 * Default constructor for the Search API
	 */
	public SearchApi() {
		
		si = new SearchImpl_Google();
	}
	
	/**
	 * Overloaded constructor for the Search API
	 * 
	 * @param searchType
	 *            - a string representing the desired backing search engine (e.g.
	 *            Google, Bing, Twitter, etc.)
	 */
	public SearchApi(String searchType) {
		
		// TODO: make this a factory class/method
		if (searchType.equals(SearchImpl_Google.searchType)) {
			si = new SearchImpl_Google();
		} else if (searchType.equals(SearchImpl_Random.searchType)) {
				si = new SearchImpl_Random();
		} else {
			System.out.println("Invalid search type!");
		}
	}
	
	
	
	public String getPage(String query) throws IOException {
		return si.getPage(query);
	}
	
	public long getPageCount(String query) throws IOException {
		return si.getPageCount(query);
	}
}
