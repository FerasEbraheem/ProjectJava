-- إنشاء قاعدة البيانات
CREATE DATABASE ShareEmpDB;
USE ShareEmpDB;

-- جدول الموظفين الداخليين (Mitarbeiter)
CREATE TABLE Mitarbeiter (
    MitarbeiterID INT PRIMARY KEY AUTO_INCREMENT,
    Vorname VARCHAR(50) NOT NULL,
    Nachname VARCHAR(50) NOT NULL,
    Straße VARCHAR(100) NOT NULL,
    PLZ VARCHAR(10) NOT NULL,
    Ort VARCHAR(50) NOT NULL,
    Telefon VARCHAR(20),
    Email VARCHAR(100)
);

-- جدول الموظفين الخارجيين (Externe Mitarbeiter)
CREATE TABLE Externe_Mitarbeiter (
    ExterneID INT PRIMARY KEY AUTO_INCREMENT,
    Vorname VARCHAR(50) NOT NULL,
    Nachname VARCHAR(50) NOT NULL,
    Straße VARCHAR(100) NOT NULL,
    PLZ VARCHAR(10) NOT NULL,
    Ort VARCHAR(50) NOT NULL,
    Telefon VARCHAR(20),
    Email VARCHAR(100),
    FirmaID INT,
    FOREIGN KEY (FirmaID) REFERENCES Firma(FirmaID) ON DELETE SET NULL
);

-- جدول الشركات (Firma)
CREATE TABLE Firma (
    FirmaID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Adresse VARCHAR(100) NOT NULL
);

-- جدول العملاء (Kunden)
CREATE TABLE Kunden (
    KundenID INT PRIMARY KEY AUTO_INCREMENT,
    Vorname VARCHAR(50) NOT NULL,
    Nachname VARCHAR(50) NOT NULL,
    Straße VARCHAR(100) NOT NULL,
    PLZ VARCHAR(10) NOT NULL,
    Ort VARCHAR(50) NOT NULL,
    Telefon VARCHAR(20),
    Email VARCHAR(100),
    Branche VARCHAR(100) NOT NULL
);

-- جدول العلاقة بين الموظفين والعملاء (Mitarbeiter_Kunden)
CREATE TABLE Mitarbeiter_Kunden (
    MitarbeiterID INT,
    KundenID INT,
    PRIMARY KEY (MitarbeiterID, KundenID),
    FOREIGN KEY (MitarbeiterID) REFERENCES Mitarbeiter(MitarbeiterID) ON DELETE CASCADE,
    FOREIGN KEY (KundenID) REFERENCES Kunden(KundenID) ON DELETE CASCADE
);
