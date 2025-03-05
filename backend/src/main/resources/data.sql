-- Initialisation des tables
INSERT INTO Country(id, code, name) VALUES
       (1, 'FR', 'France'),
       (2, 'UK', 'United Kingdom'),
       (3, 'US', 'United States of America');

-- Réinitialisation du compteur pour la clé auto-générée
ALTER TABLE Country ALTER COLUMN id RESTART WITH 4;

-- Insertion de données pour la table Champ
INSERT INTO Champ(type, placeholder) VALUES
      ('Text', 'Enter text here'),
      ('Number', 'Enter number here');

-- Insertion de données pour la table Formation
INSERT INTO Formation(nom) VALUES
      ('Formation 1'),
      ('Formation 2');

-- Insertion de données pour la table Formulaire
INSERT INTO Formulaire(actif) VALUES
     (TRUE),
     (FALSE);

-- Insertion de données pour la table Prospect
INSERT INTO Prospect(nom, prenom, numTel, email, ville, departement, codePostal, etablissement, id_salon) VALUES
    ('Doe', 'John', 123456789, 'john.doe@example.com', 'Paris', 'Paris', 75000, 'School 1', 1),
    ('Smith', 'Jane', 987654321, 'jane.smith@example.com', 'Lyon', 'Rhône', 69000, 'School 2', 2);

-- Insertion de données pour la table ProspectChamp
INSERT INTO ProspectChamp(valeur_txt, valeur_num) VALUES
     ('Text Value 1', 100),
     ('Text Value 2', 200);

-- Insertion de données pour la table Salon
INSERT INTO Salon(nom, date) VALUES
     ('Salon 1', '2023-10-01'),
     ('Salon 2', '2023-11-01');
