CREATE TABLE IF NOT EXISTS ROOM ( 
ROOMID INTEGER PRIMARY KEY NOT NULL, 
NAME VARCHAR(255)  NOT NULL, 
DESCRIPTION VARCHAR(255)  NOT NULL, 
HASVISITED VARCHAR(255) NOT NULL );

CREATE TABLE IF NOT EXISTS EXIT (
EXITID INTEGER PRIMARY KEY NOT NULL,
NEXTROOMID INT NOT NULL,
DIRECTION VARCHAR(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS ITEM ( 
ID INTEGER PRIMARY KEY NOT NULL, 
NAME         VARCHAR(20)    NOT NULL, 
DESCRIPTION  VARCHAR(255)     NOT NULL, 
VARIETY       VARCHAR(255)    , 
UPGRADE         INT, 
MONSTERTYPE           VARCHAR(255), 
LEVEL          INT,
DAMAGE          INT,
items_id INT
);
	
	
CREATE TABLE IF NOT EXISTS CHARACTER ( 
ID INTEGER PRIMARY KEY NOT NULL, 
USERNAME           VARCHAR(20)    NOT NULL, 
PASSWORD           VARCHAR(255)     NOT NULL, 
NAME           CHAR(50)     NOT NULL, 
HP         INT     NOT NULL, 
LIFE          INT     NOT NULL, 
POINTS          INT     NOT NULL, 
LEVEL          INT     NOT NULL, 
ROOM_ID         INT     NOT NULL 
);
	
CREATE TABLE IF NOT EXISTS MONSTER ( 
ID INTEGER PRIMARY KEY NOT NULL, 
 IS_BOSS         VARCHAR(5)    NOT NULL, 
 NAME          VARCHAR(255)     NOT NULL, 
 VARIETY           VARCHAR(255)     NOT NULL, 
 HP         INT, 
 DAMAGE          INT, 
 MONSTERS_ROOMID         INT     NOT NULL 
);


