ALTER TABLE owners
    ADD COLUMN IF NOT EXISTS username VARCHAR(100) UNIQUE NOT NULL,
    ADD COLUMN IF NOT EXISTS password VARCHAR(100) NOT NULL,
    ADD COLUMN IF NOT EXISTS role VARCHAR(10) NOT NULL DEFAULT 'USER';

insert into owners (name, birth_date, username, password, role)
values ('Admin user', '1111-11-11', 'admin', '$2a$10$X7WqUyvFh6LZx8z8M8kRDeNvj4D7B1cJ5VlOoK3tQd0yLm1aZsOa', 'ADMIN')
on conflict (username) DO NOTHING