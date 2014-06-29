topicInfo_create_derby() ::= <<
CREATE TABLE topicInfo
(
	topic_id BIGINT GENERATED ALWAYS AS IDENTITY,
	topic VARCHAR (64),
	info VARCHAR(512),

	PRIMARY KEY (topic_id)
)
>>