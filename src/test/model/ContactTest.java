package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests operations in the Contact class
public class ContactTest {
    Contact c;

    @BeforeEach
    public void setup() {
        c = new Contact("Joe", "An economic fellow.", 35.00, 2020, 2, 4, "d");
    }

    @Test
    public void testConstructor() {
        assertEquals("Joe", c.getName());
        assertEquals("An economic fellow.", c.getDescription());
        assertEquals(35.00, c.getTransAmount());
        assertEquals(2020, c.getYear());
        assertEquals(2, c.getMonth());
        assertEquals(4, c.getDay());
        assertEquals("d", c.getTransType());
        assertEquals(" ", c.getStatus());
    }

    @Test
    public void testSetters() {
        c.setName("Doug");
        c.setDescription("A frugal fellow.");
        c.setDate(2020, 2, 5);
        c.setTransAmount(50.00);
        c.setTransType("test trans type");
        c.setStatus("test status");

        assertEquals("Doug", c.getName());
        assertEquals("A frugal fellow.", c.getDescription());
        assertEquals(50.00, c.getTransAmount());
        assertEquals(2020, c.getYear());
        assertEquals(2, c.getMonth());
        assertEquals(5, c.getDay());
        assertEquals("test trans type", c.getTransType());
        assertEquals("test status", c.getStatus());
    }

    @Test
    public void testIncTransAmountZero() {
        assertEquals(35.00, c.getTransAmount());
        c.incTransAmount(0);

        assertEquals(35.00, c.getTransAmount());
    }

    @Test
    public void testIncTransAmountMoreThanZero() {
        assertEquals(35.00, c.getTransAmount());
        c.incTransAmount(45.00);

        assertEquals(80.00, c.getTransAmount());
    }

    @Test
    public void testDecTransAmountZero() {
        assertEquals(35.00, c.getTransAmount());
        c.decTransAmount(0);

        assertEquals(35.00, c.getTransAmount());
    }

    @Test
    public void testDecTransAmountMoneyLeftover() {
        assertEquals(35.00, c.getTransAmount());
        c.decTransAmount(25.00);

        assertEquals(10.00, c.getTransAmount());
    }

    @Test
    public void testDecTransAmountNoMoney() {
        assertEquals(35.00, c.getTransAmount());
        c.decTransAmount(35.00);

        assertEquals(0, c.getTransAmount());
    }

    @Test
    public void testUpdateTransTypeSame() {
        assertEquals("d", c.getTransType());

        c.updateTransType("d");
        assertEquals("d", c.getTransType());
    }

    @Test
    public void testUpdateTransTypeDifferentOnce() {
        assertEquals("d", c.getTransType());

        c.updateTransType("n");
        assertEquals("n", c.getTransType());
    }

    @Test
    public void testUpdateTransTypeMultiple() {
        assertEquals("d", c.getTransType());

        c.updateTransType("l");
        assertEquals("l", c.getTransType());

        c.updateTransType("d");
        assertEquals("d", c.getTransType());

        c.updateTransType("n");
        assertEquals("n", c.getTransType());
    }

    @Test
    public void testToString() {
        assertEquals("$35.00", c.toString());
    }


}