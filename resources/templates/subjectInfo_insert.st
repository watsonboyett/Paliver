subjectInfo_insert() ::= <<
INSERT INTO subjectInfo (
	subject,
	info,
	
	topic_id
)
VALUES (?,?, ?)
>>