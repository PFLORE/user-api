-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(512),
    is_active BOOLEAN
);

-- Crear tabla de teléfonos
CREATE TABLE IF NOT EXISTS phone (
    id UUID PRIMARY KEY,
    number VARCHAR(50) NOT NULL,
    citycode VARCHAR(10) NOT NULL,
    contrycode VARCHAR(10) NOT NULL,
    user_id UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
