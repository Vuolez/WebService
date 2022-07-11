create table "playerInfo".film_rating
(
    id      bigserial not null,
    rating  int4 not null,
    film_id int8 not null,
    user_id int8 not null,
    primary key (id)
);
create table "playerInfo".films
(
    id       bigserial not null,
    category varchar(1000),
    info     varchar(1000),
    won      boolean,
    nominee  varchar(1000),
    year     int4,
    avg_rating decimal,
    primary key (id)
);
create table "playerInfo".posts
(
    id      bigserial not null,
    content varchar(255),
    user_id int8      not null,
    primary key (id)
);
create table "playerInfo".posts_tags
(
    post_id int8 not null,
    tag_id  int8 not null
);
create table "playerInfo".roles
(
    id        bigserial not null,
    role_type varchar(255),
    primary key (id)
);
create table "playerInfo".tags
(
    id  bigserial not null,
    tag varchar(255),
    primary key (id)
);
create table "playerInfo".users
(
    id         bigserial not null,
    email      varchar(255),
    first_name varchar(255),
    is_active  boolean,
    last_name  varchar(255),
    password   varchar(255),
    username   varchar(255),
    primary key (id)
);
create table "playerInfo".users_roles
(
    user_id int8 not null,
    role_id int8 not null
);
alter table "playerInfo".film_rating
    add constraint FK1sh69qrv78hj0qehlcyoxlw6o foreign key (film_id) references "playerInfo".films;
alter table "playerInfo".film_rating
    add constraint FKfmx6sdttpmnohhb1yhapc48o foreign key (user_id) references "playerInfo".users;
alter table "playerInfo".posts
    add constraint FK5lidm6cqbc7u4xhqpxm898qme foreign key (user_id) references "playerInfo".users;
alter table "playerInfo".posts_tags
    add constraint FK4svsmj4juqu2l8yaw6whr1v4v foreign key (tag_id) references "playerInfo".tags;
alter table "playerInfo".posts_tags
    add constraint FKcreclgob71ibo58gsm6l5wp6 foreign key (post_id) references "playerInfo".posts;
alter table "playerInfo".users_roles
    add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references "playerInfo".roles;
alter table "playerInfo".users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references "playerInfo".users;