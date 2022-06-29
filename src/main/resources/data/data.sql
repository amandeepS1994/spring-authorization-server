insert into authority (id, authority_name) values 
(1, "READ"), 
(2, "WRITE");

insert into user (id, first_name, last_name, email, username, created_at, password) values
(1, "devel", "oauth", "devel@email.com", "devel", CURDATE(), "Pa$$"),
(2, "developer", "mrdevel", "testDevel@email.com", "devel1", CURDATE(), "Pa$$"),
(3, "oauthdevel", "test", "oauth@email.com", "devel2", CURDATE(), "Pa$$");

insert into user_authority (user_id, authority_id) values
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 2);