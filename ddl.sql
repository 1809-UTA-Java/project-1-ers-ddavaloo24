/* Create tables */

CREATE TABLE ERS_USERS (
	U_ID NUMBER(*,0) PRIMARY KEY,
	U_USERNAME VARCHAR2(40) UNIQUE,
	U_PASSWORD VARCHAR2(40),
	U_FIRSTNAME VARCHAR2(30),
	U_LASTNAME VARCHAR2(30) UNIQUE,
	U_EMAIL VARCHAR2(100),
	UR_ID NUMBER(*,0)
);

CREATE TABLE ERS_USER_ROLES (
	UR_ID NUMBER(*,0) PRIMARY KEY
	UR_ROLE VARCHAR2(40)
);

CREATE TABLE ERS_REIMBURSEMENTS (
	R_ID NUMBER(*,0) PRIMARY KEY,
	R_AMOUNT NUMBER(22,2),
	R_DESCRIPTION VARCHAR2(100),
	R_RECEIPT BLOB,
	R_SUBMITTED TIMESTAMP,
	R_RESOLVED TIMESTAMP,
	U_ID_AUTHOR NUMBER(*,0),
	U_ID_RESOLVER NUMBER(*,0),
	RT_TYPE NUMBER(*,0),
	RT_STATUS NUMBER(*,0)
);

CREATE TABLE ERS_REIMBURSEMENT_STATUS (
	RS_ID NUMBER(*,0) PRIMARY KEY,
	RS_STATUS VARCHAR2(30)
);

CREATE TABLE ERS_REIMBURSEMENT_TYPE (
	RT_ID NUMBER(*,0) PRIMARY KEY,
	RT_TYPE VARCHAR2(30)
);

ALTER ERS_USERS ADD CONSTRAINT FK_URID
FOREIGN KEY (UR_ID) REFERENCES ERS_USER_ROLES(UR_ID);

ALTER ERS_REIMBURSEMENTS ADD CONSTRAINT FK_UIDAUTHOR
FOREIGN KEY (U_ID_AUTHOR) REFERENCES ERS_USERS(U_ID);

ALTER ERS_REIMBURSEMENTS ADD CONSTRAINT FK_UIDRESOLVER
FOREIGN KEY (U_ID_RESOLVER) REFERENCES ERS_USERS(U_ID);

ALTER ERS_REIMBURSEMENTS ADD CONSTRAINT FK_RTTYPE
FOREIGN KEY (RT_TYPE) REFERENCES ERS_REIMBURSEMENT_TYPE(RT_ID);

ALTER ERS_REIMBURSEMENTS ADD CONSTRAINT FK_RTSTATUS
FOREIGN KEY (RT_STATUS) REFERENCES ERS_REIMBURSEMENT_STATUS(RS_ID);


	