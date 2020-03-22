package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations in the Debts class
public class DebtsTest {

    private Category debts;
    private Contact c1;

    @BeforeEach
    public void setup() {
        debts = new Debts();
        c1 = new Contact("Dan", "the man", 45, 2020, 3, 21, "l");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, debts.getContactsListSize());
        assertTrue(debts.isEmpty());
    }

    @Test
    public void testTotalFinancesWithContact() {
        debts.addContact(c1);
        assertEquals(-45, debts.totalFinances());
    }

    @Test
    public void testTotalFinancesNoContact() {
        assertEquals(0, debts.totalFinances());
    }
}
