insert into authority (id, authority_name) values 
(1, "READ"), 
(2, "WRITE");

insert into user (id, first_name, last_name, email, username, created_at, password) values
(1, "devel", "oauth", "devel@email.com", "devel", CURDATE(), "Pa$$"),
(2, "developer", "mrdevel", "testDevel@email.com", "devel1", CURDATE(), "Pa$$"),
(3, "oauthdevel", "test", "oauth@email.com", "devel2", CURDATE(), "Pa$$");

insert into client (id, username, secret, created_at) values
(1, "client1", "secret", CURDATE()),
(2, "client2", "secretzz", CURDATE()),
(3, "client3", "topsecret", CURDATE());

insert into grant_type (id, name) values 
(1, "AUTHORIZATION_CODE"),
(2, "PASSWORD"),
(3, "REFRESH_TOKEN");


insert into scope (id, name) values (1, "READ"), (2, "WRITE");

insert into client_grant_type (client_id, grant_id) values
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(3, 1);

insert into client_scope (client_id, scope_id) values
(1, 1),
(2, 1),
(3, 1);

insert into user_authority (user_id, authority_id) values
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 2);

