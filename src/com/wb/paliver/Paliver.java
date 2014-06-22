package com.wb.paliver;

import java.io.IOException;
import java.sql.Timestamp;

import com.wb.paliver.data.SearchResult;
import com.wb.paliver.data.SubjectData;
import com.wb.paliver.data.SubjectInfo;

public class Paliver {

	public static void main(String[] args) {
		DbApi dbApi = new DbApi();
		SearchApi searchApi = new SearchApi();

		try {
			String dbType = "Derby";
			String dbName;

			if (dbType.equalsIgnoreCase("MySQL")) {
				dbName = "//localhost/db_test?user=root";
			} else {
				dbName = "db_test";
			}

			dbApi.createDb(dbName);

			String[] subjects = { "god", "satan", "atheists", "girls", "boys",
					"pirates", "ninjas", "zombies", "wizards", "ghosts",
					"dragons", "science", "math", "books", "gym", "english",
					"history", "reading" };

			for (int subjectIter = 0; subjectIter < subjects.length; subjectIter++) {
				String subjectCurr = subjects[subjectIter];
				SubjectInfo si = new SubjectInfo();
				si.subject = subjectCurr;
				si.info = "";

				dbApi.saveSubjectInfo(si);
				long subject_id = dbApi
						.getSubjectInfo("select * from subjectInfo where subject = '"
								+ subjectCurr + "'").subject_id;

				SubjectData sd = new SubjectData();
				sd.subject_id = subject_id;

				
				String[] queryBase = { "", "i love ", "i hate ", "i want ",
						"i need ", " is good", " is bad" };

				for (int queryIter = 0; queryIter < queryBase.length; queryIter++) {
					SearchResult sr = new SearchResult();

					String query = "";
					if (queryIter < 5) {
						query = queryBase[queryIter] + subjectCurr;
					} else {
						query = subjectCurr + queryBase[queryIter];
					}

					query = "\"" + query + "\"";
					
					
					int pageCount = -1;
					try {
						pageCount = searchApi.getPageCount(query);
					} catch (IOException e) {
						e.printStackTrace();
					}

					sr.query = query;
					sr.time = new Timestamp(System.currentTimeMillis());
					sr.subject_id = subject_id;
					sr.pageCount = pageCount;
					dbApi.saveSearchResult(sr);

					
					switch (queryIter) {
						case 0:
							sd.assoc = pageCount;
							break;
						case 1:
							sd.love = pageCount;
							break;
						case 2:
							sd.hate = pageCount;
							break;
						case 3:
							sd.want = pageCount;
							break;
						case 4:
							sd.need = pageCount;
							break;
						case 5:
							sd.good = pageCount;
							break;
						case 6:
							sd.evil = pageCount;
							break;
					}

					System.out.println("Hit count: " + pageCount);
					Thread.sleep(1000);
				}

				sd.updateAmbiv();
				sd.updateSubsist();
				sd.updateDemean();
				sd.time = new Timestamp(System.currentTimeMillis());
				dbApi.saveSubjectData(sd);
			}

			dbApi.closeDb();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
