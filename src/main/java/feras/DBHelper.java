package feras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die DBHelper-Klasse ist für die Herstellung der Verbindung zur Datenbank verantwortlich.
 */
public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/shareempdb";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789feras";

    /**
     * Stellt eine Verbindung zur Datenbank her.
     * @return Ein Connection-Objekt, das die Verbindung zur Datenbank darstellt.
     * @throws SQLException Wenn ein Fehler bei der Herstellung der Verbindung auftritt.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
