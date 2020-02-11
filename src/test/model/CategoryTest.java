package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    public Category c;
    public Contact jeff;
    public Contact jon;
    public Contact jim;

    @BeforeEach
    public void setup() {
        c = new Debts();
        jeff = new Contact("Jeff", "A student at UBC studying economics.", 55.00, 2020, 2, 1, "d");
        jon = new Contact("Jon", "A software engineer working at Tesla.", 75.00, 2020, 1, 26, "l");
        jim = new Contact("Jim", "A high school freshmen who likes volleyball.", 37.50, 2020, 1, 16, "n");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, c.getContactsListSize());
        assertFalse(c.isContactInList("Jeff"));
        assertTrue(c.isEmpty());
        assertEquals("d", jeff.getTransType());
        assertEquals("l", jon.getTransType());
        assertEquals("n", jim.getTransType());
    }

    @Test
    public void testAddContact() {
        assertFalse(c.isContactInList("Jeff"));
        assertTrue(c.isEmpty());
        c.addContact(jeff);

        assertEquals(1, c.getContactsListSize());
        assertTrue(c.isContactInList("Jeff"));
        assertFalse(c.isContactInList("Jim"));
        assertFalse(c.isEmpty());
    }

    @Test
    public void testRemoveContact() {
        assertFalse(c.isContactInList("Jon"));
        c.addContact(jon);
        assertTrue(c.isContactInList("Jon"));

        c.removeContact(jon);
        assertFalse(c.isContactInList("Jon"));
    }

    @Test
    public void testGetContactsListOne() {
        c.addContact(jim);
        assertEquals("Jim", c.getContactsList().get(0).getName());
    }

    @Test
    public void testGetContactsListMultiple() {
        c.addContact(jeff);
        c.addContact(jon);
        c.addContact(jim);

        assertEquals("Jeff", c.getContactsList().get(0).getName());
        assertEquals("Jon", c.getContactsList().get(1).getName());
        assertEquals("Jim", c.getContactsList().get(2).getName());
    }

    @Test
    public void testFindContact() {
        assertFalse(c.isContactInList("Jeff"));
        assertFalse(c.isContactInList("Jon"));
        assertFalse(c.isContactInList("Jim"));

        c.addContact(jeff);
        c.addContact(jon);

        assertTrue(c.isContactInList("Jeff"));
        assertTrue(c.isContactInList("Jon"));
        assertFalse(c.isContactInList("Jim"));
    }




}
