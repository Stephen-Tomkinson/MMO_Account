DROP TABLE IF EXISTS `account` CASCADE;
CREATE TABLE account (
	id BIGINT AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	level INTEGER NOT NULL,
	region CHAR(2) NOT NULL,
	PRIMARY KEY (id)
);