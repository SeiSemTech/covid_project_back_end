
insert into role(id,name) VALUES(1,'ROLE_ADMIN');
insert into role(id,name) VALUES(2,'ROLE_CLIENT');
insert into role(id,name) VALUES(3,'ROLE_USER');

insert into privilege(id,name) VALUES(1,'administador');
insert into privilege(id,name) VALUES(2,'usuario');
insert into usuario(id, name, lastname, username, password, document, creation_date, state) VALUES(1,'name', 'lastname', 'admin', 'admin', 123456, '9999-12-31T05:00:00.000+00:00', true);
