insert into public.objective (id, description, modified_on, title, created_by_id, quarter_id, team_id, state,
                              modified_by_id, created_on)
values (19, 'Lorem Ipsum sit amet diri guru humu saguri alam apmach helum di gau', '2023-10-02 15:04:02.000000',
        'Wir verwenden ausschliesslich Angular Material in unseren Designs.', 1, 7, 8, 'NOTSUCCESSFUL', null,
        '2023-10-02 09:05:17.000000'),
       (20, 'Lorem Ipsum sit amet diri guru humu saguri alam apmach helum di gau', '2023-10-02 15:05:57.000000',
        'Loremsen Inud di doro sim guru', 1, 7, 6, 'SUCCESSFUL', null, '2023-10-02 09:06:41.000000'),
       (11, 'Damit wir effizienter und besser zusammenarbeiten, müssen wir den Teamzusammenhalt stärken',
        '2023-10-02 13:49:39.000000', 'Wir wollen die Zusammenarbeit im Team steigern.', 1, 7, 5, 'ONGOING', null,
        '2023-10-02 08:49:29.000000'),
       (14, 'Damit wir motivierte Lernende geben wir Ihnen jeden morgen ein gratis Schoggigipfeli.',
        '2023-10-02 14:55:30.000000', 'Wir wollen motivierte, satte Lernende', 1, 7, 4, 'ONGOING', null,
        '2023-10-02 08:56:12.000000'),
       (15, 'Um die Ausgaben für Getränke zu reduzieren, muss der Konsum im BBT reduziert werden.',
        '2023-10-02 14:57:25.000000', 'Wir wollen unseren Konsum von Süssgetränken und Speisen reduzieren.', 1, 7, 4,
        'NOTSUCCESSFUL', null, '2023-10-02 08:58:39.000000'),
       (23,
        'Mit der Weiterentwiklung wollen wir ein perfektes Tool für das OKR-Framework entwickeln, damit das volle Potential des Frameworks ausgenutzt werden kann.',
        '2023-10-02 14:59:21.000000', 'The Puzzle OKR Tool will be the best application ever developed by Puzzle ITC',
        1, 7, 4, 'ONGOING', null, '2023-10-02 09:00:23.000000'),
       (17, 'Lorem Ipsum sit amet diri guru humu saguri alam apmach helum di gau', '2023-10-02 14:01:50.000000',
        'Unsere Mockups werden zu den Besten der Schweiz', 1, 7, 8, 'DRAFT', null, '2023-10-02 09:02:38.000000'),
       (18, 'Lorem Ipsum sit amet diri guru humu saguri alam apmach helum di gau', '2023-10-02 14:03:01.000000',
        'Wir pflegen eine offene Kommunikation mit den Entwicklern.', 1, 7, 8, 'NOTSUCCESSFUL', null,
        '2023-10-02 09:03:44.000000'),
       (12, 'Um die Löhne unserer Mitarbeiter zu erhöhen müssen wir mehr Umsatz machen', '2023-10-02 10:51:02.000000',
        'Wir wollen mehr Umsatz machen.', 1, 7, 5, 'ONGOING', null, '2023-10-02 08:51:40.000000'),
       (13,
        'Um eine saubere und natürliche Arbeitsumgebung für die Mitarbeiter zu schaffen, richten wir mehr Pflanzen ein.',
        '2023-10-02 12:53:36.000000', 'Wir wollen mehr Pflanzen in den Puzzle Büros.', 1, 7, 5, 'ONGOING', null,
        '2023-10-02 08:54:11.000000'),
       (22, 'Lorem Ipsum sit amet diri guru humu saguri alam apmach helum di gau', '2023-10-02 13:07:56.000000',
        'Wing Wang Tala Tala Ting Tang', 1, 7, 6, 'DRAFT', null, '2023-10-02 09:08:40.000000'),
       (21, 'Lorem Ipsum sit amet diri guru humu saguri alam apmach helum di gau', '2023-10-02 13:07:09.000000',
        'Ting Tang Wala Wala Bing Bang', 1, 7, 6, 'DRAFT', null, '2023-10-02 09:07:39.000000');

