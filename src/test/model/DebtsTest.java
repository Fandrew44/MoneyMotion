package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations in the Debts class
public class DebtsTest {

    private Category debts;

    @BeforeEach
    public void setup() {
        debts = new Debts();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, debts.getContactsListSize());
        assertTrue(debts.isEmpty());
    }
}
