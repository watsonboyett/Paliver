searchResult_insert() ::= <<
INSERT INTO searchResult (
	query,
	time,
	
	subject_id,
	pageCount
)
VALUES (?,?, ?,?)
>>