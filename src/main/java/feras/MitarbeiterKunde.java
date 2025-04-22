package feras;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Die Klasse `MitarbeiterKunde` repräsentiert eine Beziehung zwischen einem Mitarbeiter und einem Kunden.
 * Sie enthält die IDs des Mitarbeiters und des Kunden und stellt sicher, dass nur gültige Werte gesetzt werden.
 */
@XmlRootElement
public class MitarbeiterKunde {

    private int mitarbeiterID;
    private int kundenID;

    /**
     * Standard-Konstruktor für JAXB.
     */
    public MitarbeiterKunde() {
    }

    /**
     * Konstruktor mit Parametern zur Initialisierung der IDs.
     *
     * @param mitarbeiterID die Mitarbeiter-ID (muss positiv sein).
     * @param kundenID die Kunden-ID (muss positiv sein).
     * @throws IllegalArgumentException falls eine der IDs ungültig ist.
     */
    public MitarbeiterKunde(int mitarbeiterID, int kundenID) {
        setMitarbeiterID(mitarbeiterID);
        setKundenID(kundenID);
    }

    /**
     * Gibt die Mitarbeiter-ID zurück.
     *
     * @return die Mitarbeiter-ID.
     */
    @XmlElement
    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    /**
     * Setzt die Mitarbeiter-ID mit Validierung.
     *
     * @param mitarbeiterID die Mitarbeiter-ID (muss positiv sein).
     * @throws IllegalArgumentException falls die ID ungültig ist.
     */
    public void setMitarbeiterID(int mitarbeiterID) {
        if (mitarbeiterID <= 0) {
            throw new IllegalArgumentException("Die Mitarbeiter-ID muss eine positive Zahl sein.");
        }
        this.mitarbeiterID = mitarbeiterID;
    }

    /**
     * Gibt die Kunden-ID zurück.
     *
     * @return die Kunden-ID.
     */
    @XmlElement
    public int getKundenID() {
        return kundenID;
    }

    /**
     * Setzt die Kunden-ID mit Validierung.
     *
     * @param kundenID die Kunden-ID (muss positiv sein).
     * @throws IllegalArgumentException falls die ID ungültig ist.
     */
    public void setKundenID(int kundenID) {
        if (kundenID <= 0) {
            throw new IllegalArgumentException("Die Kunden-ID muss eine positive Zahl sein.");
        }
        this.kundenID = kundenID;
    }
}
