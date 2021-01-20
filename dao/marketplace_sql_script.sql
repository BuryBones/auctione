CREATE DATABASE online_marketplace
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE "user" (
	id SERIAL,
	login VARCHAR(45) NOT NULL,
	firstname VARCHAR(45) NOT NULL,
	lastname VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT unique_login UNIQUE(login)
);

CREATE TABLE item (
	id SERIAL,
	name VARCHAR(45) NOT NULL,
	descript VARCHAR(300) NOT NULL DEFAULT ('No description'),
	PRIMARY KEY (id)
);

CREATE TABLE user_item (
	user_id INT NOT NULL,
	item_id INT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES "user"(id),
	FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE deal (
	id SERIAL,
	user_id INT NOT NULL,
	item_id INT NOT NULL,
	init_price NUMERIC NOT NULL,
	open_time TIMESTAMP NOT NULL DEFAULT(NOW()),
	status BOOLEAN NOT NULL DEFAULT(TRUE),
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES "user"(id),
	FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE bid (
	id SERIAL,
	user_id INT NOT NULL,
	deal_id INT NOT NULL,
	date_and_time TIMESTAMP NOT NULL DEFAULT(NOW()),
	offer NUMERIC NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES "user"(id),
	FOREIGN KEY (deal_id) REFERENCES deal(id)
);