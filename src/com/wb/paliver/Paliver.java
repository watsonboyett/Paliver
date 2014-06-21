package com.wb.paliver;


import com.wb.paliver.data.SubjectData;

public class Paliver {

	public static void main(String[] args) {
		DbApi dbApi = new DbApi();
		
		try {
			String dbType = "Derby"; 
			String dbName;
			
			if (dbType.equalsIgnoreCase("MySQL")) {
				dbName = "//localhost/db_test?user=root";
			} else {
				dbName = "db_test";
			}
			
			dbApi.createDb(dbName);

			// do some stuff
			
			dbApi.closeDb();
		} catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	ex.printStackTrace();
		}
	}
	


}
