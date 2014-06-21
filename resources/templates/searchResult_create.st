searchResult_create_derby() ::= <<
CREATE TABLE searchResult
(
	search_id BIGINT AUTO_INCREMENT,
	query VARCHAR (512) NOT NULL,
	time TIMESTAMP,
	
	subject_id BIGINT,
	pageCount BIGINT,
	
	PRIMARY KEY (search_id)
)
>>