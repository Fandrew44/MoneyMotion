package ui;

import model.*;
import persistence.ContactsReader;
import persistence.ContactsWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

//A singleton class representing all the fields for the program
public class DataState {

    private static Category debts;
    private static Category loans;
    private static Category neutral;

    private static File saveFILE = new File("./data/SavedContacts.txt");

    private static DataState instance;

    //MODIFIES: this
    //EFFECTS: Initializes the instance
    public static void init() {
        instance = new DataState();
    }

    //EFFECTS: Returns the instance
    public static DataState getState() {
        return instance;
    }

    //MODIFIES: this
    //EFFECTS: Initializes the fields
    private DataState() {
        debts = new Debts();
        loans = new Loans();
        neutral = new Neutral();
        loadContacts(saveFILE);
    }

    //EFFECTS: Returns the debts
    public Category getDebts() {
        return debts;
    }

    //EFFECTS: Returns the loans
    public Category getLoans() {
        return loans;
    }

    //EFFECT: Returns the neutral
    public Category getNeutral() {
        return neutral;
    }

    //Inspiration taken from the TellerApp
    //EFFECTS: Loads contacts from file
    public void loadContacts(File file) {
        ArrayList<Contact> contacts = null;
        try {
            contacts = ContactsReader.readContacts(saveFILE);
        } catch (FileNotFoundException e) {
            System.exit(0);
        }

        for (Contact c : contacts) {
            putIntoCategory(c);
        }
    }


    //MODIFIES: this
    //EFFECTS: Places c into the appropriate category
    public static void putIntoCategory(Contact c) {
        Double transAmount = c.getTransAmount();
        String type = c.getTransType();
        if (transAmount == 0) {
            neutral.addContact(c);
            return;
        }
        if (type.equals("d")) {
            debts.addContact(c);
        } else if (type.equals("l")) {
            loans.addContact(c);
        } else {
            neutral.addContact(c);
        }
    }

    //Inspiration taken from the TellerApp
    //EFFECTS: Writes all Contacts and their properties to FILE
    public static void saveContacts() throws FileNotFoundException {
        ContactsWriter cw = null;
        cw = new ContactsWriter(saveFILE);
        for (Contact c : debts.getContactsList()) {
            cw.write(c);
        }

        for (Contact c : loans.getContactsList()) {
            cw.write(c);
        }

        for (Contact c : neutral.getContactsList()) {
            cw.write(c);
        }
        cw.close();
    }

    //EFFECTS: Returns the total financial situation of the user
    public static String printFinancialSituation() {
        String totalFinancesMessage = "";
        double totalFinances = loans.totalFinances() + debts.totalFinances();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String amount = numberFormat.format(totalFinances);
        if (totalFinances < 0) {
            totalFinancesMessage = "-" + amount;
        } else {
            totalFinancesMessage = "+" + amount;
        }
        return totalFinancesMessage;
    }
}
