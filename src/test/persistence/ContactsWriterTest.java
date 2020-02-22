package persistence;

import model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations in the ContactsWriter class
public class ContactsWriterTest {

    public ContactsWriter cw;
    static final String TEST_FILE = "./data/TestFile.txt";

    @BeforeEach
    public void setup() throws FileNotFoundException {
        cw = new ContactsWriter(new File(TEST_FILE));
    }

    @Test
    public void testFileNotFoundExceptionNotThrown() {
        try {
            ContactsWriter cw2 = new ContactsWriter(new File(TEST_FILE));
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not be thrown here!");
        }
    }

    @Test
    public void testFileNotFoundExceptionThrown() {
        try {
            ContactsWriter cw2 = new ContactsWriter(new File(TEST_FILE));
        } catch (FileNotFoundException e) {

        }
    }

    @Test
    public void testWrite() {
        Contact jimmyBoy = new Contact("Jimmy", "boi", 13.50, 2020, 2, 2, "d");
        cw.write(jimmyBoy);
        cw.close();

        try {
            Scanner scanner = new Scanner(new File(TEST_FILE));
            ArrayList<Contact> contacts = ContactsReader.readContacts(new File(TEST_FILE));
            Contact jb = contacts.get(0);
            assertEquals("Jimmy", jb.getName());
            assertEquals("boi", jb.getDescription());
            assertEquals(13.50, jb.getTransAmount());
            assertEquals(2020, jb.getYear());
            assertEquals(2, jb.getMonth());
            assertEquals(2, jb.getDay());
            assertEquals("d", jb.getTransType());

        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not be thrown here!");
        }
    }
}
