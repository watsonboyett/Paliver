searchResult_create_derby() ::= <<
CREATE TABLE searchResult
(
	search_id BIGINT GENERATED ALWAYS AS IDENTITY,
	query VARCHAR (512) NOT NULL,
	time TIMESTAMP,
	
	subject_id BIGINT,
	pageCount BIGINT,
	
	PRIMARY KEY (search_id)
)
>>