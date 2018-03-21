drop database java_squad;
create database java_squad;
create user 'final'@'localhost' identified by 'me';
grant select, insert, update, delete, create, create view, drop,
 execute, references on java_squad.* to 'final'@'localhost';

use java_squad;
drop table if exists Player;
drop table if exists Score;


create table Player(
	player_ID integer,
    player_name varchar(25),
    primary key (player_ID)
);

create table Score(
	high_score integer,
    player_ID integer,
    level_num integer,
    primary key (high_score)
);


insert into Player values (100, 'Player A');
insert into Player values (200, 'Player B');
insert into Player values (300, 'Player C');

insert into Score values (1000, 100, 001);
insert into Score values (2000, 200, 001);
insert into Score values (3000, 300, 001);


