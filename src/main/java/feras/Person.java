package feras;

import jakarta.xml.bind.annotation.XmlElement;

/**
 * Die abstrakte Klasse Person fasst gemeinsame Eigenschaften aller Personen zusammen.
 * Enthalten sind Attribute wie Vorname, Nachname, Straße, PLZ, Ort, Telefon und Email.
 * Diese Klasse dient als Basis für spezifischere Personentypen wie Mitarbeiter, ExterneMitarbeiter und Kunde.
 */
public abstract class Person {

    private  String vorname;
    private  String nachname;
    private  String strasse;
    private  String plz;
    private  String ort;
    private  String telefon;
    private  String email;

    /**
     * Gibt den Vornamen der Person zurück.
     * @return den Vornamen.
     */
    @XmlElement
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen der Person.
     * @param vorname der Vorname.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen der Person zurück.
     * @return den Nachnamen.
     */
    @XmlElement
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen der Person.
     * @param nachname der Nachname.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt die Straße der Person zurück.
     * @return die Straße.
     */
    @XmlElement
    public String getStrasse() {
        return strasse;
    }

    /**
     * Setzt die Straße der Person.
     * @param strasse die Straße.
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Gibt die Postleitzahl der Person zurück.
     * @return die Postleitzahl.
     */
    @XmlElement
    public String getPlz() {
        return plz;
    }

    /**
     * Setzt die Postleitzahl der Person.
     * @param plz die Postleitzahl.
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * Gibt den Ort der Person zurück.
     * @return den Ort.
     */
    @XmlElement
    public String getOrt() {
        return ort;
    }

    /**
     * Setzt den Ort der Person.
     * @param ort der Ort.
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Gibt die Telefonnummer der Person zurück.
     * @return die Telefonnummer.
     */
    @XmlElement
    public String getTelefon() {
        return telefon;
    }

    /**
     * Setzt die Telefonnummer der Person.
     * @param telefon die Telefonnummer.
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Gibt die E-Mail-Adresse der Person zurück.
     * @return die E-Mail-Adresse.
     */
    @XmlElement
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse der Person.
     * @param email die E-Mail-Adresse.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
