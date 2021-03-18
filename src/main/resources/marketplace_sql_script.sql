CREATE TABLE role (
	id SERIAL,
	role_name VARCHAR(45) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT unique_role UNIQUE(role_name)
);

CREATE TABLE "user" (
	id SERIAL,
	login VARCHAR(45) NOT NULL,
	email VARCHAR(45) NOT NULL,
	firstname VARCHAR(45) NOT NULL,
	lastname VARCHAR(45) NOT NULL,
	password VARCHAR(90) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT unique_login UNIQUE(login),
	CONSTRAINT unique_email UNIQUE(email)
);

CREATE TABLE user_role (
	user_id INT NOT NULL,
	role_id INT NOT NULL DEFAULT 2,
	FOREIGN KEY (user_id) REFERENCES "user"(id),
	FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE item (
	id SERIAL,
	name VARCHAR(45) NOT NULL,
	descript VARCHAR(300) NOT NULL DEFAULT ('No description'),
	user_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE deal (
	id SERIAL,
	user_id INT NOT NULL,
	item_id INT NOT NULL,
	init_price NUMERIC NOT NULL,
	open_time TIMESTAMP NOT NULL DEFAULT(NOW()),
	close_time TIMESTAMP NOT NULL DEFAULT(NOW() + INTERVAL '5 DAY'),
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