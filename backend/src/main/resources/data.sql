-- Initialisation des tables
INSERT INTO Country(id, code, name) VALUES
    (1, 'FR', 'France'), -- Les clés sont déclarées auto-générées
    (2, 'UK', 'United Kingdom'),
    (3, 'US', 'United States of America');
-- Si on fixe les clés auto-générées, il faut réinitialiser le compteur
-- Attention, cette commande n'est pas standard SQL
-- ici la syntaxe pour H2
ALTER TABLE Country ALTER COLUMN id RESTART WITH 14;


-- Initialisation des tables
INSERT INTO Formation(id, nom) VALUES
                                   (1, 'Informatique'),
                                   (2, 'Marketing'),
                                   (3, 'Gestion'),
                                   (4, 'Ressources Humaines'),
                                   (5, 'Commerce International'),
                                   (6, 'Biologie'),
                                   (7, 'Physique'),
                                   (8, 'Mathématiques'),
                                   (9, 'Droit'),
                                   (10, 'Communication'),
                                   (11, 'Finance'),
                                   (12, 'Comptabilité'),
                                   (13, 'Langues Étrangères');

ALTER TABLE Formation ALTER COLUMN id RESTART WITH 11;

INSERT INTO Champ(id_champ, type, nom, placeholders, defaut) VALUES
                                                    (1, 'text', 'nom' ,'Entrez votre nom', true),
                                                    (2, 'number', 'code_postal' , 'Entrez votre âge', true),
                                                    (3, 'email', 'mail' ,'Entrez votre adresse mail', true),
                                                    (4, 'text', 'prénom', 'Entrez votre prénom', true),
                                                    (5, 'tel', 'téléphone', 'Entrez votre numéro de téléphone', true),
                                                    (6, 'select', 'formation_intéressée', 'Sélectionnez votre formation d''intérêt', true),
                                                    (7, 'checkbox', 'newsletter', 'Souhaitez-vous recevoir notre newsletter?', false),
                                                    (8, 'date', 'date_naissance', 'Entrez votre date de naissance', false),
                                                    (9, 'textarea', 'commentaires', 'Laissez-nous un commentaire', false),
                                                    (10, 'select', 'niveau_etudes', 'Sélectionnez votre niveau d''études', true);
ALTER TABLE Champ ALTER COLUMN id_champ RESTART WITH 4;

INSERT INTO Salon(id_salon, nom, archive, date) VALUES
                                            (1, 'Salon Étudiant', false, '2025-06-15'),
                                            (2, 'Forum de l’Emploi',false, '2025-09-10'),
                                            (3, 'Salon des Entrepreneurs',false, '2025-11-20'),
                                            (4, 'Salon de l''Éducation', false, '2025-07-05'),
                                            (5, 'Forum Tech', false, '2025-08-12'),
                                            (6, 'Salon International des Études Supérieures', false, '2025-10-08'),
                                            (7, 'Salon des Métiers d''Avenir', false, '2025-12-03'),
                                            (8, 'Forum Alternance', false, '2024-01-15'),
                                            (9, 'Salon de l''Orientation', false, '2024-02-20'),
                                            (10, 'Forum des Grandes Écoles', false, '2024-03-10');
ALTER TABLE Salon ALTER COLUMN id_salon RESTART WITH 11;

INSERT INTO Prospect(id_prospect, nom, prenom, num_tel, mail, ville, departement, code_postal, id_salon) VALUES
                                                                                                             (1, 'Dupont', 'Jean', '0612345678', 'jean.dupont@mail.com', 'Paris', '75', '75001', 1),
                                                                                                             (2, 'Martin', 'Sophie', '0623456789', 'sophie.martin@mail.com', 'Lyon', '69', '69002', 2),
                                                                                                             (3, 'Lefevre', 'Paul', '0634567890', 'paul.lefevre@mail.com', 'Marseille', '13', '13003', 3),
                                                                                                             (4, 'Lesca', 'Tauarii', '0634567899', 'tawi.lesca@gmail.com', 'Papeete', '987', '98735', 8),
                                                                                                             (5, 'Lesca', 'Tryharii', '0634567898', 'tomahari.lesca@gmail.com', 'Papeete', '987', '98735', 8),
                                                                                                             (6, 'Bernard', 'Michel', '0612345679', 'michel.bernard@mail.com', 'Paris', '75', '75002', 1),
                                                                                                             (7, 'Petit', 'Anne', '0623456780', 'anne.petit@mail.com', 'Lyon', '69', '69003', 2),
                                                                                                             (8, 'Dubois', 'Pierre', '0634567891', 'pierre.dubois@mail.com', 'Marseille', '13', '13004', 3),
                                                                                                             (9, 'Moreau', 'Catherine', '0645678902', 'catherine.moreau@mail.com', 'Toulouse', '31', '31000', 9),
                                                                                                             (10, 'Richard', 'Thomas', '0656789013', 'thomas.richard@mail.com', 'Bordeaux', '33', '33000', 5),
                                                                                                             (11, 'Simon', 'Julie', '0667890124', 'julie.simon@mail.com', 'Nantes', '44', '44000', 6),
                                                                                                             (12, 'Laurent', 'David', '0678901235', 'david.laurent@mail.com', 'Nice', '06', '06000', 7),
                                                                                                             (13, 'Leroy', 'Marie', '0689012346', 'marie.leroy@mail.com', 'Strasbourg', '67', '67000', 1),
                                                                                                             (14, 'Robert', 'Philippe', '0690123457', 'philippe.robert@mail.com', 'Montpellier', '34', '34000', 2),
                                                                                                             (15, 'Michel', 'Claire', '0701234568', 'claire.michel@mail.com', 'Lille', '59', '59000', 3),
                                                                                                             (16, 'Garcia', 'Antoine', '0712345679', 'antoine.garcia@mail.com', 'Rennes', '35', '35000', 4),
                                                                                                             (17, 'Durand', 'Élodie', '0723456780', 'elodie.durand@mail.com', 'Grenoble', '38', '38000', 10),
                                                                                                             (18, 'Fournier', 'Julien', '0734567891', 'julien.fournier@mail.com', 'Dijon', '21', '21000', 10),
                                                                                                             (19, 'Lefèvre', 'Céline', '0745678902', 'celine.lefevre@mail.com', 'Angers', '49', '49000', 7),
                                                                                                             (20, 'Mercier', 'Nicolas', '0756789013', 'nicolas.mercier@mail.com', 'Le Mans', '72', '72000', 1);

