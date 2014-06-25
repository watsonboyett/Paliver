/******************************************************************************
*
* This class implements a random data result search engine (for testing only).
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
import java.math.BigInteger;
import java.util.Random;


public class SearchImpl_Random implements SearchInterface {

	public static final String searchType = "Random";

	@Override
	public String getPage(String query) throws IOException {
		final Random random = new Random();
		return new BigInteger(64, random).toString();        
	}
	
	public long getPageCount(String query) throws IOException {
		final Random random = new Random();
        return random.nextInt(999999999);
	}

}
