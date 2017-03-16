package com.wb.paliver;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.wb.paliver.data.SearchResult;
import com.wb.paliver.data.SubjectData;
import com.wb.paliver.data.SubjectInfo;

public class Paliver {

	public static void main(String[] args) {
		try {
			String dbType = "DerbyEmbedded";
			DbApi dbApi = new DbApi(dbType);
			
			String searchType = "Bing";
			SearchApi searchApi = new SearchApi(searchType);
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			String timestampStr = dateFormat.format(timestamp);
			String dbName;
			
			// dbName = "//localhost/db_test?user=root";
			dbName = "E:/home/projects/Paliver/data/DbTest_" + timestampStr;
			dbApi.createDb(dbName);

			
			//String[] subjects = PaliverSubjects.Subjects;
			String[] subjects = MarchMadness2017.Subjects;

			for (int subjectIter = 0; subjectIter < subjects.length; subjectIter++)
			{
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

				
				String[] queryBase = { "",
						"i love ", "i hate ",
						"i want ", "i need ",
						" is good", " is bad" };

				for (int queryIter = 0; queryIter < queryBase.length; queryIter++)
				{
					SearchResult sr = new SearchResult();

					String query = "";
					if (queryIter < 5)
					{
						query = queryBase[queryIter] + subjectCurr;
					} else {
						query = subjectCurr + queryBase[queryIter];
					}

					query = "\"" + query + "\"";
					
					
					long pageCount = -1;
					try
					{
						pageCount = searchApi.getPageCount(query);
					} catch (IOException e) {
						e.printStackTrace();
					}

					sr.query = query;
					sr.time = new Timestamp(System.currentTimeMillis());
					sr.subject_id = subject_id;
					sr.pageCount = pageCount;
					dbApi.saveSearchResult(sr);

					
					switch (queryIter) 
					{
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

					System.out.println("Query:" + query + " | Hit count: " + pageCount);
					Thread.sleep(10000);
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
