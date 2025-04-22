package feras;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Die Klasse ExterneMitarbeiter stellt einen externen Mitarbeiter im System dar.
 * Sie erbt die gemeinsamen Eigenschaften von Person und fügt
 * die spezifischen Attribute externeID und firmaID hinzu.
 */
@XmlRootElement
public class ExterneMitarbeiter extends Person {

    private int externeID;
    private int firmaID;

    /**
     * Gibt die ID des externen Mitarbeiters zurück.
     * @return die externe ID.
     */
    @XmlElement
    public int getExterneID() {
        return externeID;
    }

    /**
     * Setzt die ID des externen Mitarbeiters.
     * @param externeID die externe ID.
     * @throws IllegalArgumentException wenn die ID ungültig ist.
     */
    public void setExterneID(int externeID) {
        if (externeID <= 0) {
            throw new IllegalArgumentException("Die externe ID muss eine positive Zahl sein.");
        }
        this.externeID = externeID;
    }

    /**
     * Gibt die ID der Firma zurück, bei der der externe Mitarbeiter beschäftigt ist.
     * @return die Firmen-ID.
     */
    @XmlElement
    public int getFirmaID() {
        return firmaID;
    }

    /**
     * Setzt die ID der Firma des externen Mitarbeiters.
     * @param firmaID die Firmen-ID.
     * @throws IllegalArgumentException wenn die ID ungültig ist.
     */
    public void setFirmaID(int firmaID) {
        if (firmaID <= 0) {
            throw new IllegalArgumentException("Die Firmen-ID muss eine positive Zahl sein.");
        }
        this.firmaID = firmaID;
    }
}