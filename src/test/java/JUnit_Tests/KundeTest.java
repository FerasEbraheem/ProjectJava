package JUnit_Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.Kunde;

class KundeTest {

    /**
     * Dieser Test überprüft, ob ein Kunde mit gültigen Daten korrekt erstellt wird.
     */
    @Test
    void testValidKunde() {
        // Erstellen eines gültigen Objekts
        Kunde kunde = new Kunde();
        kunde.setKundenID(1001);
        kunde.setBranche("Technologie");

        // Überprüfung, ob die Werte korrekt gespeichert wurden
        assertEquals(1001, kunde.getKundenID(), "Die Kunden-ID sollte 1001 sein.");
        assertEquals("Technologie", kunde.getBranche(), "Die Branche sollte 'Technologie' sein.");
    }

    /**
     * Dieser Test überprüft, ob eine Ausnahme ausgelöst wird, wenn eine ungültige Kunden-ID gesetzt wird (negative Zahl).
     */
    @Test
    void testSetInvalidKundenID() {
        Kunde kunde = new Kunde();

        // Überprüfen, ob eine Ausnahme geworfen wird, wenn die Kunden-ID negativ ist
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            kunde.setKundenID(-5);
        });

        assertEquals("Die Kunden-ID muss eine positive Zahl sein.", exception.getMessage());
    }

    /**
     * Dieser Test überprüft, ob eine Ausnahme ausgelöst wird, wenn die Branche leer gesetzt wird.
     */
    @Test
    void testSetEmptyBranche() {
        Kunde kunde = new Kunde();

        // Überprüfen, ob eine Ausnahme geworfen wird, wenn die Branche leer ist
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            kunde.setBranche("");
        });

        assertEquals("Die Branche darf nicht leer sein.", exception.getMessage());
    }

    /**
     * Dieser Test überprüft, ob eine Ausnahme ausgelöst wird, wenn die Branche auf null gesetzt wird.
     */
    @Test
    void testSetNullBranche() {
        Kunde kunde = new Kunde();

        // Überprüfen, ob eine Ausnahme geworfen wird, wenn die Branche null ist
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            kunde.setBranche(null);
        });

        assertEquals("Die Branche darf nicht leer sein.", exception.getMessage());
    }
}
//javac -cp "libs/*;src/main/java" src/main/java/feras/Kunde.java
//javac -cp "libs/*;src/main/java" src/test/java/JUnit_Tests/KundeTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.KundeTest

//--------------------------------------------------------------------------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test