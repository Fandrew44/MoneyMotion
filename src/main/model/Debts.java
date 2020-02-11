package model;

import java.time.LocalDate;
import java.util.LinkedList;

//Represents all the Contacts that the user is indebted to
public class Debts extends Category {

    private int totalDebt;

    public Debts() {
        contactsList = new LinkedList<Contact>();
    }

    //Methods are inherited

}
