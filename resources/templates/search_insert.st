search_insert() ::= <<
INSERT INTO search (
	subject_id,

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
VALUES (?, ?,?, ?,?,?, ?,?,?, ?,?,?)
>>