DROP TABLE IF EXISTS users;
CREATE  TABLE users (
                        username VARCHAR(45) NOT NULL ,
                        password VARCHAR(100) NOT NULL ,
                        enabled TINYINT NOT NULL DEFAULT 1 ,
                        PRIMARY KEY (username));

DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      NAME VARCHAR(250) NOT NULL,
                      EMAIL VARCHAR(250) NOT NULL,
                      USERNAME VARCHAR(250) NOT NULL,
                      PASSWORD VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS user_roles;


CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  );

