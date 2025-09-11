CREATE TABLE IF NOT EXISTS status (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS todo (
    id SERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    description VARCHAR,
    status_id INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_status_id FOREIGN KEY(status_id) REFERENCES status(id)
);