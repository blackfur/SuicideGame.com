DROP TABLE USERS IF EXISTS;
CREATE TABLE PUBLIC.USERS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,ENABLED BOOLEAN,NICKNAME VARCHAR(255),PASSWORD VARCHAR(255),ROLE VARCHAR(255),USERNAME VARCHAR(255));

DROP TABLE authorities IF EXISTS;
CREATE TABLE PUBLIC.authorities(authority VARCHAR(255),USERNAME VARCHAR(255));
