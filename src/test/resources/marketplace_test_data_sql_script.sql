INSERT INTO role (role_name) VALUES
	('ADMIN'),
	('REGULAR_USER'),
	('TEST_ROLE')
;

INSERT INTO "user" (login,firstname,lastname,password) VALUES
	('test1','TEST 1 FIRST','TEST 1 LAST','password1'),
	('test2','TEST 2 FIRST','TEST 2 LAST','password2'),
	('test3','TEST 3 FIRST','TEST 3 LAST','password3'),
	('test4','TEST 4 FIRST','TEST 4 LAST','password4'),
	('test5','TEST 5 FIRST','TEST 5 LAST','password5'),
	('test6','TEST 6 FIRST','TEST 6 LAST','password6'),
	('test7','TEST 7 FIRST','TEST 7 LAST','password7'),
	('test8','TEST 8 FIRST','TEST 8 LAST','password8')
;

INSERT INTO user_role (user_id, role_id) VALUES 
	(1,1),
	(1,2),
	(2,2),
	(3,2),
	(4,2),
	(5,2),
	(6,2),
	(7,2),
	(8,2)
;

INSERT INTO item (name,user_id,descript) VALUES
	('Test Item 1',1,'description'),
	('Test Item 2',2,DEFAULT),
	('Test Item 3',2,'description'),
	('Test Item 4',2,'description'),
	('Test Item 5',3,'description'),
	('Test Item 6',4,'description'),
	('Test Item 7',4,'description'),
	('Test Item 8',6,DEFAULT)
;

INSERT INTO deal (user_id,item_id,init_price) VALUES
	(2,2,9999.99),
	(2,3,92630),
	(3,5,650)
;

INSERT INTO deal (user_id,item_id,init_price,open_time,status) VALUES
	(4,6,10000,(NOW() - INTERVAL '7 DAY'),false),
	(4,7,20000,TO_DATE('20210101','YYYYMMDD'),false),
	(6,8,3400,TO_DATE('20210128','YYYYMMDD'),true)
;

INSERT INTO bid (user_id,deal_id,date_and_time,offer) VALUES
	(1,1,(NOW() - INTERVAL '3 HOUR'),10000),
	(8,1,(NOW() - INTERVAL '2 HOUR'),10100),
	(2,3,(NOW() - INTERVAL '1 HOUR'),800),
	(2,6,(NOW() - INTERVAL '30 MINUTE'),3400),
	(7,2,TO_DATE('20210102','YYYYMMDD'),21000)
;


