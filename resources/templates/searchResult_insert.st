searchResult_insert() ::= <<
INSERT INTO searchResult (
(
	query VARCHAR (512) NOT NULL,
	time TIMESTAMP,
	
	subject_id BIGINT,
	pageCount BIGINT
)
VALUES (?,?, ?,?)
>>