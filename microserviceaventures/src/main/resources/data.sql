INSERT INTO categorie (id, nom, description) VALUES (1, 'Mer', 'La mer c''est trop cool !');
INSERT INTO categorie (id, nom, description) VALUES (2, 'Montagne', 'La montagne ça vous gagne !');

INSERT INTO aventure (id, nom, prix, date_debut, date_fin, date_cloture, description, id_categorie, limit_reservation) VALUES (1, 'Aventure1', 100, '2019-02-19', '2019-02-23', '2019-01-19', 'C''est l''aventure 1 whouhou', 1, 34);
INSERT INTO aventure (id, nom, prix, date_debut, date_fin, date_cloture, description, id_categorie, limit_reservation) VALUES (2, 'Aventure2', 200, '2019-04-06', '2019-04-13', '2019-4-23', 'C''est l''aventure 2 whaaaaa', 1, 45);
INSERT INTO aventure (id, nom, prix, date_debut, date_fin, date_cloture, description, id_categorie, limit_reservation) VALUES (3, 'Aventure3', 300, '2019-01-14', '2019-01-24', '2019-01-03', 'C''est l''aventure 3 woooooh', 2, 28);
