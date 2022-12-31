
use jukebox;
alter table podcast
add posdcastPath varchar(500);

alter table podcast
rename column posdcastPath to podcastPath;

select * from podcast;
insert into podcast(podcastName,podcastPath)
values('Podactor','https://open.spotify.com/artist/2Vd0EKerYrWeqM72vkn2gg?si=5vt0LNOwQgacGZ4PdsiSRg');

insert into podcast(podcastName,podcastPath)
values('Crucial Dudes','https://open.spotify.com/artist/5ccLp1eIj7ufwLUpVLmSFl?si=8ezMi4dNSkSVwKtfAEVOmA');

insert into podcast(podcastName,podcastPath)
values('Joe Roghan','https://open.spotify.com/artist/0bawsIe2P8ykB6psGv81P9?si=8lqKsnT9Qn6br8eysimQGQ');

update podcast set podcastname='TED-Talks' , podcastPath='https://open.spotify.com/show/1VXcH8QHkjRcTCEd88U3ti?si=1f97709bfbb94dcb'
where podcastid=3;
select * from podcast;