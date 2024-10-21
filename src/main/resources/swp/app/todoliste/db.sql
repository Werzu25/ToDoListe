CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY, task TEXT, due_date Date);


INSERT INTO tasks (task, due_date) VALUES
                                       ('Math Homework', '2023-10-01'),
                                       ('Science Project', '2023-10-02'),
                                       ('Reading Assignment', '2023-10-03'),
                                       ('Art Class Drawing', '2023-10-04'),
                                       ('History Essay', '2023-10-05');