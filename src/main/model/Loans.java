package model;

import java.util.LinkedList;

//Represents all the contacts that the user has lent money to
public class Loans extends Category {

    private int totalLoans;

    public Loans() {
        contactsList = new LinkedList<Contact>();
    }

    //Methods are inherited

}
