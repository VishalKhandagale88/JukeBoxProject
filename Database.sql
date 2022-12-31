-- juke box project 
create database jukeBox;
use jukeBox;
create table users(
userid int not null auto_increment primary key ,
username varchar(50) not null,
passwords varchar(10) not null unique
);
select * from users;

create table songs(
songId int not null auto_increment primary key,
durationOfSong varchar(10) not null, -- eg:10:11
SongName varchar(50) not null,
SongPath varchar(500) not null,
songGenre varchar(20) not null,
ArtistName varchar(40) not null,
Album varchar(20) not null
);
select * from Songs ;

create table podcast(
podcastId int auto_increment not null primary key,
podcastName varchar(30) not null
);

select * from podcast;

create table podcastEpisodes(
podcastEpisodeId int not null auto_increment primary key,
DurationOfPodcastEpisode varchar(10) not null,
podcastId int references podcast(podcastId),
EpisodeNumber float not null,
PathOfEpisode varchar(400) not null
);

select * from podcastEpisodes;

create table playlist(
PlaylistId int not null primary key not null,
playlistName varchar(20) not null unique,
userid int references users(userid),
SongId int references songs(SongId),
PodcastId int references podcast(podcastId)
);

select * from playlist;