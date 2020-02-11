package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoansTest {

    private Category loans;

    @BeforeEach
    public void setup() {
        loans = new Loans();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, loans.getContactsListSize());
        assertTrue(loans.isEmpty());
    }

}
