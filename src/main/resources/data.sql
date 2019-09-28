insert into tag (category, title) values ('Срочность', 'Срочные');
insert into tag (category, title) values ('Срочность', 'Долгосрочные');

insert into tag (category, title) values ('Регион', 'Центральный');
insert into tag (category, title) values ('Регион', 'Северо-Западный');
insert into tag (category, title) values ('Регион', 'Южный');
insert into tag (category, title) values ('Регион', 'Северо-Кавказский');
insert into tag (category, title) values ('Регион', 'Приволжский');
insert into tag (category, title) values ('Регион', 'Уральский');
insert into tag (category, title) values ('Регион', 'Сибирский');
insert into tag (category, title) values ('Регион', 'Дальневосточный');
insert into tag (category, title) values ('Регион', 'Федеральный');
insert into tag (category, title) values ('Регион', 'Международный');

insert into tag (category, title) values ('Тема', 'Катастрофы');
insert into tag (category, title) values ('Тема', 'Приюты животных');
insert into tag (category, title) values ('Тема', 'Сироты');


insert into "USER" (login, name, password, role) values ('admin', 'Александр Григорьевич', 'admin', 'ADMIN');
insert into "USER" (login, name, password, role) values ('minnauki', 'Михаил', 'minnauki', 'ADMIN');
insert into "USER" (login, name, password, role) values ('user', 'Вася', 'user', 'USER');

insert into foundation (id, title, content, picture)
    values (1, 'SCP', 'Secure. Contain. Protect. Фонд, занятый созданием заповедников для редких животных, растений и прочих совершенно нормальных объектов.', 'https://upload.wikimedia.org/wikipedia/commons/e/ec/SCP_Foundation_%28emblem%29.svg');

insert into project (id, title, foundation_id, begin_date, end_date, image, content)
    values (1, 'Сгорела жопа', '2019-09-28', '2019-09-28', '/assets/sibir.jpg', 'Внимание внимание говорит Германия. Сегодня в самолёте из Казанского аэропорта отправится домой в Санкт-Петербург команда Федерального Государственного Автономного Бюджетного Образовательного Учреждения Высшего Образования Санкт-Петербургского Государственного Политехнического университета имени Петра Алексеевича Первого Великого. <img src="/assets/sibir.jpg">');
insert into project (id, title, foundation_id, begin_date, end_date, image, content)
    values (2, 'Жопа снова горит', '2019-09-28', '2019-10-28', '/assets/sibir.jpg', 'Спасите мою жопу');

insert into checkpoint (id, title, date, sum, project_id) VALUES (1, 'Шокеры', '2019-09-28', 1000, 1);

insert into project_tags (tags_title, project_id) values ('Приюты животных', 1);