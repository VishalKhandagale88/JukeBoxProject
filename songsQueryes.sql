
use jukebox;
/*
create table songs(
songId int not null auto_increment primary key,
durationOfSong varchar(10) not null, -- eg:10:11
SongName varchar(50) not null,
SongPath varchar(500) not null,
songGenre varchar(20) not null,
ArtistName varchar(40) not null,
Album varchar(20) not null

D:\stack route\JAVA project\songs wav file\classical\Raag.wav
*/
-- ******************query for songs table************
-- classical genere songs
insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('06:48','A Fusion In Raag Das',"D:\stack route\JAVA project\songs wav file\classical\Raag.wav",'classical','Paras Nath','classical music');
insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('03:37','Sweet Dreams(Are made of this)','D:\\stack route\\JAVA project\\songs wav file\\classical\\SweetDreams.wav','classical','Eurythmics','classical song');
select * from songs;
update songs set SongPath='D:\\stack route\\JAVA project\\songs wav file\\classical\\Raag.wav' where songId=1;

-- indian cinema genere

insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('02:45','ide kadha ne katha','D:\\stack route\\JAVA project\\songs wav file\\Indian cinema\\ide_kada.wav','Indian cinema','vijay prakash','Maharshi');

insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('04:25','Rasiya','D:\\stack route\\JAVA project\\songs wav file\\Indian cinema\\Rasiya.wav','Indian cinema','Arjit singh','Bramhastra');

-- jazz
insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('01:42','jazz music','"D:\\stack route\\JAVA project\\songs wav file\\Jazz\\jazzz.wav','jazz','Lenin','jazz band');

insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('03:46','nightslife','D:\\stack route\\JAVA project\\songs wav file\\Jazz\\nightlife.wav','jazz','Annie wells','Dark');

select * from songs;

-- pop
insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('03:54','A_waqt_ruk','D:\\stack route\\JAVA project\\songs wav file\\pop\\AWaqt.wav','pop','Soham Naik','Awaqt_ruk');

insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('03:49','This_is_what','D:\\stack route\\JAVA project\\songs wav file\\pop\\ThisIsWhat.wav','pop','Calvin Harris','Calv');

-- Rock 
insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('02:51','Friends','D:\\stack route\\JAVA project\\songs wav file\\rock\\Boy.wav','Rock','DJ Gotta','Charley');
insert into songs(durationOfSong,SongName,SongPath,songGenre,ArtistName,Album)
values('03:55','Heat wave','D:\\stack route\\JAVA project\\songs wav file\\rock\\HeatWave.wav','Rock','Glass Animals','Dave');


select songid,songname from songs;
select * from songs;


select songid,ArtistName from  Songs;
select songid,album from  Songs;
select songid,album from songs;
select songid,songGenre from songs;
select * from songs;

update songs set songname='Sweet Dreams' where songid= 2;
/*
"D:stack routeJAVA projectjukebox-projectsongs wav fileclassicalRaag.wav"
D:\stack route\JAVA project\songs wav file\Indian cinema\Rasiya.wav
*/
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\classical\\Raag.wav' where songid=1;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\classical\\SweetDreams.wav' where songid=2;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\Indian cinema\\ide_kada.wav' where songid=3;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\Indian cinema\\Rasiya.wav' where songid=4;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\Jazz\\jazzz.wav' where songid=5;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\Jazz\\nightlife.wav' where songid=6;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\pop\\AWaqt.wav'  where songid=7;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\pop\\ThisIsWhat.wav' where songid=8;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\rock\\Boy.wav' where songid=9;
update songs set songpath='D:\\stack route\\JAVA project\\jukebox-project\\songs wav file\\rock\\HeatWave.wav' where songid=10;

select * from songs where songid=1;
delete from Songs where songid in (12,13,14,15,15,16,17,18,19,20);
select * from songs;
select* from songs where  album='classical music';
select * from songs where songGenre='Indian cinema';
desc songs;
select * from songs where songid=1;
