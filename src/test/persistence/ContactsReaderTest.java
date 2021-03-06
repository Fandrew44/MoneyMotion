package persistence;

import model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations in the ContactsReader class
public class ContactsReaderTest {

    public ContactsReader cr;

    @BeforeEach
    public void setup() {
        cr = new ContactsReader();
    }

    @Test
    public void testReadContactsFileFound() {
        try {
            ArrayList<Contact> contacts = cr.readContacts(new File("./data/SavedContacts.txt"));
            Contact adrian = contacts.get(0);
            assertEquals("adrian", adrian.getName());
            assertEquals("buff kid", adrian.getDescription());
            assertEquals(50.0, adrian.getTransAmount());
            assertEquals(2020, adrian.getYear());
            assertEquals(2, adrian.getMonth());
            assertEquals(26, adrian.getDay());
            assertEquals("d", adrian.getTransType());

        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not be thrown here!");
        }
    }

    @Test
    public void testReadContactsFileNotFound() {
        try {
            ArrayList<Contact> contacts = cr.readContacts(new File("./data/ThisFileDoesNotExist.txt"));
        } catch (FileNotFoundException e) {
            //Exception expected
        }
    }

    @Test
    public void testSeparateProperties() {
        try {
            Scanner scanner = new Scanner(new File("./data/SavedContacts.txt"));
            ArrayList<String> properties = cr.separateProperties(scanner.nextLine());
            String name = properties.get(0);
            String description = properties.get(1);
            String transAmount = properties.get(2);
            String year = properties.get(3);
            String month = properties.get(4);
            String day = properties.get(5);
            String transType = properties.get(6);

        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not be thrown here!");
        }
    }

    @Test
    public void testCreateContact() {
        try {
            Scanner scanner = new Scanner(new File("./data/SavedContacts.txt"));
            ArrayList<String> properties = cr.separateProperties(scanner.nextLine());
            Contact adrian = cr.createContact(properties);

            assertEquals("adrian", adrian.getName());
            assertEquals("buff kid", adrian.getDescription());
            assertEquals(50.0, adrian.getTransAmount());
            assertEquals(2020, adrian.getYear());
            assertEquals(2, adrian.getMonth());
            assertEquals(26, adrian.getDay());
            assertEquals("d", adrian.getTransType());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not be thrown here!");
        }
    }


}
