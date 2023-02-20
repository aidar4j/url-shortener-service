CREATE TABLE urls (
    id BIGINT PRIMARY KEY,
    original_url TEXT NOT NULL,
    url_key VARChAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);