ALTER TABLE Prospect ALTER COLUMN id_prospect RESTART WITH 21;

INSERT INTO Formulaire(id_form, actif, id_salon) VALUES
                                                     (1, TRUE, 1),
                                                     (2, TRUE, 2),
                                                     (3, TRUE, 3),
                                                     (4, FALSE, 4),
                                                     (5, FALSE, 5),
                                                     (6, FALSE, 6),
                                                     (7, FALSE, 7),
                                                     (8, FALSE, 1),
                                                     (9, FALSE, 2),
                                                     (10, FALSE, 3);
ALTER TABLE Formulaire ALTER COLUMN id_form RESTART WITH 11;

INSERT INTO Suivie(id_prospect, id) VALUES
                                        (1, 1),
                                        (2, 2),
                                        (3, 3),
                                        (4, 1),
                                        (5, 2),
                                        (6, 3),
                                        (7, 1),
                                        (8, 2),
                                        (9, 3),
                                        (10, 4),
                                        (11, 5),
                                        (12, 6),
                                        (13, 7),
                                        (14, 8),
                                        (15, 9),
                                        (16, 10),
                                        (17, 11),
                                        (18, 12),
                                        (19, 13),
                                        (20, 1);

INSERT INTO Contient(id_champ, id_form) VALUES
                                            (1, 1),
                                            (2,1),
                                            (3,1),
                                            (1,2),
                                            (2, 2),
                                            (3,2),
                                            (1,3),
                                            (2,3),
                                            (3, 3),
                                            (1, 4),
                                            (2, 4),
                                            (3, 4),
                                            (4, 4),
                                            (1, 5),
                                            (2, 5),
                                            (3, 5),
                                            (5, 5),
                                            (1, 6),
                                            (2, 6),
                                            (3, 6),
                                            (6, 6),
                                            (1, 7),
                                            (2, 7),
                                            (3, 7),
                                            (7, 7),
                                            (4, 8),
                                            (5, 8),
                                            (6, 8),
                                            (4, 9),
                                            (5, 9),
                                            (7, 9),
                                            (4, 10),
                                            (6, 10),
                                            (7, 10);

INSERT INTO Info(id_prospect, id_champ, valeur_txt, valeur_num) VALUES
                                                                    (4, 1, 'Tauarii Lesca', NULL),
                                                                    (5, 1, 'Tryharii Lesca', NULL),
                                                                    (6, 1, 'Michel Bernard', NULL),
                                                                    (7, 1, 'Anne Petit', NULL),
                                                                    (8, 1, 'Pierre Dubois', NULL),
                                                                    (9, 1, 'Catherine Moreau', NULL),
                                                                    (10, 1, 'Thomas Richard', NULL),
                                                                    (4, 2, NULL, 27),
                                                                    (5, 2, NULL, 23),
                                                                    (6, 2, NULL, 32),
                                                                    (7, 2, NULL, 29),
                                                                    (8, 2, NULL, 45),
                                                                    (9, 2, NULL, 38),
                                                                    (10, 2, NULL, 41),
                                                                    (4, 3, 'tawi.lesca@gmail.com', NULL),
                                                                    (5, 3, 'tomahari.lesca@gmail.com', NULL),
                                                                    (6, 3, 'michel.bernard@mail.com', NULL),
                                                                    (7, 3, 'anne.petit@mail.com', NULL),
                                                                    (8, 3, 'pierre.dubois@mail.com', NULL),
                                                                    (9, 3, 'catherine.moreau@mail.com', NULL),
                                                                    (10, 3, 'thomas.richard@mail.com', NULL),
                                                                    (11, 1, 'Julie Simon', NULL),
                                                                    (11, 2, NULL, 35),
                                                                    (11, 3, 'julie.simon@mail.com', NULL),
                                                                    (12, 1, 'David Laurent', NULL),
                                                                    (12, 2, NULL, 42),
                                                                    (12, 3, 'david.laurent@mail.com', NULL),
                                                                    (13, 1, 'Marie Leroy', NULL),
                                                                    (13, 2, NULL, 39),
                                                                    (13, 3, 'marie.leroy@mail.com', NULL);