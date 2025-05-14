-- Insert users
INSERT INTO users (id, name, address, phone, email, profile, password, role, gender, dob)
VALUES 
(1, 'John Doe', '123 Main St', '1234567890', 'john@example.com', 'profile1.png', 'password123', 'STUDENT', 'MALE', '2000-01-01'),
(2, 'Jane Smith', '456 Elm St', '0987654321', 'jane@example.com', 'profile2.png', 'password456', 'STUDENT', 'FEMALE', '2001-02-02');

-- Insert students (linked to users)
INSERT INTO students (id, user_id, deleted)
VALUES
(1, 1, false),
(2, 2, false);
