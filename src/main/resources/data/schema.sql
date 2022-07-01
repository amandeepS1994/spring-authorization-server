drop table if exists user_authority;
drop table if exists authority;
drop table if exists user;
drop table if exists client_grant_type;
drop table if exists client_scope;
drop table if exists client;
drop table if exists grant_type;
drop table if exists scope;


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
    password varchar(255), 
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


create table client (
    id bigint NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    secret varchar(50) NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT un_username UNIQUE (username)
);

create table grant_type (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT un_name UNIQUE(name)
);

create table scope (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT un_scope UNIQUE (name)
);

create table client_grant_type (
    client_id bigint NOT NULL,
    grant_id bigint NOT NULL,
    CONSTRAINT pk_cid_gid PRIMARY KEY (client_id, grant_id),
    CONSTRAINT fk_cgtcid FOREIGN KEY (client_id) references client(id),
    CONSTRAINT fk_cgtgid FOREIGN KEY (grant_id) references grant_type(id)

);

create table client_scope (
    client_id bigint NOT NULL,
    scope_id bigint NOT NULL,
    CONSTRAINT pk_cid PRIMARY KEY (client_id, scope_id),
    CONSTRAINT fk_cscid FOREIGN KEY (client_id) references client(id),
    CONSTRAINT fk_cdgid FOREIGN KEY (scope_id) references scope(id)
);


