INSERT INTO role (role_name) VALUES
	('ADMIN'),
	('REGULAR_USER')
;

INSERT INTO "user" (login,firstname,lastname,password) VALUES
	('ivan1990','Ivan','Ivanov','12345678'),
	('alex777','Alexander','Kalushenko','111$$$abc'),
	('g30rge','George','Migello','pass1'),
	('maxxx','Max','Dicker','acbdefg'),
	('Just_John','John','Rambo','the$trongPa$$w0rd'),
	('JacobsMonarch','Jacob','Potti','123heheheh'),
	('flowerHelen','Helen','Stone','helenhelen'),
	('andrew25','Andrew','Smirnoff','somePassword1111'),
	('testuser','test','test','qwerty123'),
	('boris12','Boris','Veltsin','boriska1205')
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
	(8,2),
	(9,1),
	(9,2),
	(10,2)
;

INSERT INTO item (name,user_id,descript) VALUES
	('Колесо от кировца',1,'Б\У колесо от трактора "Кировец", использовалось 40 лет'),
	('Лопасть вертолёта МИ-8',3,DEFAULT),
	('Глобус Украины',7,'Круглый'),
	('Пуговица Пушкина',5,'Толстовка Толстого в подарок!'),
	('Статуя Чапаева',2,'масштаб 1/4'),
	('Бюст Эраста П. Фандорина',4,'Гипсовый'),
	('Набор фишек с покемонами',7,'104 шт.'),
	('Колода карт (52) Fallout',7,DEFAULT),
	('Necronomicon',9,DEFAULT)
;

INSERT INTO deal (user_id,item_id,init_price,open_time,close_time,status) VALUES
	(7,7,5000,NOW(),(NOW() + INTERVAL '7 DAY'),true),
	(2,5,9999.99,NOW(),(NOW() + INTERVAL '1 DAY'),true),
	(3,2,92630,NOW(),(NOW() + INTERVAL '3 DAY'),true),
	(1,1,650,NOW(),(NOW() + INTERVAL '5 DAY'),true),
	(7,8,2499.99,NOW(),(NOW() + INTERVAL '10 DAY'),true),
	(4,6,10000,(NOW() - INTERVAL '7 DAY'),(NOW() - INTERVAL '5 DAY'),false),
  (5,4,20000,TO_DATE('20210101','YYYYMMDD'),TO_DATE('20210104','YYYYMMDD'),false),
  (7,3,3400,TO_DATE('20210128','YYYYMMDD'),TO_DATE('202102015','YYYYMMDD'),false)
;

INSERT INTO bid (user_id,deal_id,date_and_time,offer) VALUES
	(6,4,(NOW() - INTERVAL '3 HOUR'),700),
	(5,2,(NOW() - INTERVAL '2 HOUR'),10100),
	(2,4,(NOW() - INTERVAL '1 HOUR'),800),
	(6,4,(NOW() - INTERVAL '30 MINUTE'),1000)
;


