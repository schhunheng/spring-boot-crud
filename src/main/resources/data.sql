
INSERT INTO users(username,password,enabled) VALUES (
    'user',
    'password',
    true
);


INSERT INTO users(username,"password",enabled) values (
    'admin',
    'password',
    true
);

INSERT INTO users(username,password,enabled) values (
    'test',
    'password',
    false
);

INSERT INTO authorities(username,authority) VALUES(
    'user',
    'ROLE_USER'
);
INSERT INTO authorities(username,authority) VALUES(
    'admin',
    'ROLE_ADMIN'
);
