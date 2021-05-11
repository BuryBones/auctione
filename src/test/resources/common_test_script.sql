create table role (
  id int not null generated by default as identity,
  role_name varchar(45) not null,
  primary key (id),
  constraint unique_role
    unique (role_name)
);
create table "user" (
  id int not null generated by default as identity,
  login varchar(45) not null,
  email varchar(45) not null,
  firstname varchar(45) not null,
  lastname varchar(45) not null,
  password varchar(90) not null,
  primary key (id),
  constraint unique_login
    unique (login),
  constraint unique_email
    unique (email)
);
create table user_role (
  user_id int not null,
  role_id int not null default 2,
  foreign key (user_id)
  references "user" (id),
  foreign key (role_id)
  references role (id)
);
create table item (
  id int not null generated by default as identity,
  name varchar(45) not null,
  descript varchar(300) not null default 'No description',
  user_id int not null,
  primary key (id),
  foreign key (user_id)
  references "user" (id)
);
create table deal (
  id int not null generated by default as identity,
  user_id int not null,
  item_id int not null,
  init_price number not null,
  open_time timestamp not null default current_timestamp,
  close_time timestamp not null default ((current_timestamp + cast('+0-0' as interval year to month)) + cast('+5 00:00:00.000000000' as interval day(9) to second)),
  status boolean not null default true,
  primary key (id),
  foreign key (user_id)
  references "user" (id),
  foreign key (item_id)
  references item (id)
);
create table bid (
  id int not null generated by default as identity,
  user_id int not null,
  deal_id int not null,
  date_and_time timestamp not null default current_timestamp,
  offer number not null,
  primary key (id),
  foreign key (user_id)
  references "user" (id),
  foreign key (deal_id)
  references deal (id)
);

INSERT INTO role (role_name) VALUES
	('ADMIN'),
	('REGULAR_USER'),
	('TEST_ROLE')
;

INSERT INTO "user" (login, email, firstname, lastname, password) VALUES
	('test1','test1@testmail.com','TEST 1 FIRST','TEST 1 LAST','password1'),
	('test2','test2@testmail.com','TEST 2 FIRST','TEST 2 LAST','password2'),
	('test3','test3@testmail.com','TEST 3 FIRST','TEST 3 LAST','password3'),
	('test4','test4@testmail.com','TEST 4 FIRST','TEST 4 LAST','password4'),
	('test5','test5@testmail.com','TEST 5 FIRST','TEST 5 LAST','password5'),
	('test6','test6@testmail.com','TEST 6 FIRST','TEST 6 LAST','password6'),
	('test7','test7@testmail.com','TEST 7 FIRST','TEST 7 LAST','password7'),
	('test8','test8@testmail.com','TEST 8 FIRST','TEST 8 LAST','password8')
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

insert into deal (user_id, item_id, init_price, open_time, close_time, status)
values (
  4,
  6,
  10000,
  ((current_timestamp - cast('+0-0' as interval year to month)) - cast('+7 00:00:00.000000000' as interval day(9) to second)),
  default,
  false
), (
  5,
  4,
  20000,
  to_date('20210101', 'YYYYMMDD'),
  to_date('20210106', 'YYYYMMDD'),
  false
), (
  7,
  3,
  3400,
  to_date('20210128', 'YYYYMMDD'),
  default,
  true
)
;

insert into bid (user_id, deal_id, date_and_time, offer)
values (
  1,
  1,
  ((current_timestamp - cast('+0-0' as interval year to month)) - cast('+0 03:00:00.000000000' as interval day(9) to second)),
  10000
), (
  8,
  1,
  ((current_timestamp - cast('+0-0' as interval year to month)) - cast('+0 02:00:00.000000000' as interval day(9) to second)),
  10100
), (
  2,
  3,
  ((current_timestamp - cast('+0-0' as interval year to month)) - cast('+0 01:00:00.000000000' as interval day(9) to second)),
  800
), (
  2,
  6,
  ((current_timestamp - cast('+0-0' as interval year to month)) - cast('+0 00:30:00.000000000' as interval day(9) to second)),
  3400
), (
  7,
  2,
  to_date('20210102', 'YYYYMMDD'),
  21000
)
;


