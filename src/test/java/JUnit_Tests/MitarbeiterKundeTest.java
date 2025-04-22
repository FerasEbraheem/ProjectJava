package JUnit_Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.MitarbeiterKunde;

class MitarbeiterKundeTest {

    @Test
    void testValidMitarbeiterKunde() {
        // Erstellen eines gültigen Objekts
        MitarbeiterKunde mitarbeiterKunde = new MitarbeiterKunde(1001, 2001);

        // Überprüfung, ob die Werte korrekt gespeichert wurden
        assertEquals(1001, mitarbeiterKunde.getMitarbeiterID(), "Die Mitarbeiter-ID sollte 1001 sein.");
        assertEquals(2001, mitarbeiterKunde.getKundenID(), "Die Kunden-ID sollte 2001 sein.");
    }

    @Test
    void testSetInvalidMitarbeiterID() {
        MitarbeiterKunde mitarbeiterKunde = new MitarbeiterKunde();

        // Überprüfen, ob eine negative ID eine Exception auslöst
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mitarbeiterKunde.setMitarbeiterID(-5);
        });

        assertEquals("Die Mitarbeiter-ID muss eine positive Zahl sein.", exception.getMessage());
    }

    @Test
    void testSetInvalidKundenID() {
        MitarbeiterKunde mitarbeiterKunde = new MitarbeiterKunde();

        // Überprüfen, ob eine negative Kunden-ID eine Exception auslöst
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mitarbeiterKunde.setKundenID(0);
        });

        assertEquals("Die Kunden-ID muss eine positive Zahl sein.", exception.getMessage());
    }
}
//javac -cp "libs/*" src/main/java/feras/Person.java
//javac -cp "libs/*;src/main/java" src/main/java/feras/MitarbeiterKunde.java
//javac -cp "libs/*;src/main/java;src/test/java" src/test/java/JUnit_Tests/MitarbeiterKundeTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.MitarbeiterKundeTest

//-----------------------------------------------------------------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test
