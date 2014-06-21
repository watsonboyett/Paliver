subjectData_insert() ::= <<
INSERT INTO subjectData (
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