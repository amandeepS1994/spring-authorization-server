drop table if exists user_authority;
drop table if exists authority;
drop table if exists user;


create table authority (
    id bigint NOT NULL AUTO_INCREMENT,
    authority_name char(50) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT un_auth UNIQUE (authority_name)
);

create table user (
    id bigint NOT NULL AUTO_INCREMENT, 
    first_name varchar(25),
    last_name varchar(25),
    email varchar(25),
    username varchar(25),
    created_at DATETIME,
    password varchar(25), 
    CONSTRAINT un_username UNIQUE(username),
    CONSTRAINT un_email UNIQUE (email),
    CONSTRAINT pk_id PRIMARY KEY (id)
);

create table user_authority (
    user_id bigint NOT NULL,
    authority_id bigint NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (user_id, authority_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) references user(id),
    CONSTRAINT fk_authoriy_id FOREIGN KEY (authority_id) references authority(id)
);




