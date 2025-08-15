-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO roles (id, name) VALUES (2, 'BANKER')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO roles (id, name) VALUES (3, 'CLIENT')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- Admin user
INSERT INTO users (id, username, password, full_name, email, role_id)
VALUES (
           1,
           'admin',
           '$2a$10$K6zxZ8mLGrZ6xX9zR7y8M.NUpnSxpHUPzR.Vt.6JTHvCBVjog9jH',
           'System Administrator',
           'admin@bank.com',
           1
       )
ON DUPLICATE KEY UPDATE username = VALUES(username);

-- Bankers (BCrypt passwords for 'Banker#123')
INSERT INTO users (id, username, password, full_name, email, role_id)
VALUES
    (2, 'banker1', '$2a$10$K6zxZ8mLGrZ6xX9zR7y8M.NUpnSxpHUPzR.Vt.6JTHvCBVjog9jH', 'First Banker', 'banker1@bank.com', 2),
    (3, 'banker2', '$2a$10$K6zxZ8mLGrZ6xX9zR7y8M.NUpnSxpHUPzR.Vt.6JTHvCBVjog9jH', 'Second Banker', 'banker2@bank.com', 2)
ON DUPLICATE KEY UPDATE username = VALUES(username);

-- Clients (BCrypt passwords for 'Client#123')
INSERT INTO users (id, username, password, full_name, email, role_id)
VALUES
    (4, 'client1', '$2a$10$K6zxZ8mLGrZ6xX9zR7y8M.NUpnSxpHUPzR.Vt.6JTHvCBVjog9jH', 'Alice Client', 'alice.client@example.com', 3),
    (5, 'client2', '$2a$10$K6zxZ8mLGrZ6xX9zR7y8M.NUpnSxpHUPzR.Vt.6JTHvCBVjog9jH', 'Bob Client', 'bob.client@example.com', 3),
    (6, 'client3', '$2a$10$K6zxZ8mLGrZ6xX9zR7y8M.NUpnSxpHUPzR.Vt.6JTHvCBVjog9jH', 'Charlie Client', 'charlie.client@example.com', 3)
ON DUPLICATE KEY UPDATE username = VALUES(username);
