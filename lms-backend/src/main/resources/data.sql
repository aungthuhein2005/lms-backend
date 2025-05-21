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

-- Insert into Course table
INSERT INTO course (id, title, description) VALUES 
(1, 'Java Programming', 'Learn core and advanced Java concepts.'),
(2, 'Web Development', 'HTML, CSS, JavaScript, and modern frameworks.');

-- Insert into Module table
INSERT INTO module (id, name, description, exam_id, assignment_id, course_id) VALUES 
(1, 'Java Basics', 'Covers basic syntax and OOP.', 101, 201, 1),
(2, 'Advanced Java', 'Threads, Streams, and Collections.', 102, 202, 1),
(3, 'Frontend Basics', 'HTML and CSS introduction.', 103, 203, 2),
(4, 'JavaScript', 'DOM, ES6, and event handling.', 104, 204, 2);

-- Insert into Lesson table
INSERT INTO lesson (id, title, media, exam_id, assignment_id, module_id) VALUES 
(1, 'Introduction to Java', 'video1.mp4', 101, 201, 1),
(2, 'OOP Concepts', 'video2.mp4', 101, 201, 1),
(3, 'Working with Threads', 'video3.mp4', 102, 202, 2),
(4, 'HTML Structure', 'video4.mp4', 103, 203, 3),
(5, 'CSS Styling', 'video5.mp4', 103, 203, 3),
(6, 'DOM Manipulation', 'video6.mp4', 104, 204, 4);
