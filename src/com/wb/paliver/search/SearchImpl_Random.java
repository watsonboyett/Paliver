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
