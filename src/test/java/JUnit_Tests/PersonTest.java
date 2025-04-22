package JUnit_Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feras.Person;

/**
 * Testklasse für die Person.
 */
public class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        // Erstellen eines Unterobjekts von Person zur Durchführung des Tests
        person = new Person() {
            @Override
            public String toString() {
                return "Person{name=" + getVorname() + " " + getNachname() + "}";
            }
        };
    }

    @Test
    public void testSetAndGetVorname() {
        person.setVorname("John");  // Setter verwenden
        assertEquals("John", person.getVorname(), "Der Vorname sollte John sein.");  // Getter verwenden
    }

    @Test
    public void testSetAndGetNachname() {
        person.setNachname("Doe");
        assertEquals("Doe", person.getNachname(), "Der Nachname sollte Doe sein.");
    }

    @Test
    public void testSetAndGetStrasse() {
        person.setStrasse("Main Street 123");
        assertEquals("Main Street 123", person.getStrasse(), "Die Straße sollte Main Street 123 sein.");
    }

    @Test
    public void testSetAndGetPlz() {
        person.setPlz("12345");
        assertEquals("12345", person.getPlz(), "Die Postleitzahl sollte 12345 sein.");
    }

    @Test
    public void testSetAndGetOrt() {
        person.setOrt("Berlin");
        assertEquals("Berlin", person.getOrt(), "Die Stadt sollte Berlin sein.");
    }

    @Test
    public void testSetAndGetTelefon() {
        person.setTelefon("123-456-7890");
        assertEquals("123-456-7890", person.getTelefon(), "Die Telefonnummer sollte 123-456-7890 sein.");
    }

    @Test
    public void testSetAndGetEmail() {
        person.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", person.getEmail(), "Die E-Mail-Adresse sollte john.doe@example.com sein.");
    }

    @Test
    public void testFullPersonDetails() {
        // Alle Werte setzen
        person.setVorname("Jane");
        person.setNachname("Smith");
        person.setStrasse("Elm Street 45");
        person.setPlz("54321");
        person.setOrt("München");
        person.setTelefon("987-654-3210");
        person.setEmail("jane.smith@example.com");

        // Alle Werte überprüfen
        assertEquals("Jane", person.getVorname(), "Der Vorname sollte Jane sein.");
        assertEquals("Smith", person.getNachname(), "Der Nachname sollte Smith sein.");
        assertEquals("Elm Street 45", person.getStrasse(), "Die Straße sollte Elm Street 45 sein.");
        assertEquals("54321", person.getPlz(), "Die Postleitzahl sollte 54321 sein.");
        assertEquals("München", person.getOrt(), "Die Stadt sollte München sein.");
        assertEquals("987-654-3210", person.getTelefon(), "Die Telefonnummer sollte 987-654-3210 sein.");
        assertEquals("jane.smith@example.com", person.getEmail(), "Die E-Mail-Adresse sollte jane.smith@example.com sein.");
    }
}

//javac -cp "libs/*" src/main/java/feras/Person.java src/test/java/JUnit_Tests/PersonTest.java
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --select-class JUnit_Tests.PersonTest
//--------------------------------------
//java -cp "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\junit-platform-console-standalone-1.8.2.jar;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\libs\*;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\main\java;C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\src\test\java" org.junit.platform.console.ConsoleLauncher --scan-classpath
//mvn test
