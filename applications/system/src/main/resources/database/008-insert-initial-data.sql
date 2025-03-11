-- liquibase formatted sql
-- changeset nadol:8
insert into author(author_id, first_name, last_name, email)
values ('d89dd2c9-2569-4c01-a9cf-791329cf59d7', 'Adam', 'Mickiewicz', 'hejtnaslowackiego@dworek.pl'),
       ('aff8de2f-2c61-4f7e-8de0-5adb476f13cd', 'Jerzy', 'Sapkowski', 'wither.author@gmail.com'),
       ('4912e26c-4931-4ca2-bcba-e53356f6a0e8', 'Stanisław', 'Wyspiański', 'hoholi.taniec@wesele.pl');

insert into book_category(category_name)
values ('Fantasy'),
       ('Horror'),
       ('Science fiction'),
       ('Educational'),
       ('Finances'),
       ('Folk');

insert into book(book_id, book_title, pages, category_id)
values ('98194de2-0bc3-4619-b72f-669e9e54ffe3', 'Pan Tadeusz', 443, 6),
       ('4dc19428-77ad-4090-8b59-3c3db5052568', 'Wesele', 211, 6),
       ('cb8670a2-03d4-4bc9-a62a-ff7fdc48eb8a', 'Witcher', 637, 1),
       ('ad06e57c-edff-42da-90d8-822428a3c3b4', 'Praktyki', 59, 4);

insert into book_author(author_id, book_id)
values ('d89dd2c9-2569-4c01-a9cf-791329cf59d7', '98194de2-0bc3-4619-b72f-669e9e54ffe3'),
       ('aff8de2f-2c61-4f7e-8de0-5adb476f13cd', 'cb8670a2-03d4-4bc9-a62a-ff7fdc48eb8a'),
       ('4912e26c-4931-4ca2-bcba-e53356f6a0e8', '4dc19428-77ad-4090-8b59-3c3db5052568'),
       ('d89dd2c9-2569-4c01-a9cf-791329cf59d7', 'ad06e57c-edff-42da-90d8-822428a3c3b4'),
       ('4912e26c-4931-4ca2-bcba-e53356f6a0e8', 'ad06e57c-edff-42da-90d8-822428a3c3b4');

insert into publisher(publisher_id, publisher_name)
values ('e137137d-2a48-48b1-a693-0731f2bbdf8e', 'WPN'),
       ('bc906c93-0c46-45cf-83bc-20b7cb8e2809', 'Helion');

insert into book_type(book_type_name)
values ('Soft-cover'),
       ('Hard-cover'),
       ('E-book');

insert into published_book(published_book_id, book_id, publisher_id, book_type_id, release_number, publish_date)
values (DEFAULT, '98194de2-0bc3-4619-b72f-669e9e54ffe3', 'e137137d-2a48-48b1-a693-0731f2bbdf8e', 1, 1,
        current_date),
       (DEFAULT, '4dc19428-77ad-4090-8b59-3c3db5052568', 'e137137d-2a48-48b1-a693-0731f2bbdf8e', 2, 4,
        current_date),
       (DEFAULT, 'cb8670a2-03d4-4bc9-a62a-ff7fdc48eb8a', 'bc906c93-0c46-45cf-83bc-20b7cb8e2809', 2, 1,
        current_date),
       ( DEFAULT, 'cb8670a2-03d4-4bc9-a62a-ff7fdc48eb8a', 'bc906c93-0c46-45cf-83bc-20b7cb8e2809', 2, 2,
        current_date),
       ( DEFAULT, 'ad06e57c-edff-42da-90d8-822428a3c3b4', 'bc906c93-0c46-45cf-83bc-20b7cb8e2809', 3, 1,
        current_date),
       ( DEFAULT, '98194de2-0bc3-4619-b72f-669e9e54ffe3', 'e137137d-2a48-48b1-a693-0731f2bbdf8e', 3, 2,
        current_date)