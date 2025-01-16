CREATE TABLE slots (
    id UUID PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    is_reserved BOOLEAN NOT NULL,
    cost DECIMAL NOT NULL
);