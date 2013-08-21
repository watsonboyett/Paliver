search_create() ::= <<
CREATE TABLE search
(
	id BIGINT NOT NULL AUTO_INCREMENT,

	subject VARCHAR (64),
	subject_id BIGINT NOT NULL,
	
	topic VARCHAR (64),
	topic_id BIGINT NOT NULL,

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

	PRIMARY KEY (id)
);
>>