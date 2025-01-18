CREATE TABLE appointments (
    id UUID PRIMARY KEY,
    slot_id UUID NOT NULL,
    patient_id UUID NOT NULL,
    reserved_at TIMESTAMP NOT NULL,
    status_id INTEGER NULL,
    patient_name VARCHAR(255) NOT NULL,
    date_time TIMESTAMP NOT NULL
);
