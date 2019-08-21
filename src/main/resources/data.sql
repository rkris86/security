INSERT INTO users(username,password,enabled)
VALUES ('ram','$2a$10$yEKcA8joeckzLzZpbEfjiuOY06SZl6NmXf3qCqTY9kqziubLG2zxy', true);
INSERT INTO users(username,password,enabled)
VALUES ('alex','$2a$10$ntU2ZDAoRfKDJv92vOsMUe/f/TSECV41jgVyl1Kg5DetnuqU7GgNS', true);

INSERT INTO user_roles (username, role)
VALUES ('ram', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('ram', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');

insert into USER(NAME,EMAIL,USERNAME,PASSWORD) values ( 'Ram','ram@ram.com','ram','123456');