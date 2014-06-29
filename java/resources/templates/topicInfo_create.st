topicInfo_create() ::= <<
CREATE TABLE topicInfo
(
	topic_id BIGINT NOT NULL AUTO_INCREMENT,
	topic VARCHAR (64),
	info VARCHAR(512),

	PRIMARY KEY (topic_id)
)
>>