package com.wb.paliver.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchImpl_Google implements SearchInterface {

	@Override
	public String getPage(String query) throws IOException {
		String urlName = "http://www.google.com/search?q=\"" + query + "\"";
        URL url = new URL(urlName);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723 Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String page = "";
        String line;
        while ((line = in.readLine()) != null) {
        	page = page + line;
        }
        in.close();
        //System.out.println(page);
        
        return page;
	}
	
	public int getHitCount(String query) throws IOException {
		String page = getPage(query);

        Pattern pattern = Pattern.compile("id=\"resultStats\">About (.*?) results"); //About 1,620,000 results
        Matcher m = pattern.matcher(page);
        String pageCountStr = "";
        if (m.find()) {
        	pageCountStr = m.group(1);  // m.group(1) corresponds to results number: i.e.: 1,620,000
            //System.out.println(result); 
        }
        pageCountStr = pageCountStr.replace(",", "");
        int pageCount = Integer.parseInt(pageCountStr);

        return pageCount;
	}

}
