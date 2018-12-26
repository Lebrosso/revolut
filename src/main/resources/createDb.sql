CREATE TABLE IF NOT EXISTS BANK_TRANSACTION (id number auto_increment , title varchar(20), amount number, sourceAcc number,
destinationAcc number );

CREATE TABLE IF NOT EXISTS ACCOUNT (id number auto_increment, owner varchar(20), credit number);


