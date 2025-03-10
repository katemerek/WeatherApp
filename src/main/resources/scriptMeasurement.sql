create table measurement
(
    id               integer generated by default as identity
        primary key,
    value            double precision not null,
    raining          boolean,
    time_measurement timestamp,
    sensor           varchar(100)
        constraint measurement___fk
            references sensor (name)
);


