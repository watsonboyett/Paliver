subject_create() ::= <<
CREATE TABLE subject
(
	id BIGINT NOT NULL AUTO_INCREMENT,

	subject VARCHAR (64),
	subject_id BIGINT NOT NULL UNIQUE,
	
	descrip VARCHAR (512),

	PRIMARY KEY (id)
);
>>