create table if not exists process(
    id uuid primary key,
    image_name varchar,
    status varchar not null
);
