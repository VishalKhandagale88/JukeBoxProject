-- juke box project 
create database jukeBox;
use jukeBox;
create table users(
userid int not null auto_increment primary key ,
username varchar(50) not null,
passwords varchar(10) not null
);
select * from users;
select songid,songname from songs where songid=1;
select * from users where username='vishal';
desc users;
