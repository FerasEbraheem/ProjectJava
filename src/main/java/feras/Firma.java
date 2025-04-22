package feras;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Die Klasse Firma stellt eine Firma im System dar.
 * Diese Klasse enthält Informationen über die Firma, wie deren ID, Name und Adresse.
 * Sie wird mit JAXB verwendet, um Objekte in XML zu konvertieren und umgekehrt.
 */
@XmlRootElement
public class Firma {

    private int firmaID;
    private String name;
    private String adresse;

    /**
     * Gibt die ID der Firma zurück.
     *
     * @return die ID der Firma.
     */
    @XmlElement
    public int getFirmaID() {
        return firmaID;
    }

    /**
     * Setzt die ID der Firma.
     *
     * @param firmaID die ID der Firma.
     * @throws IllegalArgumentException wenn die ID ungültig ist.
     */
    public void setFirmaID(int firmaID) {
        if (firmaID <= 0) {
            throw new IllegalArgumentException("Die Firmen-ID muss eine positive Zahl sein.");
        }
        this.firmaID = firmaID;
    }

    /**
     * Gibt den Namen der Firma zurück.
     *
     * @return der Name der Firma.
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Firma.
     *
     * @param name der Name der Firma.
     * @throws IllegalArgumentException wenn der Name leer ist.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Firmenname darf nicht leer sein.");
        }
        this.name = name;
    }

    /**
     * Gibt die Adresse der Firma zurück.
     *
     * @return die Adresse der Firma.
     */
    @XmlElement
    public String getAdresse() {
        return adresse;
    }

    /**
     * Setzt die Adresse der Firma.
     *
     * @param adresse die Adresse der Firma.
     * @throws IllegalArgumentException wenn die Adresse leer ist.
     */
    public void setAdresse(String adresse) {
        if (adresse == null || adresse.trim().isEmpty()) {
            throw new IllegalArgumentException("Die Adresse der Firma darf nicht leer sein.");
        }
        this.adresse = adresse;
    }
}
