INSERT INTO etat_reservation (num_etat, etat) VALUES (1, 'Non-payé');
INSERT INTO etat_reservation (num_etat, etat) VALUES (2, 'Payé');
INSERT INTO etat_reservation (num_etat, etat) VALUES (3, 'Annulé avant paiement');
INSERT INTO etat_reservation (num_etat, etat) VALUES (4, 'Annulé après paiement');

INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (1, 1, 1, 2, '2019-01-02', 'Franchement c''était nul', '2018-12-03 12:32:03','false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (2, 1, 2, 2, '2019-01-02', NULL, NULL, 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (3, 1, 2, 2, '2019-01-02', 'Trop bien', '2018-11-23 03:45:03', 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, commentaire_reservation, timestamp_commentaire_reservation, is_reservation_precedente) VALUES (4, 3, 1, 3, '2019-01-02', 'Franchement c''était pas ouf', '2019-01-17 12:42:45', 'false');
