package JUnit_Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.Firma;

class FirmaTest {

    @Test
    void testValidFirma() {
        Firma firma = new Firma();

        // Set valid values
        firma.setFirmaID(123);
        firma.setName("Firma XYZ");
        firma.setAdresse("Musterstr. 1, 12345 Musterstadt");

        // Check if values are set correctly
        assertEquals(123, firma.getFirmaID(), "Die Firmen-ID sollte 123 sein.");
        assertEquals("Firma XYZ", firma.getName(), "Der Firmenname sollte 'Firma XYZ' sein.");
        assertEquals("Musterstr. 1, 12345 Musterstadt", firma.getAdresse(), "Die Adresse sollte 'Musterstr. 1, 12345 Musterstadt' sein.");
    }

    @Test
    void testSetInvalidFirmaID() {
        Firma firma = new Firma();

        // Trying to set an invalid FirmenID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            firma.setFirmaID(-1);
        });

        assertEquals("Die Firmen-ID muss eine positive Zahl sein.", exception.getMessage());
    }

    @Test
    void testSetInvalidName() {
        Firma firma = new Firma();

        // Trying to set an invalid Name (empty)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            firma.setName("");
        });

        assertEquals("Der Firmenname darf nicht leer sein.", exception.getMessage());
    }

    @Test
    void testSetInvalidAdresse() {
        Firma firma = new Firma();

        // Trying to set an invalid Adresse (empty)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            firma.setAdresse("");
        });

        assertEquals("Die Adresse der Firma darf nicht leer sein.", exception.getMessage());
    }
}
//javac -cp "libs/*;src/main/java" src/main/java/feras/Firma.java
//javac -cp "libs/*;src/main/java" src/test/java/JUnit_Tests/FirmaTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.FirmaTest
//-------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test
