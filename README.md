# Mitarbeiter- und Kundenverwaltungssystem (Java + MySQL + XML)

## 🔍 Projektüberblick

Dieses Projekt ist ein prototypisches Java-Programm zur Verwaltung von Mitarbeitern, externen Mitarbeitern und Kunden. Es ersetzt die bisherige Verwaltung über Excel-Tabellen durch eine moderne Anwendung mit Datenbankanbindung, grafischer Benutzeroberfläche und XML-Datenaustausch.

---

## 🎯 Projektziele

- Ersetzung manueller Excel-Verwaltung durch automatisierte Prozesse.
- Verwaltung der Daten in einer relationalen MySQL-Datenbank.
- Import und Export über XML-Dateien als Zwischenschicht.
- Benutzerfreundliche Oberfläche (GUI) mit JavaFX.
- Hohe Datenintegrität und Konsistenz durch Validierung.

---

## 🧱 Architektur

Das Projekt ist in drei Hauptschichten unterteilt:

1. **Präsentationsschicht**  
   - JavaFX-GUI mit TableView, Buttons, Dialogen.
   - Intuitive Benutzerführung (Import, Export, Suche, Bearbeitung).

2. **Logikschicht**  
   - Domänenklassen: `Mitarbeiter`, `Kunde`, `Firma`, `ExterneMitarbeiter`, `Person` usw.
   - `DataImporter` zur Datenverarbeitung von XML → DB.

3. **Datenzugriffsschicht**  
   - `DBHelper` für die Verbindung zur MySQL-Datenbank.
   - PreparedStatements für sicheren Datenzugriff.

---

## ⚙️ Funktionen

- **XML → MySQL**: Import aller Daten in die DB (Mitarbeiter, Kunden, Firmen…).
- **MySQL → XML**: Export aktueller Daten in eine neue XML-Datei.
- **Datenanzeige**: Tabellenansicht mit dynamischen Spalten.
- **Suche & Filter**: Spaltenbasierte Suche in Tabellen.
- **Bearbeitung**: Hinzufügen, Löschen, Aktualisieren von Datensätzen.
- **Fehlerbehandlung**: Valides Feedback bei Eingabefehlern oder Konflikten.

---

## 🧪 Tests

- Umfassende Unit-Tests für alle Domänenklassen (JUnit).
- Validierung von IDs, Pflichtfeldern und Datentypen.
- Manuelle Tests über GUI und MySQL zur Funktionsprüfung.

---

## 🖼️ GUI-Layout

- **Sidebar** mit Buttons:  
  - `Show Tables`, `XML zu Datenbank`, `Datenbank zu XML`, `Beenden`.
- **Zentrale TableView** für dynamische Datenanzeige.
- **Dialoge** für Eingabe, Suche, Export und Fehler-Feedback.

---

## 🧰 Technologien

| Bereich         | Technologie        |
|----------------|--------------------|
| Programmiersprache | Java 17+       |
| GUI             | JavaFX             |
| Datenbank       | MySQL              |
| Datentransfer   | XML (DOM Parser)   |
| Build-Tool      | Maven              |
| Tests           | JUnit              |
| Diagramme       | PlantUML           |

---

## 📂 Projektstruktur (Auszug)


---

## ✅ Status

🟢 **Funktionaler Prototyp fertiggestellt**  
✔️ Getestet mit Unit-Tests & manuellen GUI-Tests  
📦 Bereit zur Weiterentwicklung in Richtung Produktivsystem

---

## 🧠 Autor

**Feras Alshaekh Ebraheem**  
📧 feras.kh.ebraheem@gmail.com

---

## 📜 Lizenz

Dieses Projekt dient Ausbildungszwecken und ist nicht für den Produktiveinsatz vorgesehen. Nutzung nur mit Quellenangabe.

