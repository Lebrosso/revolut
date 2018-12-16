CREATE TABLE IF NOT EXISTS BANK_TRANSACTION (id number , title varchar(20), amount number, sourceAcc number,
destinationAcc number );

INSERT into BANK_TRANSACTION values (1, 'atm', 32, 1000,3000 );
INSERT into BANK_TRANSACTION values (2, 'rbs', 25, 23, 1500);
INSERT into BANK_TRANSACTION values (3, 'hsbc', 23, 7, 2000);
INSERT into BANK_TRANSACTION values (4, 'pko', 25, 3, 6500);

CREATE TABLE IF NOT EXISTS ACCOUNT (id number, owner varchar(20), credit number);

INSERT into ACCOUNT values (1, 'TOM SAWYER', 5000);
INSERT into ACCOUNT values (2, 'BIN LADEN', 8000);
INSERT into ACCOUNT values (3, 'OBAMA', 10000);
INSERT into ACCOUNT values (4, 'STALIN', 20000);
