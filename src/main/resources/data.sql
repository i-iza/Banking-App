-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO roles (id, name) VALUES (2, 'BANKER')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO roles (id, name) VALUES (3, 'CLIENT')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- Admin user (BCrypt passwords for 'admin123')
INSERT INTO users (id, username, password, full_name, email, role_id)
VALUES (
           1,
           'admin',
           '$2a$10$Vp7CB.79sNy7nMIiWV.1luVHhx7HpzeexmT4eFOCxaCT1DBSxx3wy',
           'System Administrator',
           'admin@bank.com',
           1
       )
ON DUPLICATE KEY UPDATE username = VALUES(username);

-- Bankers (BCrypt passwords for 'Banker#123')
INSERT INTO users (id, username, password, full_name, email, role_id)
VALUES
    (2, 'banker1', '$2a$10$kX4QDNMex185m4Oy3bqc9uQPFAc80/YOCENmx16gfsgEUkOy.AAZq', 'First Banker', 'banker1@bank.com', 2),
    (3, 'banker2', '$2a$10$kX4QDNMex185m4Oy3bqc9uQPFAc80/YOCENmx16gfsgEUkOy.AAZq', 'Second Banker', 'banker2@bank.com', 2)
ON DUPLICATE KEY UPDATE username = VALUES(username);

-- Clients (BCrypt passwords for 'Client#123')
INSERT INTO users (id, username, password, full_name, email, role_id)
VALUES
    (4, 'client1', '$2a$10$8GiuIJe8//dCdw7IVLX78OCjUPFnoie3CYx9GWcMldqNRe0K/b8da', 'Alice Client', 'alice.client@example.com', 3),
    (5, 'client2', '$2a$10$8GiuIJe8//dCdw7IVLX78OCjUPFnoie3CYx9GWcMldqNRe0K/b8da', 'Bob Client', 'bob.client@example.com', 3),
    (6, 'client3', '$2a$10$8GiuIJe8//dCdw7IVLX78OCjUPFnoie3CYx9GWcMldqNRe0K/b8da', 'Charlie Client', 'charlie.client@example.com', 3)
ON DUPLICATE KEY UPDATE username = VALUES(username);
