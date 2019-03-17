INSERT INTO etat_reservation (num_etat, code, libelle) VALUES (1, 'NP', 'Non payée');
INSERT INTO etat_reservation (num_etat, code, libelle) VALUES (2, 'P', 'Payée');
INSERT INTO etat_reservation (num_etat, code, libelle) VALUES (3, 'AAVP', 'Annulée avant paiement');
INSERT INTO etat_reservation (num_etat, code, libelle) VALUES (4, 'AAPP', 'Annulée après paiement');

INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (1, 1, 1, 2, '2019-01-05', 'C''était vraiment génial je recommande!', '2019-02-25 12:32:03', 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (5, 2, 1, 2, '2019-01-02', NULL, NULL, 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (4, 3, 1, 1, '2019-03-16', NULL, NULL, 'false');

INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (2, 3, 2, 2, '2019-01-02', NULL, NULL, 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (3, 2, 2, 2, '2019-01-02', 'Pour l''instant rien à dire, c''est magnifique!', '2019-03-19 03:45:03', 'false');
