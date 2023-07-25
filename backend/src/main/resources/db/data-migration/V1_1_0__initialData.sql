truncate table measure cascade;

truncate table key_result cascade;

truncate table objective cascade;

truncate table person cascade;

truncate table quarter cascade;

truncate table team cascade;


ALTER SEQUENCE sequence_team RESTART WITH 200;
ALTER SEQUENCE sequence_person RESTART WITH 200;
ALTER SEQUENCE sequence_quarter RESTART WITH 200;
ALTER SEQUENCE sequence_objective RESTART WITH 200;
ALTER SEQUENCE sequence_key_result RESTART WITH 200;
ALTER SEQUENCE sequence_measure RESTART WITH 200;

insert into person (id, email, firstname, lastname, username)
values  (1, 'peggimann@puzzle.ch', 'Paco', 'Eggimann', 'peggimann'),
        (11, 'wunderland@puzzle.ch', 'Alice', 'Wunderland', 'alice'),
        (21, 'baumeister@puzzle.ch', 'Bob', 'Baumeister', 'bob'),
        (31, 'peterson@puzzle.ch', 'Findus', 'Peterson', 'findus'),
        (41, 'egiman@puzzle.ch', 'Paco', 'Egiman', 'paco'),
        (51, 'papierer@puzzle.ch', 'Robin', 'Papierer', 'robin');

insert into quarter (id, label)
values  (1, 'GJ 22/23-Q4'),
        (2, 'GJ 23/24-Q1'),
        (3, 'GJ 22/23-Q3'),
        (4, 'GJ 22/23-Q2'),
        (5, 'GJ 22/23-Q1'),
        (6, 'GJ 21/22-Q4'),
        (7, 'GJ 23/24-Q2');

insert into team (id, name)
values  (4, '/BBT'),
        (8, 'we are cube.³'),
        (5, 'Puzzle ITC'),
        (6, 'LoremIpsum');

insert into objective (id, description, modified_on, progress, title, owner_id, quarter_id, team_id)
values  (4, '', '2023-07-25 08:17:51.309958', 66, 'Build a company culture that kills the competition.', 1, 2, 5),
        (3, 'Die Konkurenz nimmt stark zu, um weiterhin einen angenehmen Markt bearbeiten zu können, wollen wir die Kundenzufriedenheit steigern. ', '2023-07-25 08:13:48.768262', 84, 'Wir wollen die Kundenzufriedenheit steigern', 1, 2, 5),
        (6, '', '2023-07-25 08:26:46.982010', 25, 'Als BBT wollen wir den Arbeitsalltag der Members von Puzzle ITC erleichtern.', 1, 2, 4),
        (5, 'Damit wir nicht alle anderen Entwickler stören wollen wir so leise wie möglich arbeiten', '2023-07-25 08:20:36.894258', 65, 'Wir wollen das leiseste Team bei Puzzle sein', 1, 2, 4),
        (9, '', '2023-07-25 08:39:45.752126', 88, 'At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.', 1, 2, 6),
        (10, '', '2023-07-25 08:39:45.772126', 88, 'should not appear on staging, no sea takimata sanctus est Lorem ipsum dolor sit amet.', 1, 2, 6),
        (8, '', '2023-07-25 08:39:28.175703', 40, 'consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua', 1, 2, 6);

