package feras;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Die Klasse `Kunde` repräsentiert einen Kunden im System.
 * Sie erbt die gemeinsamen Eigenschaften von der Klasse `Person` und fügt die Felder "Branche" sowie die "Kunden-ID" hinzu.
 * Die Daten werden durch Kapselung geschützt, und die Werte werden nur über öffentliche Getter- und Setter-Methoden zugänglich gemacht.
 */
@XmlRootElement
public class Kunde extends Person {

    private int kundenID;  // private - Kann nur über Getter und Setter zugegriffen werden.
    private String branche; // private - Kann nur über Getter und Setter zugegriffen werden.

    /**
     * Gibt die Kunden-ID zurück.
     * @return die Kunden-ID.
     */
    @XmlElement
    public int getKundenID() {
        return kundenID;
    }

    /**
     * Setzt die Kunden-ID. Die ID muss eine positive Zahl sein.
     * @param kundenID die Kunden-ID.
     * @throws IllegalArgumentException wenn die Kunden-ID ungültig ist (<= 0).
     */
    public void setKundenID(int kundenID) {
        if (kundenID <= 0) {
            throw new IllegalArgumentException("Die Kunden-ID muss eine positive Zahl sein.");
        }
        this.kundenID = kundenID;
    }

    /**
     * Gibt die Branche des Kunden zurück.
     * @return die Branche des Kunden.
     */
    @XmlElement
    public String getBranche() {
        return branche;
    }

    /**
     * Setzt die Branche des Kunden. Die Branche darf nicht leer sein.
     * @param branche die Branche des Kunden.
     * @throws IllegalArgumentException wenn die Branche leer ist.
     */
    public void setBranche(String branche) {
        if (branche == null || branche.trim().isEmpty()) {
            throw new IllegalArgumentException("Die Branche darf nicht leer sein.");
        }
        this.branche = branche;
    }
}
