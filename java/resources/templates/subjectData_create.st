subjectData_create() ::= <<
CREATE TABLE subjectData
(
	search_id BIGINT NOT NULL AUTO_INCREMENT,
	subject_id BIGINT NOT NULL,

	time TIMESTAMP,
	assoc double,

	love double,
	hate double, 
	ambiv double,

	want double,
	need double,
	subsist double,

	good double,
	evil double,
	demean double,
	
	PRIMARY KEY (search_id)
)
>>