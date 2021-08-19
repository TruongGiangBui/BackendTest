create database TicketManager;
use TicketManager;
create table user(
	email nvarchar(200),
    fullname nvarchar(200),
    password nvarchar(200),
    constraint pk primary key(email)
);
create table usersession(
	email nvarchar(200),
    sessionid varchar(5),
    constraint pk primary key(email,sessionid)
);

create table department(
	deptid int,
    deptname nvarchar(200),
    createdate Date,
    constraint pk primary key(deptid)
);

create table complainticket(
	ticketid varchar(5) primary key,
    deptid int,
    phonenumber varchar(11),
    customeropinion text,
    timereceive date,
    answer text,
    processingstatus boolean,
    processingtime date,
    constraint fk1 foreign key(deptid) 
    references department(deptid)
    ON DELETE CASCADE
);
drop database TicketManager;