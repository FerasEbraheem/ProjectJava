
USE ShareEmpDB;

-- (Firma)
INSERT INTO Firma (Name, Adresse) VALUES 
('TechCorp', 'Musterstraße 1, Berlin'),
('InnoSoft', 'Innovationsweg 5, Hamburg'),
('DataSolutions', 'Hauptstraße 23, München');

-- (Mitarbeiter)
INSERT INTO Mitarbeiter (Vorname, Nachname, Straße, PLZ, Ort, Telefon, Email) VALUES
('Max', 'Müller', 'Beispielstraße 10', '10115', 'Berlin', '030123456', 'max.mueller@email.com'),
('Lisa', 'Schneider', 'Hauptstraße 5', '20095', 'Hamburg', '040987654', 'lisa.schneider@email.com');

-- (Externe Mitarbeiter)
INSERT INTO Externe_Mitarbeiter (Vorname, Nachname, Straße, PLZ, Ort, Telefon, Email, FirmaID) VALUES
('John', 'Doe', 'Weststraße 3', '60311', 'Frankfurt', '069112233', 'john.doe@firma.com', 1),
('Anna', 'Fischer', 'Nordweg 8', '50667', 'Köln', '0221123456', 'anna.fischer@firma.com', 2);

-- (Kunden)
INSERT INTO Kunden (Vorname, Nachname, Straße, PLZ, Ort, Telefon, Email, Branche) VALUES
('Karl', 'Meier', 'Ringstraße 45', '70173', 'Stuttgart', '0711123456', 'karl.meier@kunde.com', 'IT-Beratung'),
('Sabine', 'Schmidt', 'Platz der Republik 1', '60327', 'Frankfurt', '069998877', 'sabine.schmidt@kunde.com', 'Marketing');

-- (Mitarbeiter_Kunden)
INSERT INTO Mitarbeiter_Kunden (MitarbeiterID, KundenID) VALUES
(1, 1), -- Max Müller arbeitet für Karl Meier
(2, 2); -- Lisa Schneider arbeitet für Sabine Schmidt
