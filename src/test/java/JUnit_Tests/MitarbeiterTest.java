package JUnit_Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.Mitarbeiter;


class MitarbeiterTest {

    @Test
    void testMitarbeiterIDSetterAndGetter() {
        // Arrange
        Mitarbeiter mitarbeiter = new Mitarbeiter(12345);

        // Act
        int aktuelleID = mitarbeiter.getMitarbeiterID();

        // Assert
        assertEquals(12345, aktuelleID, "Die Mitarbeiter-ID sollte korrekt gesetzt und zurückgegeben werden.");
    }

    @Test
    void testSetMitarbeiterIDWithInvalidValue() {
        // Arrange
        Mitarbeiter mitarbeiter = new Mitarbeiter();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mitarbeiter.setMitarbeiterID(-10);
        });

        assertEquals("Die Mitarbeiter-ID muss eine positive Zahl sein.", exception.getMessage());
    }
}
//javac -cp "libs/*" src/main/java/feras/Person.java
//javac -cp "libs/*;src/main/java" src/main/java/feras/Mitarbeiter.java
//javac -cp "libs/*;src/main/java;src/test/java" src/test/java/JUnit_Tests/MitarbeiterTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.MitarbeiterTest

//-----------------------------------------------------------------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test
