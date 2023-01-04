
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
update podcast set podcastname = 'Jay shetty' , podcastpath='https://open.spotify.com/show/5EqqB52m2bsr4k1Ii7sStc?si=8e8fb7bf2dfb4975'
where podcastid=1;
update podcast set podcastname='sandeep Maheshwari',podcastpath='https://open.spotify.com/show/0jCWG5oU6BvRtlLwusgLv5?si=5eec47617bbd4276'
where podcastid=2;
insert into podcast(podcastname,podcastpath)
values('Chetan Bhagat','https://open.spotify.com/show/6mFtFXJlMWpNhFXOWGHRew?si=31f8e83c7e854792');

select count(podcastName) from podcast;