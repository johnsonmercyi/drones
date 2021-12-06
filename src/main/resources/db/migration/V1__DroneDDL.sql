CREATE TABLE drone_models (
    id                      UUID NOT NULL DEFAULT RANDOM_UUID(),
    name                    VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE drone_states (
    id                      UUID NOT NULL DEFAULT RANDOM_UUID(),
    name                    VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE drones (
    id                      UUID NOT NULL DEFAULT RANDOM_UUID(),
    serial_no               VARCHAR (100) NOT NULL,
    model_id                UUID NOT NULL,
    weight_limit            INTEGER NOT NULL,
    battery_capacity         INTEGER NOT NULL,
    state_id                UUID NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (serial_no),
    CHECK(weight_limit <= 500),
    FOREIGN KEY (model_id) REFERENCES drone_models (id),
    FOREIGN KEY (state_id) REFERENCES drone_states (id)
);

CREATE TABLE medications (
    id                      UUID NOT NULL DEFAULT RANDOM_UUID(),
    name                    VARCHAR (255) NOT NULL,
    weight                  INTEGER NOT NULL,
    code                    VARCHAR (255) NOT NULL,
    image                   VARCHAR (1000), -- stores image link

    PRIMARY KEY (id)
);