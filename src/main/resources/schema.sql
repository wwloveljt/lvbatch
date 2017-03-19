create table person (
id bigint(19) not null primary key, 
area varchar(255),
bj  varchar(255),
city  varchar(255), 
grade  varchar(255), 
name  varchar(255), 
patriarch  varchar(255),
patriarch_tel  varchar(255), 
school  varchar(255), 
state boolean(1) default 0, 
teacher  varchar(255),
teacher_tel  varchar(255),
zkzh  varchar(255) default 0,
kc varchar(50) ,
zs varchar(50),
kssj varchar(50),
kd varchar(50)
);

create sequence hibernate_sequence;