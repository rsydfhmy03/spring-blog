-- V2__seed_data.sql
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Hello World', 'Post pertama blog', 'hello-world', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Belajar Spring Boot', 'Post kedua blog', 'belajar-spring-boot', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Belajar JPA', 'Post ketiga blog', 'belajar-jpa', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Belajar Hibernate', 'Post keempat blog', 'belajar-hibernate', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Belajar MySQL', 'Post kelima blog', 'belajar-mysql', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Belajar PostgreSQL', 'Post keenam blog', 'belajar-postgresql', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);
INSERT INTO posts (title, body, slug, is_published, is_deleted, created_at, published_at)
VALUES 
('Belajar MongoDB', 'Post ketujuh blog', 'belajar-mongodb', true, false, UNIX_TIMESTAMP()*1000, UNIX_TIMESTAMP()*1000);