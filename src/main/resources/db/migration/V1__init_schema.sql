-- V1__init_schema.sql
CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    body TEXT,
    slug VARCHAR(255) UNIQUE,
    is_published BOOLEAN,
    is_deleted BOOLEAN,
    created_at BIGINT,
    published_at BIGINT
);
