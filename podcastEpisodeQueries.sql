-- Queries for podcast 
use jukebox;
select * from podcastEpisodes;


-- ********* Jay-Shetty *******************
insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode,podcastepisodename)
values('01:06:00',1,1.1,'https://open.spotify.com/episode/58utxINxcGPB7YLmqVpKcq?si=c4bb37d5558a475d','How_Food_can_heal_or_damage'),
('01:12:00',1,1.2,'https://open.spotify.com/episode/5aQ4WIkNWgF8peenmtnMwj?si=cbfe977b7e05401c','practice_forgiveness'),
('01:23:00',1,1.3,'https://open.spotify.com/episode/3OVJHkM5CZAUumvSfhOtiD?si=e41fe8c2b51348f3','Attract_your_dream_life'),
('00:25:41',1,1.4,'https://open.spotify.com/episode/1G2FV5IItDmtHMl3E6m6t5?si=1479e0e9e52b473a','get_ou_of_control_zone');

-- ********* Sandeep Maheshwari ****************
insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode,podcastepisodename)
values('00:26:06',2,2.1,'https://open.spotify.com/episode/0e2TVlLB0c3ejurk1jiy9f?si=5748b08e5b854b6f','self_respect'),
('00:12:51',2,2.2,'https://open.spotify.com/episode/6Y8kmE5knFZ1T8tV1LdfRH?si=34a13bff882a4a0c','Dr_APJ_Abdul_Kalam'),
('22:15',2,2.3,'https://open.spotify.com/episode/5gPYrfObSQziolVnAMIcXk?si=542a98c7364741f8','power_of_thinking');

-- *********Insert into TED-Talks***********
insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode,podcastepisodename)
values('00:39:33',3,3.1,'https://open.spotify.com/episode/6iReL7zwdCeFaWHVVZChbc?si=31d26859ebcd451f','Setting_goals'),
('00:14:04',3,3.2,'https://open.spotify.com/episode/2uvmPQEDkACAqFfimdt5hW?si=9c48dbdbf2f54533','Emotion_behind_money'),
('00:40:07',3,3.3,'https://open.spotify.com/episode/5HODdUqiJbJO3KaBpVpUmr?si=c7294f1fa0d54c6f','futurist'),
('00:12:30',3,3.4,'https://open.spotify.com/episode/2slIl2GzRocxSw3BvPneNs?si=4dfc603f99784aa9','Internet_problems');

-- **************Chetan Bhagat ***********************
insert into podcastEpisodes(DurationOfPodcastEpisode,podcastId,EpisodeNumber,PathOFEpisode,podcastepisodename)
values('00:10:28',4,4.1,'https://open.spotify.com/episode/5xdH7AXGZbeSARMwclT0xA?si=5a20be8c1ba34475','street_smart'),
('00:10:26',4,4.2,'https://open.spotify.com/episode/3TdKvuYWg1nXD2ATo1dpdL?si=e75a55a6072d4b5d','In_life');


select podcast.podcastid , episodenumber ,pathofepisode
from podcast join podcastepisodes
on podcast.podcastid = podcastepisodes.podcastid;

select durationofpodcastepisode,episodenumber,podcastepisodename from podcastEpisodes where podcastid=1;

alter table podcastepisodes add podcastepisodename varchar(60);
select * from podcastepisodes;


select * from podcastepisodes ;



select * from podcastEpisodes;
drop table podcastepisodes ;

