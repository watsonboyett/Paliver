topic_create() ::= <<
CREATE TABLE topic
(
	id BIGINT NOT NULL AUTO_INCREMENT,

	topic VARCHAR (64),
	topic_id BIGINT NOT NULL UNIQUE,
	
	descrip VARCHAR(512),

	PRIMARY KEY (id)
);
>>