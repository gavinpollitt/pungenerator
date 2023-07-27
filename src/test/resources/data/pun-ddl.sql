drop table PUN if exists;

create table PUN (
    ID long not null AUTO_INCREMENT,
    PUN varchar(200) not null,
    PRIMARY KEY (ID)
)