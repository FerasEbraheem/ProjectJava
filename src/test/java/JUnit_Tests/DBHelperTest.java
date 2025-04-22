package JUnit_Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Die Testklasse für die DBHelper-Klasse.
 * Sie überprüft, ob eine Verbindung zur Datenbank erfolgreich hergestellt werden kann.
 */
class DBHelperTest {

    /**
     * Testet die Verbindung zur Datenbank.
     * <p>
     * Dieser Test stellt sicher, dass die Methode {@link feras.DBHelper#getConnection()}
     * eine gültige Verbindung zurückgibt.
     * </p>
     *
     * Erfolgreiche Kriterien:
     * <ul>
     *     <li>Die Verbindung darf nicht {@code null} sein.</li>
     *     <li>Die Verbindung sollte geöffnet sein.</li>
     * </ul>
     */
    @Test
    void testDatabaseConnection() {
        try (Connection connection = DBHelper.getConnection()) {
            assertNotNull(connection, "Die Verbindung sollte nicht null sein.");
            assertFalse(connection.isClosed(), "Die Verbindung sollte geöffnet sein.");
        } catch (SQLException e) {
            fail("Fehler beim Herstellen der Verbindung: " + e.getMessage());
        }
    }
}

//javac -cp "libs/*" src/main/java/feras/DBHelper.java
//javac -cp "libs/*;src/main/java;src/test/java" src/test/java/JUnit_Tests/DBHelperTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.DBHelperTest

//-----------------------------------------------------------------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test
