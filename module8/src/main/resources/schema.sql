drop table if exists CLIENTS;
drop table if exists TRAINERS;
drop table if exists GYMS;
drop table if exists ADDRESSES;

create table CLIENTS (
	id bigint auto_increment,
	personal_data_id bigint,
	trainer_id bigint,
	training_goal varchar(255),
	
	email varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	
	primary key (id)
);

create table TRAINERS (
	id bigint auto_increment,
	personal_data_id bigint,
	gym_id bigint,
	
	email varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	
	primary key (id)
);

create table GYMS (
	id bigint auto_increment,
	address_id bigint,
	primary key (id)
);

create table ADDRESSES (
	id bigint auto_increment,
	address1 varchar(255),
	address2 varchar(255),
	zip varchar(255),
	primary key (id)
);

