INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'folkoch@iss.edu.sg', 'Folk', 'Och', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'bacalhau@iss.edu.sg', 'Furia', 'Bacalhau', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'fredweasley@iss.edu.sg', 'Fred', 'Weasley', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'francefranken@iss.edu.sg', 'France', 'Frankenversand', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'godoscocina@iss.edu.sg', 'Godos ', 'Cocina', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'hanaricarnes@iss.edu.sg', 'Hanari', 'Carnes', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'hillarionlanchone@iss.edu.sg', 'Hilarion', 'Lanchonetes', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'handelernst@iss.edu.sg', 'Ernst', 'Handel', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'wartianwellington@iss.edu.sg', 'Wartian', 'Wellington', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('STU', 'crackerbox@iss.edu.sg', 'Cracker', 'Box', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('LEC', 'ntt@iss.edu.sg', 'Tri Tin', 'Nguyen ', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('LEC', 'cwt@iss.edu.sg', 'Cher Wah', 'Tan', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('LEC', 'esthert@iss.edu.sg', 'Esther', 'Tan', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('LEC', 'suriyap@iss.edu.sg', 'Suriya', 'Priya', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('LEC', 'ykc@iss.edu.sg', 'Yuen Kwan', 'Chia', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('ADM', 'admin1@iss.edu.sg', 'Kim', 'Truong', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('ADM', 'admin2@iss.edu.sg', 'Zhengguang', 'Han', 'p@ssw0rd');
INSERT INTO lighthouse.user (user_type, email, first_name, last_name, password) VALUES ('ADM', 'admin3@iss.edu.sg', 'Shirley', 'Chow', 'p@ssw0rd');

INSERT INTO lighthouse.admin (id) VALUES (16);
INSERT INTO lighthouse.admin (id) VALUES (17);
INSERT INTO lighthouse.admin (id) VALUES (18);

INSERT INTO lighthouse.lecturer (designation, id) VALUES ('Associate Lecturer', 11);
INSERT INTO lighthouse.lecturer (designation, id) VALUES ('Senior Lecturer', 12);
INSERT INTO lighthouse.lecturer (designation, id) VALUES ('Chief Lecturer', 13);
INSERT INTO lighthouse.lecturer (designation, id) VALUES ('Staff Lecturer', 14);
INSERT INTO lighthouse.lecturer (designation, id) VALUES ('Lecturer', 15);

INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('Mehrheimerstr 369', '2009-01-23', '2019-01-23', '39139238', 1);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('265 boulevard Charonne', '2010-02-11', '2019-01-25', '13874213', 2);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('Taucherstrabe 10', '2010-01-14', '2019-01-23', '54872433', 3);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('Strada Provinciale 124', '2012-12-19', '2019-01-24', '24789237', 4);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('Erling Skakkes gate 78', '2010-01-09', '2019-01-23', '24342649', 5);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('89 Jefferson Way Suite 2', '2009-05-03', '2019-01-23', '99866768', 6);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('2 rue du Commerce', '2009-04-03', '2019-01-22', '23143545', 7);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('ul. Filtrowa 68', '2009-10-03', '2019-01-22', '11109724', 8);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('187 Suffolk Ln', '2009-06-13', '2019-01-23', '31343435', 9);
INSERT INTO lighthouse.student (address, dob, enrolment_date, phone, id) VALUES ('Ing Gustavo Moncada 8585 Piso 20-A', '2009-05-08', '2019-01-21', '54586892', 10);


INSERT INTO lighthouse.course (course_des, course_name, credits, duration, enroll_by, exam_date, max_cap, start_date, lecturer_id) VALUES ('Fundamentals of Programming using C# - Object Oriented Programming using C# - User Interface Development with Visual Studio Net and C# - SQL Programming & DBMS - Enterprise System Development using .Net Framework', 'Software Analysis and Design', 6, 12, '2021-02-01', '2021-04-24', 50, '2021-02-03', 11);
INSERT INTO lighthouse.course (course_des, course_name, credits, duration, enroll_by, exam_date, max_cap, start_date, lecturer_id) VALUES ('Application Development Life Cycle I - Application Development Life Cycle II', 'Enterprise Solutions Design and Development', 8, 16, '2021-02-01', '2021-04-25', 80, '2021-03-04', 13);
INSERT INTO lighthouse.course (course_des, course_name, credits, duration, enroll_by, exam_date, max_cap, start_date, lecturer_id) VALUES ('Planning, scheduling, resource allocation, execution, tracking and delivery of software projects.', 'Digital Product Management', 4, 8, '2021-07-01', '2021-07-29', 30, '2021-07-05', 14);
INSERT INTO lighthouse.course (course_des, course_name, credits, duration, enroll_by, exam_date, max_cap, start_date, lecturer_id) VALUES ('Java Programming - Java Object Persistence - Web-based J2EE Applications - Wireless Technology - J2EE Project', 'Web Application Development', 6, 17, '2021-07-01', '2021-07-30', 60, '2021-07-04', 12);

INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'A-', '2021-01-25', 1, 10);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'B+', '2021-01-23', 1, 9);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('ENROLLED', '', '2021-06-16', 3, 7);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'C+', '2021-01-31', 2, 4);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'C-', '2021-01-26', 1, 6);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('ENROLLED', '', '2021-06-27', 4, 8);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('ENROLLED', '', '2021-05-22', 3, 3);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'B', '2021-01-01', 2, 9);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'B', '2021-01-05', 2, 10);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('ENROLLED', '', '2021-06-09', 4, 5);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('COMPLETED', 'A+', '2021-01-07', 1, 4);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('ENROLLED', '', '2021-05-26', 3, 1);
INSERT INTO lighthouse.enrolment (enrolment_status, grade, registered_date, course_id, student_id) VALUES ('ENROLLED', '', '2021-05-30', 3, 2);
