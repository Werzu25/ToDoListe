CREATE TABLE IF NOT EXISTS tasks (
                                     id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                     task TEXT,
                                     due_date DATE,
									 done BOOLEAN
);

INSERT INTO tasks (task, due_date, done) VALUES
                                       ('Math Homework', '2023-10-01', false),
                                       ('Science Project', '2023-10-02',true),
                                       ('Reading Assignment', '2023-10-03',false),
                                       ('Art Class Drawing', '2023-10-04',false),
                                       ('History Essay', '2023-10-05',false);