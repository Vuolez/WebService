insert into "playerInfo".users(id, email, password, username, is_active)
    values (1, 'admin@admin.ru', '$2a$12$S3T7uSXXMaRrf0PGvqxlGOiOvRKzlJaluz5Ig7VwmZ7YAfR8TRyRG', 'admin', true);

insert into "playerInfo".roles(id, role_type)
values (1, 'ADMIN');

insert into "playerInfo".users_roles(user_id, role_id)
    values (1, 1);