/******************************************************************************
*
* This class implements search functionality for the Google engine.
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchImpl_Google implements SearchInterface {

	public static final String searchType = "Google";
	
	// URL parts that make up the full search url
	private String urlBase = "http://www.google.com";
	private String urlSearchSuffix = "/search?";
	
	// the regex pattern to search for in the HTML results string
	private String pageCountPattern = "id=\"resultStats\">About (.*?) results";
	
	//private String userAgent = "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723 Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)";
	private String userAgent = "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6)";
	
	@Override
	public String getPage(String query) throws IOException {
		// open connection to full URL and get returned page
		String urlFull = urlBase;
		urlFull += urlSearchSuffix;
		urlFull += "q=" + URLEncoder.encode(query, "UTF-8");
		
		BufferedReader in = null;
		try {
			URL url = new URL(urlFull);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", userAgent);
			conn.connect();
			InputStream inStream = conn.getInputStream();
			in = new BufferedReader(new InputStreamReader(inStream));
		} catch (IOException e) {
			e.getMessage();
		}
		
        // save returned page into a string
        String page = "";
        String line;
        while ((in != null) && (line = in.readLine()) != null) {
        	page = page + line;
        }
        in.close();
        //System.out.println(page);
        
        return page;
	}
	
	public long getPageCount(String query) throws IOException {
		String page = getPage(query);

		// attempt to find page count pattern in html page
        Pattern pattern = Pattern.compile(pageCountPattern);
        Matcher m = pattern.matcher(page);
        String pageCountStr = "";
        if (m.find()) {
        	pageCountStr = m.group(1);
            //System.out.println(result); 
        }
        
        long pageCount = 0;
        if (!pageCountStr.isEmpty()) {
	        pageCountStr = pageCountStr.replace(",", "");
	        try {
	        pageCount = Long.parseLong(pageCountStr);
	        } catch (NumberFormatException e) {
	        	e.printStackTrace();
	        }
        }
        
        return pageCount;
	}

}
