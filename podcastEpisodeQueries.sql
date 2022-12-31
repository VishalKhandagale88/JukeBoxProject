-- Queries for podcast 
use jukebox;
select * from podcastEpisodes;
insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode)
values('1:53',1,1.1,'https://open.spotify.com/track/2DBhxHbihgganoBn6qKW6k?si=beeb9f005e134543'),
('2:25',1,1.2,'https://open.spotify.com/track/3FilNDzB6n7FkhHmIJtdXj?si=f49f9f1c1ee040f7'),
('2:28',1,1.3,'https://open.spotify.com/track/6OrX2V2sCqwEDxGTgRxQ0L?si=80fff62f672347e5'),
('2:25',1,1.4,'https://open.spotify.com/track/4HbmHttvHHyWZlhlTGm90W?si=8d6c9fcdca2a4445'),
('1:30',1,1.5,'https://open.spotify.com/track/4HbmHttvHHyWZlhlTGm90W?si=c310990f9e994237'),
('2:53',1,1.6,'https://open.spotify.com/track/5CZ1p7Os1Qh261gTeiGO1S?si=79dfd385a4674c46'),
('2:35',1,1.7,'https://open.spotify.com/track/0BbtpB3wMrc4aHAuMrukrC?si=2425507a59b64d90'),
('2:22',1,1.8,'https://open.spotify.com/track/5Vk4mDuad5FFghluSiJpSZ?si=932897b46ec64a03'),
('2:58',1,1.9,'https://open.spotify.com/track/2z1zy024wcJ5kiGOFdi4Rn?si=682b62d0a5d246df'),
('2:47',1,1.101,'https://open.spotify.com/track/5jKGWaaDElI5b7wBMrgpTp?si=4a23b25efe8545f2');





insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode)
values('1:13',2,2.1,'https://open.spotify.com/track/04HzvC2nPPVO6HwhAUt9Oq?si=97de04d55f4348e0'),
('1.26',2,2.2,'https://open.spotify.com/track/2zJvZbhBoV9teonha75CFz?si=39ab23f3e34945d6'),
('3:21',2,2.3,'https://open.spotify.com/track/7IhYqFM3CIaXaid33OTVxc?si=702a1afa03474821'),
('2:17',2,2.4,'https://open.spotify.com/track/2D1NbOx9O1ieqqR4vymrAC?si=1c7946a43100409c'),
('0:52',2,2.5,'https://open.spotify.com/track/3u01rxTbvnXZ3r9KRL7dcA?si=d56af00be3f14893'),
('2:03',2,2.6,'https://open.spotify.com/track/40p1UPkdsWgYpYlsd9xE8Q?si=77c9fa6788ed42dd'),
('2:36',2,2.7,'https://open.spotify.com/track/2TyL45vESJgJosisymP2tU?si=a925c8b2864245d3'),
('2:26',2,2.8,'https://open.spotify.com/track/0ObWaz4knHQKH09NWR17vN?si=b4075f3950f64919'),
('3:35',2,2.9,'https://open.spotify.com/track/2QWWxRkjdAhFOVYIXWvGMX?si=5b81c520e9644043');

-- *********Insert into TED-Talks***********
insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode)
values('00:39:33',3,3.1,'https://open.spotify.com/episode/6iReL7zwdCeFaWHVVZChbc?si=31d26859ebcd451f'),
('00:14:04',3,3.2,'https://open.spotify.com/episode/2uvmPQEDkACAqFfimdt5hW?si=9c48dbdbf2f54533');

insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode,podcastepisodename)
values('00:40:07',3,3.3,'https://open.spotify.com/episode/5HODdUqiJbJO3KaBpVpUmr?si=c7294f1fa0d54c6f','futurist'),
('00:12:30',3,3.4,'https://open.spotify.com/episode/2slIl2GzRocxSw3BvPneNs?si=4dfc603f99784aa9','Internet_problems');



select podcast.podcastid , episodenumber ,pathofepisode
from podcast join podcastepisodes
on podcast.podcastid = podcastepisodes.podcastid;

select durationofpodcastepisode,episodenumber,podcastepisodename from podcastEpisodes where podcastid=1;

alter table podcastepisodes add podcastepisodename varchar(60);
select * from podcastepisodes;
update podcastepisodes set podcastepisodename='Living infacts' where podcastEpisodeId=1 ;
update podcastepisodes set episodeNumber=1.11 where podcastEpisodeId=10; 
update podcastepisodes set podcastepisodename='Bleend Heart' where podcastEpisodeId=2;
update podcastepisodes  set podcastepisodename='Absurdity in Everyting' where podcastEpisodeId=3;
update podcastepisodes  set podcastepisodename='I get so low' where podcastEpisodeId=4;
update podcastepisodes   set podcastepisodename='H8' where podcastEpisodeId=5;
update podcastepisodes  set podcastepisodename='Doom' where podcastEpisodeId=6;
update podcastepisodes   set podcastepisodename='Die' where podcastEpisodeId=7;
update podcastepisodes    set podcastepisodename='Hard habit' where podcastEpisodeId=8;
update podcastepisodes    set podcastepisodename='connections' where podcastEpisodeId=9;
update podcastepisodes    set podcastepisodename='Millennial' where podcastEpisodeId=10;

select * from podcastepisodes ;
update podcastepisodes  set podcastepisodename='Doubt' where podcastEpisodeId=11;
update podcastepisodes  set podcastepisodename='Boom' where podcastEpisodeId=12;
update podcastepisodes  set podcastepisodename='small' where podcastEpisodeId=13;
update podcastepisodes  set podcastepisodename='contour' where podcastEpisodeId=14;
update podcastepisodes  set podcastepisodename='Mt.chill' where podcastEpisodeId=15;
update podcastepisodes  set podcastepisodename='Mad Nice' where podcastEpisodeId=16;
update podcastepisodes  set podcastepisodename='Begin Crucial' where podcastEpisodeId=17;
update podcastepisodes  set podcastepisodename='Give the Howard' where podcastEpisodeId=18;
update podcastepisodes  set podcastepisodename='61 penn' where podcastEpisodeId=19;

update podcastepisodes  set podcastepisodename='Why_should_you_forget' where podcastEpisodeId=20;
update podcastepisodes   set podcastepisodename='Emotions_behind_money' where podcastEpisodeId=21;