package JUnit_Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.ExterneMitarbeiter;

class ExterneMitarbeiterTest {

    @Test
    void testValidExterneMitarbeiter() {
        ExterneMitarbeiter externeMitarbeiter = new ExterneMitarbeiter();

        // Set valid values
        externeMitarbeiter.setExterneID(123);
        externeMitarbeiter.setFirmaID(456);

        // Check if values are set correctly
        assertEquals(123, externeMitarbeiter.getExterneID(), "Die externe ID sollte 123 sein.");
        assertEquals(456, externeMitarbeiter.getFirmaID(), "Die Firmen-ID sollte 456 sein.");
    }

    @Test
    void testSetInvalidExterneID() {
        ExterneMitarbeiter externeMitarbeiter = new ExterneMitarbeiter();

        // Trying to set an invalid ExterneID (negative)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            externeMitarbeiter.setExterneID(-1);
        });

        assertEquals("Die externe ID muss eine positive Zahl sein.", exception.getMessage());
    }

    @Test
    void testSetInvalidFirmaID() {
        ExterneMitarbeiter externeMitarbeiter = new ExterneMitarbeiter();

        // Trying to set an invalid FirmaID (zero)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            externeMitarbeiter.setFirmaID(0);
        });

        assertEquals("Die Firmen-ID muss eine positive Zahl sein.", exception.getMessage());
    }
}
//javac -cp "libs/*;src/main/java" src/main/java/feras/ExterneMitarbeiter.java
//javac -cp "libs/*;src/main/java" src/test/java/JUnit_Tests/ExterneMitarbeiterTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.ExterneMitarbeiterTest
//---------------------------------------------------------------------------------------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test
