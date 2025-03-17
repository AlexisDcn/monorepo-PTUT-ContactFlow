-- Initialisation des tables
INSERT INTO Country(id, code, name) VALUES
    (1, 'FR', 'France'), -- Les clés sont déclarées auto-générées
    (2, 'UK', 'United Kingdom'),
    (3, 'US', 'United States of America');
-- Si on fixe les clés auto-générées, il faut réinitialiser le compteur
-- Attention, cette commande n'est pas standard SQL
-- ici la syntaxe pour H2
ALTER TABLE Country ALTER COLUMN id RESTART WITH 4;


-- Initialisation des tables
INSERT INTO Formation(id, nom) VALUES
                                   (1, 'Informatique'),
                                   (2, 'Marketing'),
                                   (3, 'Gestion');
ALTER TABLE Formation ALTER COLUMN id RESTART WITH 4;

INSERT INTO Champ(id_champ, type, nom, placeholders) VALUES
                                                    (1, 'text', 'nom' ,'Entrez votre nom'),
                                                    (2, 'number', 'code_postal' , 'Entrez votre âge'),
                                                    (3, 'email', 'mail' ,'Entrez votre adresse mail');
ALTER TABLE Champ ALTER COLUMN id_champ RESTART WITH 4;

INSERT INTO Salon(id_salon, nom, date) VALUES
                                            (1, 'Salon Étudiant', '2025-06-15'),
                                            (2, 'Forum de l’Emploi', '2025-09-10'),
                                            (3, 'Salon des Entrepreneurs', '2025-11-20');
ALTER TABLE Salon ALTER COLUMN id_salon RESTART WITH 4;

INSERT INTO Prospect(id_prospect, nom, prenom, num_tel, mail, ville, departement, code_postal, id_salon) VALUES
                                                                                                             (1, 'Dupont', 'Jean', '0612345678', 'jean.dupont@mail.com', 'Paris', '75', '75001', 1),
                                                                                                             (2, 'Martin', 'Sophie', '0623456789', 'sophie.martin@mail.com', 'Lyon', '69', '69002', 2),
                                                                                                             (3, 'Lefevre', 'Paul', '0634567890', 'paul.lefevre@mail.com', 'Marseille', '13', '13003', 3);

ALTER TABLE Prospect ALTER COLUMN id_prospect RESTART WITH 4;

INSERT INTO Formulaire(id_form, actif, id_salon) VALUES
                                                     (1, TRUE, 1),
                                                     (2, FALSE, 2),
                                                     (3, TRUE, 3);
ALTER TABLE Formulaire ALTER COLUMN id_form RESTART WITH 4;

INSERT INTO Suivie(id_prospect, id) VALUES
                                        (1, 1),
                                        (2, 2),
                                        (3, 3);

INSERT INTO Contient(id_champ, id_form) VALUES
                                            (1, 1),
                                            (2,1),
                                            (3,1),
                                            (1,2),
                                            (2, 2),
                                            (3,2),
                                            (1,3),
                                            (2,3),
                                            (3, 3);

INSERT INTO Info(id_prospect, id_champ, valeur_txt, valeur_num) VALUES
                                                                    (1, 1, 'Jean Dupont', NULL),
                                                                    (2, 2, NULL, 25),
                                                                    (3, 3, 'paul.lefevre@mail.com', NULL);
