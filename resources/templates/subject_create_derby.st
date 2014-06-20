subject_create_derby() ::= <<
CREATE TABLE subject
(
	subject_id BIGINT GENERATED ALWAYS AS IDENTITY,
	subject VARCHAR (64),
	info VARCHAR (512),

	topic_id BIGINT NOT NULL,

	PRIMARY KEY (subject_id)
)
>>