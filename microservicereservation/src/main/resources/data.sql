INSERT INTO etat_reservation (etat) VALUES ('demandée');
INSERT INTO etat_reservation (etat) VALUES ('payée');
INSERT INTO etat_reservation (etat) VALUES ('annulée avant paiement');
INSERT INTO etat_reservation (etat) VALUES ('annulée après paiement');

INSERT INTO reservation (id_aventure, id_user, num_etat, date_reservation) VALUES (1, 1, 1, '2019-01-02');
INSERT INTO reservation (id_aventure, id_user, num_etat, date_reservation) VALUES (2, 2, 1, '2019-01-02');
INSERT INTO reservation (id_aventure, id_user, num_etat, date_reservation) VALUES (3, 1, 3, '2019-01-02');
