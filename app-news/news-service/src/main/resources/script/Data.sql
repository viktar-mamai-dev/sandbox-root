Insert into USERS (PASSWORD,USERNAME,USER_ID) values ('684c851af59965b680086b7b4896ff98','robert',4);
Insert into USERS (PASSWORD,USERNAME,USER_ID) values ('ce5225d01c39d2567bc229501d9e610d','marina',1);
Insert into USERS (PASSWORD,USERNAME,USER_ID) values ('0acf4539a14b3aa27deeb4cbdf6e989f','michael',2);
Insert into USERS (PASSWORD,USERNAME,USER_ID) values ('b071c0c8d6adc66a3c2eb1b9b87d6d5c','ronnie',3);

Insert into ROLE (ROLE_NAME,USER_ID) values ('role_admin',1);
Insert into ROLE (ROLE_NAME,USER_ID) values ('role_user',2);
Insert into ROLE (ROLE_NAME,USER_ID) values ('role_user',3);
Insert into ROLE (ROLE_NAME,USER_ID) values ('role_admin',4);

commit;