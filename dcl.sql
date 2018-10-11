/* Drop database and user if it exists */
DROP USER Darius CASCADE;

/* Create database and user */
CREATE USER Darius
IDENTIFIED BY Football360
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

/* Grant permissions to user */
GRANT connect to Darius;
GRANT resource to Darius;
GRANT create session to Darius;
GRANT create table to Darius;
GRANT create view to Darius;