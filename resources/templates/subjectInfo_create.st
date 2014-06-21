subjectInfo_create() ::= <<
CREATE TABLE subjectInfo
(
	subject_id BIGINT NOT NULL AUTO_INCREMENT,
	subject VARCHAR (64),
	info VARCHAR (512),

	topic_id BIGINT NOT NULL,

	PRIMARY KEY (subject_id)
)
>>