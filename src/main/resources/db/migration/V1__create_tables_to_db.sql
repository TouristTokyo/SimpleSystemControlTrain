CREATE TABLE stations
(
    id   bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name text NOT NULL,
    town text NOT NULL
);

CREATE TABLE routes
(
    id               bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name             text NOT NULL,
    start_station_id bigint,
    end_station_id   bigint,
    FOREIGN KEY (start_station_id) REFERENCES stations (id) ON DELETE SET NULL,
    FOREIGN KEY (end_station_id) REFERENCES stations (id) ON DELETE SET NULL
);

CREATE TABLE trains
(
    id                 bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name               text    NOT NULL,
    speed              decimal DEFAULT 0,
    is_broken          boolean DEFAULT false,
    current_station_id bigint,
    rout_id            bigint,
    FOREIGN KEY (current_station_id) REFERENCES stations (id) ON DELETE SET NULL,
    FOREIGN KEY (rout_id) REFERENCES routes (id) ON DELETE SET NULL
);

CREATE TABLE events
(
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description text NOT NULL,
    event_date timestamp DEFAULT now()
);