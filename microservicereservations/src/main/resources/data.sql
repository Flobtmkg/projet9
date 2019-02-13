INSERT INTO etat_reservation (num_etat, etat) VALUES (1, 'Non-payé');
INSERT INTO etat_reservation (num_etat, etat) VALUES (2, 'Payé');
INSERT INTO etat_reservation (num_etat, etat) VALUES (3, 'Annulé avant paiement');
INSERT INTO etat_reservation (num_etat, etat) VALUES (4, 'Annulé après paiement');

INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, is_reservation_precedente) VALUES (1, 1, 1, 2, '2019-01-02', 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, is_reservation_precedente) VALUES (2, 2, 2, 2, '2019-01-02', 'false');
INSERT INTO reservation (id, id_aventure, id_user, num_etat, date_reservation, is_reservation_precedente) VALUES (3, 3, 1, 3, '2019-01-02', 'false');
