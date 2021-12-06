ALTER TABLE drones ALTER COLUMN battery_capacity
SET DEFAULT (100);

CREATE TABLE battery_check_logs (
    id                      UUID NOT NULL DEFAULT RANDOM_UUID(),
    drone_id                UUID NOT NULL,
    battery_capacity        INTEGER NOT NULL,
    checked_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (drone_id) REFERENCES drones (id)
);