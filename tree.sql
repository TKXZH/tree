create database test;

create table file
(
fid int primary key auto_increment,
fsid int not null,
attr int not null,
size int,
parent int
);

 alter table file add constraint foreign key (parent) references file(fid);