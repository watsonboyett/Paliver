package com.wb.paliver.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchImpl_Google implements SearchInterface {

	public static final String searchType = "Google";
	
	// URL parts that make up the full search url
	private String urlBase = "http://www.google.com";
	private String urlSearchSuffix = "/search?q=";
	
	// the regex pattern to search for in the HTML results string
	private String pageCountPattern = "id=\"resultStats\">About (.*?) results";
	
	private String userAgent = "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723 Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)";
	
	
	@Override
	public String getPage(String query) throws IOException {
		// open connection to full URL and get returned page
		String urlFull = urlBase + urlSearchSuffix + "\"" + query + "\"";
        URL url = new URL(urlFull);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", userAgent);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        // save returned page into a string
        String page = "";
        String line;
        while ((line = in.readLine()) != null) {
        	page = page + line;
        }
        in.close();
        //System.out.println(page);
        
        return page;
	}
	
	public int getPageCount(String query) throws IOException {
		String page = getPage(query);

		// attempt to find page count pattern in html page
        Pattern pattern = Pattern.compile(pageCountPattern);
        Matcher m = pattern.matcher(page);
        String pageCountStr = "";
        if (m.find()) {
        	pageCountStr = m.group(1);
            //System.out.println(result); 
        }
        
        int pageCount = 0;
        if (!pageCountStr.isEmpty()) {
	        pageCountStr = pageCountStr.replace(",", "");
	        pageCount = Integer.parseInt(pageCountStr);
        }
        
        return pageCount;
	}

}
