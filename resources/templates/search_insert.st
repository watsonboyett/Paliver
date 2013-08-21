search_insert() ::= <<
INSERT INTO search (
	subject,
	subject_id,
	
	topic,
	topic_id,

	time,
	assoc,

	love,
	hate, 
	ambiv,

	want,
	need,
	subsist,

	good,
	evil,
	demean
)
VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?);
>>