package com.wb.paliver.search;

import java.io.IOException;

public interface SearchInterface {

	public String getPage(String query) throws IOException;
	
	public int getHitCount(String query) throws IOException;
}
