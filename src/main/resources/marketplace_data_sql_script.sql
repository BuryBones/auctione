INSERT INTO role (role_name) VALUES
	('ADMIN'),
	('USER')
;

INSERT INTO "user" (login,email,firstname,lastname,password) VALUES
	('ivan1990','ivanov@gmail.com','Ivan','Ivanov','{bcrypt}$2a$04$UBv4irSE2Wsf1gO53XKhm.hfsHH91C5dNLi.PBrQ6L./RGSEvLk4m'),
	('alex777','sasha.kalush@mail.ru','Alexander','Kalushenko','{bcrypt}$2a$04$I9N9mTb6R0SXi9ErvJ4.aeHSmbDk/r.z8j2RKvJLQ5Gz/xOch4FM6'),
	('g30rge','gm30@bk.ru','George','Migello','{bcrypt}$2a$04$v.5LeazBqSY85ZRfGQwA4e984outIs/3ajRNbNw8X7N4qLSv4zKQa'),
	('maxxx','dicker.max@serious.ge','Max','Dicker','{bcrypt}$2a$04$elfzeW5AKmOoVJU4i6Pvr.WKpqPSX/Yy5f6oiRbkGKrHsgd5geRu6'),
	('Just_John','firstblood@pat.us','John','Rambo','{bcrypt}$2a$04$ID8c.JlnW9pGR11m3woxMu1JpzC8wp2XQeq49CcYnj8HTEWedxXm6'),
	('JacobsMonarch','monarch@coffee.se','Jacob','Potti','{bcrypt}$2a$04$491zLOqFKAeVysgjnH5XROGPlR1UL5QDAPOSiQY.1b5f9xoCP8of6'),
	('flowerHelen','flowerhelen@mail.ru','Helen','Stone','{bcrypt}$2a$04$8DC1CD8tbXvYqlDAtzHMPuSj.w1pMkdMXVSEvVrE/ZritXH7b85Pi'),
	('andrew25','an.smirnov@yandex.ru','Andrew','Smirnoff','{bcrypt}$2a$04$guQd3HUA2nTL30W7Vg9kBuOCSumGVCwLZrfE2ttamBss/QHG/fEki'),
	('mike12011990','mike.jee@mail.com','Mike','Jimmiling','{bcrypt}$2a$04$QSR.I73c.wtczRztgkLYWeA97iZLjfjs6nx/jSMH7GFymi1GG0L2a'),
	('boris12','boriska-v@rabler.ru','Boris','Veltsin','{bcrypt}$2a$04$5zVyBZiKd0FNJ4ioeaGQvOGGPB1.mWuLUjFVBo3Y5u1ZZvCcMtiWy')
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
	('Necronomicon',9,DEFAULT),
	('Dead Space Marker Figure',9,'34 cm height. Make us the whole!'),
	('iPhone 2G',10,'Sunk in the river 10 years ago')
;

INSERT INTO deal (user_id,item_id,init_price,open_time,close_time) VALUES
	(7,7,5000,NOW(),(NOW() + INTERVAL '7 DAY')),
	(2,5,9999.99,NOW(),(NOW() + INTERVAL '1 DAY')),
	(3,2,92630,NOW(),(NOW() + INTERVAL '3 DAY')),
	(1,1,650,NOW(),(NOW() + INTERVAL '5 DAY')),
	(7,8,2499.99,NOW(),(NOW() + INTERVAL '10 DAY')),
	(4,6,10000,(NOW() - INTERVAL '7 DAY'),(NOW() - INTERVAL '5 DAY')),
  (5,4,20000,TO_DATE('20210101','YYYYMMDD'),TO_DATE('20210104','YYYYMMDD')),
  (7,3,3400,TO_DATE('20210128','YYYYMMDD'),TO_DATE('202102015','YYYYMMDD')),
  (9,9,14999,(NOW() - INTERVAL '3 DAY'),(NOW() + INTERVAL '2 MINUTE')),
  (9,10,8300,(NOW() - INTERVAL '2 DAY'),(NOW() + INTERVAL '3 MINUTE')),
  (10,11,1200,(NOW() - INTERVAL '6 HOUR'),(NOW() + INTERVAL '3 DAY'))
;

INSERT INTO bid (user_id,deal_id,date_and_time,offer) VALUES
	(6,4,(NOW() - INTERVAL '3 HOUR'),700),
	(5,2,(NOW() - INTERVAL '2 HOUR'),10100),
	(2,4,(NOW() - INTERVAL '1 HOUR'),800),
	(6,4,(NOW() - INTERVAL '30 MINUTE'),1000),
	(1,10,(NOW() - INTERVAL '1 DAY'),8350),
	(3,11,(NOW() - INTERVAL '1 HOUR'),1201)
;