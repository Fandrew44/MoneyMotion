package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations in the Loans class
public class LoansTest {

    private Category loans;
    private Contact c1;

    @BeforeEach
    public void setup() {
        loans = new Loans();
        c1 = new Contact("Jimmy", "Cool jim", 55, 2020, 3, 20, "l");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, loans.getContactsListSize());
        assertTrue(loans.isEmpty());
    }

    @Test
    public void testTotalFinancesWithContact() {
        loans.addContact(c1);
        assertEquals(55, loans.totalFinances());
    }

    @Test
    public void testTotalFinancesNoContact() {
        assertEquals(0, loans.totalFinances());
    }

}
