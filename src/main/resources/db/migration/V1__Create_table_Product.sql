CREATE TABLE Product(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
	description VARCHAR(255),
	price DECIMAL,
	inventory INT(11)
) engine=InnoDB DEFAULT CHARSET = gbk;