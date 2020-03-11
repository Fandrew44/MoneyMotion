package model;

import java.util.LinkedList;

//Represents all the contacts that the user has lent money to
public class Loans extends Category {

    private int totalLoans;

    public Loans() {
        contactsList = new LinkedList<Contact>();
    }

    @Override
    public double totalFinances() {
        double totalAmount = 0.00;
        for (Contact c : contactsList) {
            totalAmount += c.getTransAmount();
        }
        return totalAmount;
    }

    //Methods are inherited

}