insert into key_result (id, basis_value, description, expected_evolution, modified_on, target_value, title, unit, created_by_id, objective_id, owner_id)
values  (3, 0, '', 0, '2023-07-25 08:14:31.964115', 25, 'Steigern der URS um 25%', 0, 1, 3, 1),
        (4, 100, '', 1, '2023-07-25 08:15:18.244565', 67, 'Antwortzeit für Supportanfragen um 33% verkürzen.', 0, 1, 3, 1),
        (5, null, '', 4, '2023-07-25 08:16:24.466383', 1, 'Kundenzufriedenheitsumfrage soll mindestens einmal pro 2 Wochen durchgeführt werden. ', 2, 1, 3, 1),
        (6, null, '', 4, '2023-07-25 08:18:44.087674', 1, 'New structure that rewards funny guys and innovation before the end of Q1. ', 2, 1, 4, 1),
        (7, null, '', 4, '2023-07-25 08:19:13.569300', 4, 'Monthly town halls between our people and leadership teams over the next four months.', 2, 1, 4, 1),
        (8, null, '', 4, '2023-07-25 08:19:44.351252', 80, 'High employee satisfaction scores (80%+) throughout the year.', 0, 1, 4, 1),
        (10, null, '', 5, '2023-07-25 08:23:02.273028', 60, 'Im Durchschnitt soll die Lautstärke 60dB nicht überschreiten', 2, 1, 5, 1),
        (11, null, '', 4, '2023-07-25 08:24:10.972984', 2, 'Mindestens 2 Komplimente für eine angenehme Arbeitslautstärke soll dem Team zugesprochen werden.', 2, 1, 5, 1),
        (12, 0, '', 0, '2023-07-25 08:28:45.110759', 80, 'Wir wollen bereits nach Q1  rund 80% des Redesigns vom OKR-Tool abgeschlossen haben. ', 0, 1, 6, 1),
        (13, null, '', 4, '2023-07-25 08:29:57.709926', 15, 'Jedes Woche wird ein Kuchen vom BBT für Puzzle Organisiert', 2, 1, 6, 1),
        (14, 0, '', 0, '2023-07-25 08:31:39.249943', 20, 'Das BBT hilft den Membern 20% mehr beim Töggelen', 0, 1, 6, 1),
        (15, 0, 'asf', 1, '2023-07-25 08:40:49.412684', 1, ' Lorem ipsum dolor sit amet', 2, 1, 9, 1),
        (16, 200, '', 1, '2023-07-25 08:41:24.592007', 1, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.', 1, 1, 9, 1),
        (17, null, 'asdf', 5, '2023-07-25 08:41:52.844903', 20000000, 'vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', 1, 1, 9, 1),
        (18, 0, 'consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', 0, '2023-07-25 08:42:24.779721', 1, 'Lorem', 2, 1, 8, 1),
        (19, 50, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', 1, '2023-07-25 08:42:56.407125', 1, 'nsetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At ', 2, 1, 8, 1),
        (9, 100, '', 1, '2023-07-25 08:48:45.825328', 80, 'Die Member des BBT reduzieren Ihre Lautstärke um 20%', 0, 1, 5, 1);

insert into measure (id, change_info, created_on, initiatives, measure_date, value, created_by_id, key_result_id)
values  (1, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam', '2023-07-25 08:44:13.865976', '', '2023-07-24 22:00:00.000000', 77, 1, 8),
        (2, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:44:35.998776', '', '2023-07-25 22:00:00.000000', 89, 1, 8),
        (3, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:44:54.832400', '', '2023-07-24 22:00:00.000000', 1, 1, 7),
        (4, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:45:07.911215', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 22:00:00.000000', 7, 1, 7),
        (5, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:45:25.583267', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 3, 1, 6),
        (6, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:45:42.707340', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 3, 1, 5),
        (7, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:45:57.304875', '', '2023-07-25 22:00:00.000000', 2, 1, 5),
        (8, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:46:21.358930', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 70, 1, 4),
        (9, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:46:39.204525', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 16, 1, 3),
        (10, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:47:01.649202', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 10, 1, 14),
        (11, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:47:22.341767', 'Mehr Kuchen', '2023-07-24 22:00:00.000000', 1, 1, 13),
        (12, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:47:38.435770', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 20, 1, 12),
        (13, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:47:55.811768', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 1, 1, 11),
        (14, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:48:08.440951', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 50, 1, 10),
        (15, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:48:21.822399', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 81, 1, 9),
        (16, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:49:11.811674', '', '2023-07-24 22:00:00.000000', 15000, 1, 17),
        (17, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:49:32.030171', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 66.7, 1, 16),
        (18, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:49:56.975649', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 99, 1, 15),
        (19, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:50:19.024254', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 35, 1, 19),
        (20, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-25 08:50:44.059020', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', '2023-07-24 22:00:00.000000', 0.5, 1, 18);

