
CREATE DATABASE IF NOT EXISTS jdbc_test_db;

USE jdbc_test_db;
CREATE TABLE IF NOT EXISTS account(
	id INT UNSIGNED NOT NULL,
	name VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	money DECIMAL(10,2) NOT NULL,
	PRIMARY key(id)
);