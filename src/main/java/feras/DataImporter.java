package feras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 * Die DataImporter-Klasse importiert Daten aus XML-Dateien in die Datenbank.
 */
public class DataImporter {

    /**
     * Importiert Mitarbeiterdaten aus einem XML-Dokument in die Tabelle Mitarbeiter.
     * @param conn Die Verbindung zur Datenbank.
     * @param doc Das XML-Dokument, das die Mitarbeiterdaten enthält.
     * @throws SQLException Wenn ein Fehler beim Einfügen auftritt.
     */
    public static void importMitarbeiter(Connection conn, Document doc) throws SQLException {
        String mitarbeiterSQL = "INSERT INTO Mitarbeiter (MitarbeiterID, Vorname, Nachname, Straße, PLZ, Ort, Telefon, Email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Vorname = VALUES(Vorname), Nachname = VALUES(Nachname), " +
                "Straße = VALUES(Straße), PLZ = VALUES(PLZ), Ort = VALUES(Ort), Telefon = VALUES(Telefon), Email = VALUES(Email)";
        try (PreparedStatement pstmt = conn.prepareStatement(mitarbeiterSQL)) {
            NodeList list = doc.getElementsByTagName("person");
            // Wir nehmen an, dass die Personen in <mitarbeiter> hier importiert werden
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                // Wenn das Element Teil von <mitarbeiter> und nicht <externe_mitarbeiter> ist, kann es durch den Kontext unterschieden werden
                // In diesem Beispiel nehmen wir an, dass die Mitarbeiter nach dem Import der Daten aus <mitarbeiter> importiert werden

                String id = getTagContent(element, "MitarbeiterID");
                String vorname = getTagContent(element, "Vorname");
                String nachname = getTagContent(element, "Nachname");
                String strasse = getTagContent(element, "Straße");
                String plz = getTagContent(element, "PLZ");
                String ort = getTagContent(element, "Ort");
                String telefon = getTagContent(element, "Telefon");
                String email = getTagContent(element, "Email");

                if (id != null && vorname != null && nachname != null) {
                    pstmt.setInt(1, Integer.parseInt(id));
                    pstmt.setString(2, vorname);
                    pstmt.setString(3, nachname);
                    pstmt.setString(4, strasse);
                    pstmt.setString(5, plz);
                    pstmt.setString(6, ort);
                    pstmt.setString(7, telefon);
                    pstmt.setString(8, email);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    /**
     * Importiert Firmendaten aus einem XML-Dokument in die Tabelle Firmen.
     * @param conn Die Verbindung zur Datenbank.
     * @param doc Das XML-Dokument, das die Firmendaten enthält.
     * @throws SQLException Wenn ein Fehler beim Einfügen auftritt.
     */
    public static void importFirmen(Connection conn, Document doc) throws SQLException {
        String sql = "INSERT INTO Firmen (FirmaID, Name, Adresse) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Name = VALUES(Name), Adresse = VALUES(Adresse)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            NodeList list = doc.getElementsByTagName("firma");
            for (int i = 0; i < list.getLength(); i++){
                Element element = (Element) list.item(i);
                String id = getTagContent(element, "FirmaID");
                String name = getTagContent(element, "Name");
                String adresse = getTagContent(element, "Adresse");
                if(id != null && name != null && adresse != null) {
                    pstmt.setInt(1, Integer.parseInt(id));
                    pstmt.setString(2, name);
                    pstmt.setString(3, adresse);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    /**
     * Importiert Daten von externen Mitarbeitern aus einem XML-Dokument in die Tabelle Externe_Mitarbeiter.
     * @param conn Die Verbindung zur Datenbank.
     * @param doc Das XML-Dokument, das die Daten der externen Mitarbeiter enthält.
     * @throws SQLException Wenn ein Fehler beim Einfügen auftritt.
     */
    public static void importExterneMitarbeiter(Connection conn, Document doc) throws SQLException {
        String sql = "INSERT INTO Externe_Mitarbeiter (ExterneID, Vorname, Nachname, Straße, PLZ, Ort, Telefon, Email, FirmaID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Vorname = VALUES(Vorname), Nachname = VALUES(Nachname), " +
                "Straße = VALUES(Straße), PLZ = VALUES(PLZ), Ort = VALUES(Ort), Telefon = VALUES(Telefon), " +
                "Email = VALUES(Email), FirmaID = VALUES(FirmaID)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Wir nehmen an, dass die Daten der externen Mitarbeiter innerhalb des Elements <externe_mitarbeiter> vorhanden sind
            NodeList externeNodes = doc.getElementsByTagName("externe_mitarbeiter");
            if (externeNodes.getLength() > 0) {
                Element externeParent = (Element) externeNodes.item(0);
                NodeList list = externeParent.getElementsByTagName("person");
                for (int i = 0; i < list.getLength(); i++){
                    Element element = (Element) list.item(i);
                    String id = getTagContent(element, "ExterneID");
                    String vorname = getTagContent(element, "Vorname");
                    String nachname = getTagContent(element, "Nachname");
                    String strasse = getTagContent(element, "Straße");
                    String plz = getTagContent(element, "PLZ");
                    String ort = getTagContent(element, "Ort");
                    String telefon = getTagContent(element, "Telefon");
                    String email = getTagContent(element, "Email");
                    String firmaID = getTagContent(element, "FirmaID");
                    if (id != null && vorname != null && nachname != null && strasse != null &&
                            plz != null && ort != null && telefon != null && email != null && firmaID != null) {
                        pstmt.setInt(1, Integer.parseInt(id));
                        pstmt.setString(2, vorname);
                        pstmt.setString(3, nachname);
                        pstmt.setString(4, strasse);
                        pstmt.setString(5, plz);
                        pstmt.setString(6, ort);
                        pstmt.setString(7, telefon);
                        pstmt.setString(8, email);
                        pstmt.setInt(9, Integer.parseInt(firmaID));
                        pstmt.executeUpdate();
                    }
                }
            }
        }
    }

    /**
     * Importiert Kundendaten aus einem XML-Dokument in die Tabelle Kunden.
     * @param conn Die Verbindung zur Datenbank.
     * @param doc Das XML-Dokument, das die Kundendaten enthält.
     * @throws SQLException Wenn ein Fehler beim Einfügen auftritt.
     */
    public static void importKunden(Connection conn, Document doc) throws SQLException {
        String sql = "INSERT INTO Kunden (KundenID, Vorname, Nachname, Straße, PLZ, Ort, Telefon, Email, Branche) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Vorname = VALUES(Vorname), Nachname = VALUES(Nachname), " +
                "Straße = VALUES(Straße), PLZ = VALUES(PLZ), Ort = VALUES(Ort), Telefon = VALUES(Telefon), " +
                "Email = VALUES(Email), Branche = VALUES(Branche)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            NodeList list = doc.getElementsByTagName("kunde");
            for (int i = 0; i < list.getLength(); i++){
                Element element = (Element) list.item(i);
                String kundenID = getTagContent(element, "KundenID");
                String vorname = getTagContent(element, "Vorname");
                String nachname = getTagContent(element, "Nachname");
                String strasse = getTagContent(element, "Straße");
                String plz = getTagContent(element, "PLZ");
                String ort = getTagContent(element, "Ort");
                String telefon = getTagContent(element, "Telefon");
                String email = getTagContent(element, "Email");
                String branche = getTagContent(element, "Branche");
                if (kundenID != null && vorname != null && nachname != null && strasse != null &&
                        plz != null && ort != null && telefon != null && email != null && branche != null) {
                    pstmt.setInt(1, Integer.parseInt(kundenID));
                    pstmt.setString(2, vorname);
                    pstmt.setString(3, nachname);
                    pstmt.setString(4, strasse);
                    pstmt.setString(5, plz);
                    pstmt.setString(6, ort);
                    pstmt.setString(7, telefon);
                    pstmt.setString(8, email);
                    pstmt.setString(9, branche);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    /**
     * Importiert Beziehungen zwischen Mitarbeitern und Kunden aus einem XML-Dokument in die Tabelle Mitarbeiter_Kunden.
     * @param conn Die Verbindung zur Datenbank.
     * @param doc Das XML-Dokument, das die Beziehungsdaten enthält.
     * @throws SQLException Wenn ein Fehler beim Einfügen auftritt.
     */
    public static void importMitarbeiterKunden(Connection conn, Document doc) throws SQLException {
        String sql = "INSERT INTO Mitarbeiter_Kunden (MitarbeiterID, KundenID) VALUES (?, ?)" +
                " ON DUPLICATE KEY UPDATE MitarbeiterID = VALUES(MitarbeiterID), KundenID = VALUES(KundenID)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            NodeList mkNodes = doc.getElementsByTagName("mitarbeiter_kunden");
            if (mkNodes.getLength() > 0) {
                Element mkParent = (Element) mkNodes.item(0);
                NodeList list = mkParent.getElementsByTagName("beziehung");
                for (int i = 0; i < list.getLength(); i++){
                    Element element = (Element) list.item(i);
                    String mitarbeiterID = getTagContent(element, "MitarbeiterID");
                    String kundenID = getTagContent(element, "KundenID");
                    if (mitarbeiterID != null && kundenID != null) {
                        pstmt.setInt(1, Integer.parseInt(mitarbeiterID));
                        pstmt.setInt(2, Integer.parseInt(kundenID));
                        pstmt.executeUpdate();
                    }
                }
            }
        }
    }

    /**
     * Hilfsmethode zum Extrahieren des Inhalts eines XML-Elements.
     * @param e Das Element.
     * @param tagName Der Tagname.
     * @return Der Textinhalt des Elements oder null, wenn nicht vorhanden.
     */
    private static String getTagContent(Element e, String tagName) {
        NodeList list = e.getElementsByTagName(tagName);
        if (list.getLength() > 0)
            return list.item(0).getTextContent();
        return null;
    }
}
