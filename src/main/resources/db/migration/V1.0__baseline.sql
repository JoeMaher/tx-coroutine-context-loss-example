CREATE TABLE IF NOT EXISTS example(
    id                    BIGSERIAL PRIMARY KEY,
    value                 TEXT NOT NULL,
    created_time          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);