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


INSERT INTO teachers (user_id,hire_date,deleted) VALUES (1,'2024-09-22',false);

-- Insert students (linked to users)
INSERT INTO students (user_id, enroll_date, deleted)
VALUES
(3, '2024-09-01', false),
(2, '2024-09-01', false);

-- Academic Years (assuming already inserted)
INSERT INTO academic_year (name, start_date, end_date) VALUES ('2024-2025', '2024-06-01', '2025-05-31');
INSERT INTO academic_year (name, start_date, end_date) VALUES ('2025-2026', '2025-06-01', '2026-05-31');

-- Semesters for academic year 1
INSERT INTO semester (name, start_date, end_date, academic_year_id) VALUES
('Semester 1', '2024-06-01', '2024-11-30', 1),
('Semester 2', '2024-12-01', '2025-05-31', 1);

-- You can add more semesters for other academic years if needed


INSERT INTO subject (name,description) VALUES ('Computer Science','Computer science subject');

-- Insert into Course table
INSERT INTO course (title, description) VALUES 
('Java Programming', 'Learn core and advanced Java concepts.'),
('Web Development', 'HTML, CSS, JavaScript, and modern frameworks.');

-- Insert into Module table
INSERT INTO module (name, description, exam_id, assignment_id, course_id) VALUES 
('Java Basics', 'Covers basic syntax and OOP.', 101, 201, 1),
('Advanced Java', 'Threads, Streams, and Collections.', 102, 202, 1),
('Frontend Basics', 'HTML and CSS introduction.', 103, 203, 2),
('JavaScript', 'DOM, ES6, and event handling.', 104, 204, 2);

-- Insert Lessons


INSERT INTO classes (name, description, schedule, teacher_id, semester_id, course_id)
VALUES ('Java Class A', 'Intro to Java programming', 'Mon-Wed 10:00-12:00', 1, 1, 1);

INSERT INTO classes (name, description, schedule, teacher_id, semester_id, course_id)
VALUES ('Python Class B', 'Python basics for beginners', 'Tue-Thu 14:00-16:00', 1, 2, 2);


