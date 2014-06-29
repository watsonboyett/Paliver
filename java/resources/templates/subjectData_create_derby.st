subjectData_create_derby() ::= <<
CREATE TABLE subjectData
(
	search_id BIGINT GENERATED ALWAYS AS IDENTITY,
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