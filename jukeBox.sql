-- juke box project 
create database jukeBox;
use jukeBox;
create table users(
userid int not null auto_increment primary key ,
username varchar(50) not null,
passwords varchar(10) not null
);
select * from users;

