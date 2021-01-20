INSERT INTO "user" (login,firstname,lastname,password) VALUES
	('ivan1990','Ivan','Ivanov','12345678'),
	('alex777','Alexander','Kalushenko','111$$$abc'),
	('g30rge','George','Migello','pass1'),
	('maxxx','Max','Dicker','acbdefg'),
	('Just_John','John','Rambo','the$trongPa$$w0rd'),
	('JacobsMonarch','Jacob','Potti','123heheheh'),
	('flowerHelen','Helen','Stone','helenhelen'),
	('andrew25','Andrew','Smirnoff','somePassword1111')
;

INSERT INTO item (name,descript) VALUES
	('Колесо от кировца','Б\У колесо от трактора "Кировец", использовалось 40 лет'),
	('Лопасть вертолёта МИ-8',DEFAULT),
	('Глобус Украины','Круглый'),
	('Пуговица Пушкина','Толстовка Толстого в подарок!'),
	('Статуя Чапаева','масштаб 1/4'),
	('Бюст Эраста П. Фандорина','Гипсовый'),
	('Набор фишек с покемонами','104 шт.'),
	('Колода карт (52) Fallout',DEFAULT)
;

INSERT INTO user_item (user_id,item_id) VALUES
	(1,1),
	(7,3),
	(2,5),
	(3,2),
	(5,4),
	(4,6),
	(7,7),
	(7,8)
;

INSERT INTO deal (user_id,item_id,init_price) VALUES
	(7,7,5000),
	(2,5,9999.99),
	(3,2,92630),
	(1,1,650),
	(7,8,2499.99)
;

INSERT INTO deal (user_id,item_id,init_price,open_time,status) VALUES
	(4,6,10000,(NOW() - INTERVAL '2 DAY'),false)
;

INSERT INTO bid (user_id,deal_id,date_and_time,offer) VALUES
	(6,4,(NOW() - INTERVAL '3 HOUR'),700),
	(5,2,(NOW() - INTERVAL '2 HOUR'),10100),
	(2,4,(NOW() - INTERVAL '1 HOUR'),800),
	(6,4,(NOW() - INTERVAL '30 MINUTE'),1000)
;


