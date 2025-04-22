package feras;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Die Klasse Mitarbeiter repräsentiert einen internen Mitarbeiter im System.
 * Sie erbt von der abstrakten Klasse Person und fügt die spezifische Mitarbeiter-ID hinzu.
 */
@XmlRootElement
public class Mitarbeiter extends Person {

    private int mitarbeiterID;

    /**
     * Konstruktor zum Initialisieren eines Mitarbeiters mit einer gültigen ID.
     * @param mitarbeiterID die eindeutige Mitarbeiter-ID (muss positiv sein).
     */
    public Mitarbeiter(int mitarbeiterID) {
        setMitarbeiterID(mitarbeiterID); // Verwendung des Setters für Validierung
    }

    /**
     * Standardkonstruktor (wichtig für JAXB und andere Frameworks).
     */
    public Mitarbeiter() {
    }

    /**
     * Gibt die Mitarbeiter-ID zurück.
     * @return die Mitarbeiter-ID.
     */
    @XmlElement
    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    /**
     * Setzt die Mitarbeiter-ID mit Validierung.
     * @param mitarbeiterID die neue Mitarbeiter-ID (muss positiv sein).
     * @throws IllegalArgumentException falls die ID ungültig ist.
     */
    public void setMitarbeiterID(int mitarbeiterID) {
        if (mitarbeiterID <= 0) {
            throw new IllegalArgumentException("Die Mitarbeiter-ID muss eine positive Zahl sein.");
        }
        this.mitarbeiterID = mitarbeiterID;
    }
}
