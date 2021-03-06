-- master 库
CREATE DATABASE IF NOT EXISTS master;

USE master;
CREATE TABLE IF NOT EXISTS account(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	money DECIMAL(10,2) NOT NULL,
	PRIMARY key(id)
);
insert into account(name,phone,money) values('master-张三','1829999999',12.12),('master-李四','1829999999',12.12);
-- slave1 库
CREATE DATABASE IF NOT EXISTS slave1;

USE slave1;
CREATE TABLE IF NOT EXISTS account(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	money DECIMAL(10,2) NOT NULL,
	PRIMARY key(id)
);
insert into account(name,phone,money) values('slave1-张三','1829999999',12.12),('slave1-李四','1829999999',12.12);

-- slave2 库
CREATE DATABASE IF NOT EXISTS slave2;

USE slave2;
CREATE TABLE IF NOT EXISTS account(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	money DECIMAL(10,2) NOT NULL,
	PRIMARY key(id)
);
insert into account(name,phone,money) values('slave2-张三','1829999999',12.12),('slave2-李四','1829999999',12.12);