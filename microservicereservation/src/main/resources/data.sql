INSERT INTO etat_reservation (etat) VALUES ("demandée");
INSERT INTO etat_reservation (etat) VALUES ("payée");
INSERT INTO etat_reservation (etat) VALUES ("annulée avant paiement");
INSERT INTO etat_reservation (etat) VALUES ("annulée après paiement");

INSERT INTO reservation (id_aventure, id_user, num_etat, date_reservation, timestamp_commentaire_reservation, commentaire_reservation) VALUES (1, 1, 1, '02/01/2019');
INSERT INTO reservation (id_aventure, id_user, num_etat, date_reservation, timestamp_commentaire_reservation, commentaire_reservation) VALUES (2, 2, 1, '02/01/2019');
INSERT INTO reservation (id_aventure, id_user, num_etat, date_reservation, timestamp_commentaire_reservation, commentaire_reservation) VALUES (3, 1, 3, '02/01/2019');