insert into key_result (id, baseline, description, modified_on, stretch_goal, title, created_by_id, objective_id, owner_id, unit, key_result_type, created_on, commit_zone, target_zone, stretch_zone)
values
        (20, 0, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', 150, 'Prow scuttle parrel provost Sail ho shrouds spirits boom mizzenmast yardarm.', 1, 11, 1, 'PERCENT', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (22, 40, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', 300, 'Scourge of the seven seas blow the man down provost hail-shot Yellow Jack', 1, 11, 1, 'PERCENT', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (23, 1050, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', 5000, 'Sea Legs hogshead yardarm Pieces of Eight boatswain jack mizzen tack belay ballast', 1, 12, 1, 'NUMBER', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (24, 50, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', 100, 'Parrel Shiver me timbers lanyard crows nest fluke gun skysail no prey', 1, 12, 1, 'CHF', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (25, 15, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', 45, 'Driver jack hempen halter poop deck bucko broadside me', 1, 12, 1, 'FTE', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (31, 9, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', 27, 'Rigging tender flogging gun clipper Plate Fleet bowsprit crack Jennys tea cup gunwalls Davy Jones Locker', 1, 20, 1, 'NUMBER', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (26, null, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', null, 'Hornswaggle hands rum Gold Road lugsail spanker Davy Jones Locker pressgang ', 1, 13, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Reached commit zone here', 'Reached target zone here', 'Reached stretch zone here'),
        (28, null, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', null, 'Scallywag Spanish Main coxswain brigantine case shot bring a spring upon her cable ', 1, 13, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Reached commit zone here', 'Reached target zone here', 'Reached stretch zone here'),
        (32, null, '', '2023-10-19 16:19:34.000000', null, 'Das OKR-Tool wird zum Vorzeigeprojekt für herrvorragende Usability', 1, 23, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Wenn ein User die Applikation zum ersten mal benutzt, kann dieser ohne nachfragen seine Objectives und Key Results erfassen und scoren', 'Für interne Projekte wird das OKR-Tool als zu erreichender Standard angesehen, wir wollen mindestens 1 Anfrage erhalten, wie wir ein Frontendfeature umgesetzt haben.', 'Das OKR-Tool wird von UX als Beispiel bei mindestens einem Kunde für ein perfektes Usererlebnis verwendet. Weiter richten sich die restlichen internen Projekte (PTime, Cryptopus und PSkills) nach den Icons und Buttonhirarchien, welche in unserem OKR-Tool implementiert sind.'),
        (30, null, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', null, 'Jack Tar strike colors draft Cat onine tails blow the man down skysail mutiny yawl overhaul bilge', 1, 18, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Commit zone is here', 'Target zone is here', 'Stretch zone is here'),
        (34, null, 'Um zukünftige Wartungsarbeiten oder Weiterentwicklungen am Tool sauber auszuführen und um die Funktionalität des Tools sicherzustellen, wird die Applikation mit verschieden Testsgetestet.', '2023-10-19 16:19:36.000000', null, 'Das OKR-Tool weisst eine ausgezeichnete Testabdenkung auf', 1, 23, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Unit Tests erreichen eine Testabdekung von 80% auf den Methoden und 80% auf den Branches, Controller sind mit IntegrationsTests getestet, einfache EndToEnd Tests sind mit Cypress umgesetzt.', 'Unit Tests erreichen eine Testabdekung von 80% auf Methoden und Branches, Controller sind mit Integrationstests getestet und EndToEnd Tests, testen Rollenspezifisch die verschiedenen Ansichten der Applikation durch.', 'Unit Tests erreichen eine Testabdekung von 85% auf Branches und Methoden, Controller sind zu 100% mit Integrationstests getestet, EndToEnd Tests, testen für jede Rolle, jede Ansicht, ausserdem überprüffen die End2End tests, ob jeweils die Richtigen ereignisse geschehen wenn eine Aktion ausgeführt wird.'),
        (29, null, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', null, 'Smartly aye Pieces of Eight hang the jib gun nipperkin Nelsons folly schooner Pirate Round swab', 1, 18, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Reached commit zone here', 'Reached target zone here', 'Reached stretch zone here'),
        (35, null, 'Der erleichterte Umgang mit dem OKR-Framework soll weitere Members dazu Anregen, das Framework von OKRs zu nutzen.', '2023-10-19 16:19:33.000000', null, 'Unser Tool macht das OKR-Framework beliebter', 1, 23, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Mindestens ein Team von Puzzle, welches bis lang nicht am OKR-Framework teilnimmt, wird dank dem Tool neu auch am OKR Teilnehmen. Wir finden dies heraus anhand von Nachfragen wenn neue Teams dazustossen.', 'Alle Teams von Puzzle nehmen am OKR-Framework Teil.', 'Alle Teams von Puzzle nehmen am OKR-Framework Teil, zusätzlich haben wir mindestens eine externe Anfrage für unser Tool.'),
        (27, null, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', null, 'Black spot bilge booty marooned Davy Jones Locker rum scourge of the seven seas Sink ', 1, 13, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'Reached commit zone here', 'Reached target zone here', 'Reached stretch zone here'),
        (33, 3, 'Sonar untersucht den Code auf unsaubere Methoden (schlechte performance, security issues), und auf Code, welcher potentiell Bugs herbeiführen kann, dabei erstellt Sonar ein Rating mit 5 stufen, wobei E die schlechteste Stufe ist und A die beste.', '2023-10-19 16:19:37.000000', 5, 'Das OKR-Tool verbessert den Sonar Bugscore von C auf A', 1, 23, 1, 'NUMBER', 'metric', '2023-10-02 09:16:07.000000', null, null, null),
        (21, null, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lore', '2023-10-02 13:15:22.000000', null, 'Clap of thunder bilge aft log crows nest landlubber or just lubber overhaul', 1, 11, 1, '', 'ordinal', '2023-10-02 09:16:07.000000', 'This is the commit zone', 'This is the target zone', 'This is the stretch zone');

insert into check_in (id, change_info, created_on, initiatives, modified_on, value_metric, created_by_id, key_result_id,
                      confidence, check_in_type, zone)
values (21,
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 08:50:44.059000',
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 22:00:00.000000', 150, 1, 20, 10, 'metric', null),
       (22,
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 08:50:44.059000',
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 22:00:00.000000', null, 1, 21, 4, 'ordinal', 'FAIL'),
       (23,
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 08:50:44.059000',
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 22:00:00.000000', 200, 1, 22, 6, 'metric', null),
       (24,
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 08:50:44.059000',
        'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ',
        '2023-10-02 22:00:00.000000', 200, 1, 23, 1, 'metric', null),
       (25, 'Lorem ipsum dolor sit amet', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        75, 1, 24, 2, 'metric', ''),
       (26, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        70, 1, 25, 9, 'metric', null),
       (27, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        null, 1, 26, 6, 'ordinal', 'COMMIT'),
       (28, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        null, 1, 27, 8, 'ordinal', 'TARGET'),
       (29, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        null, 1, 28, 8, 'ordinal', 'STRETCH'),
       (30, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        null, 1, 29, 10, 'ordinal', 'STRETCH'),
       (31, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        null, 1, 30, 2, 'ordinal', 'FAIL'),
       (32, 'Lorem ipsum dolor sit amet, richi rogsi brokilon', '2023-10-02 08:50:44.059000',
        ' sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat', '2023-10-02 22:00:00.000000',
        13, 1, 31, 3, 'metric', null);

insert into public.quarter (id, label, start_date, end_date)
values  (200, 'GJ 23/24-Q3', '2024-01-01', '2024-03-31');

insert into organisation (id, org_name, state) values
                                                   (1, 'org_gl', 'ACTIVE'),
                                                   (2, 'org_bl', 'ACTIVE'),
                                                   (3, 'org_mobility', 'ACTIVE'),
                                                   (4, 'org_azubi', 'ACTIVE'),
                                                   (5, 'org_inactive', 'INACTIVE');

insert into team_organisation (team_id, organisation_id) values
                                                             (6, 3),
                                                             (4, 5),
                                                             (5, 1),
                                                             (6, 2),
                                                             (8, 2),
                                                             (8, 5);