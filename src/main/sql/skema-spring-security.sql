create table users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null
);

create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) 
      references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

insert into users values ('adi', '123', 1);
insert into authorities values ('adi', 'ROLE_USER');