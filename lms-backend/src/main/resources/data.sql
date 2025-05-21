INSERT INTO users (name, address, phone, email, profile, password, role,gender,dob)
VALUES ('Ye Yint Aung', 'Chanmyathazi', '0911223344', 'yeyintaung@gmail.com', 'default.png', '2254555', 'USER','MALE','2005-10-05');
INSERT INTO users (name, address, phone, email, profile, password, role,gender,dob)
VALUES ('Nan Ei Ei Phyo', 'Chanmyathazi', '0978123723', 'naneieiphyo@gmail.com', 'default.png', '8989899', 'USER','FEMALE','2009-04-14');
INSERT INTO users (name, address, phone, email, profile, password, role,gender,dob)
VALUES ('Aeint Myat Hume', 'Chanmyathazi', '0990898333', 'aeintmyathume@gmail.com', 'default.png', 'aefefeee', 'USER','FEMALE','2006-12-26');
INSERT INTO users (name, address, phone, email, profile, password, role,gender,dob)
VALUES ('Aye Myat Aung', 'Chanmyathazi', '0978732830', 'ayemyataung@gmail.com', 'default.png', 'ama3222', 'USER','FEMALE','2005-08-09');
INSERT INTO users (name, address, phone, email, profile, password, role,gender,dob)
VALUES ('Myat Thiri Wai', 'Chanmyathazi', '09755774563', 'myatthiriwai@gmail.com', 'default.png', 'fdfdfdg', 'USER','FEMALE','2009-05-14');

-- Insert students (linked to users)
INSERT INTO students (id, user_id, deleted)
VALUES
(1, 1, false),
(2, 2, false);

INSERT INTO course (title, description) VALUES ('Course 1', 'Description 1');
INSERT INTO course (title, description) VALUES ('Course 2', 'Description 2');
