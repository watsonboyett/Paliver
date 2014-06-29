/******************************************************************************
*
* This class is a low-level data container for search result information.
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

package com.wb.paliver.data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Random;


public class SearchResult {

	public long search_id = -1;
	public String query = "";

	public Timestamp time = null;

	public long subject_id = -1;
	public long pageCount = -1;
	
	
	public boolean compareTo(SearchResult other) {
		boolean isEqual = true;
		
		isEqual &= (this.query == other.query);
		isEqual &= (this.time == other.time);
		
		isEqual &= (this.subject_id == other.subject_id);
		isEqual &= (this.pageCount == other.pageCount);

		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		this.query = tester;
		this.time = new Timestamp(System.currentTimeMillis());
		
		this.subject_id = random.nextLong();
		this.pageCount = random.nextLong();
	}
}
