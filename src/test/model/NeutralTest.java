package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations for the Neutral class
public class NeutralTest {

    private Category neutral;

    @BeforeEach
    public void setup() {
        neutral = new Neutral();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, neutral.getContactsListSize());
        assertTrue(neutral.isEmpty());
    }

    @Test
    public void testTotalFinancies() {
        assertEquals(0, neutral.totalFinances());
    }
}
