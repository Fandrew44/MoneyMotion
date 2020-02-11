package ui;

import model.*;

import java.util.LinkedList;
import java.util.Scanner;

public class Display {
    private Category neutral;
    private Category debts;
    private Category loans;
    private Scanner scanner;

    //TODO: taken from TellerApp
    //EFFECTS: runs the application
    public Display() {
        beginDisplay();
    }

    //TODO: taken from TellerApp
    //MODIFIES: this
    //EFFECTS: processes the user input
    public void beginDisplay() {
        instantiateCategories();

        System.out.println("Welcome to MoneyMotion!");

        runDisplay();
    }

    //TODO: SPECIFY THIS PLS
    public void runDisplay() {
        boolean continueRunning = true;
        String input = null;

        while (continueRunning) {
            System.out.println("Please select an option: ");
            System.out.println("\t[c] <-- Create a Contact");
            System.out.println("\t[s] <-- Select a Category");
            System.out.println("\t[q] <-- Quit");
            input = scanner.nextLine();
            input.toLowerCase();

            if (input.equals("c")) {
                createContact();
            } else if (input.equals("s")) {
                chooseCategory();
            } else {
                continueRunning = false;
                System.out.println("Thank you, come again.");
            }
        }
    }

    //TODO: Reference the TellerApp
    //MODIFIES: this
    //EFFECTS: initializes the categories
    public void instantiateCategories() {
        neutral = new Neutral();
        debts = new Debts();
        loans = new Loans();
        scanner = new Scanner(System.in);
    }

    //TODO: SPECIFY THIS PLS SIR
    public String provideContactsName() {
        System.out.println("What is the name of the Contact?");
        String name = scanner.nextLine();
        return name;
    }

    //TODO: BRUH IT MUST BE 25 LINES OR LESS
    //MODIFIES: this
    //EFFECTS: Creates a new Contact
    public void createContact() {
        String name = provideContactsName();
        System.out.println("Provide a quick description of the Contact");
        String description = scanner.nextLine();
        System.out.println("How much money was involved in the transaction?");
        double transAmount = scanner.nextDouble();
        System.out.println("Debt or loan? (from your perspective)");
        System.out.println("\t[d] <-- Debt");
        System.out.println("\t[l] <-- Loaned");
        scanner.nextLine();
        String transType = scanner.nextLine();
        System.out.println("When did the transaction occur?");
        System.out.println("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Month (1 - January, 2 - February, etc.): ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Day (of the Month): ");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Contact has been created!");

        Contact c1 = new Contact(name, description, transAmount, year, month, day, transType);

        putIntoCategory(c1);
    }

    //MODIFIES: this
    //EFFECTS: Adds c to the specific category depending on its trans type
    public void putIntoCategory(Contact c) {
        String type = c.getTransType();
        if (type.equals("d")) {
            debts.addContact(c);
        } else if (type.equals("l")) {
            loans.addContact(c);
        } else {
            neutral.addContact(c);
        }
    }

    //TODO: SPECIFY THIS SHIT
    public void chooseCategory() {
        System.out.println("Pick a Category: ");
        System.out.println("\t[d] <-- Debts");
        System.out.println("\t[l] <-- Loans");
        System.out.println("\t[n] <-- Neutral ");
        System.out.println("Or return to the Menu with [m]");
        String category = scanner.nextLine();

        if (category.equals("m")) {
            returnToMenu();
        } else {
            displayCategory(category);
        }
    }

    //TODO: SPECIFY THIS DOG SHIT
    public void displayCategory(String category) {
        System.out.println("Select an option: ");
        System.out.println("\t[1] <-- Displays all Contacts in this Category");
        System.out.println("\t[2] <-- Search for a Contact by name");
        int input = scanner.nextInt();
        scanner.nextLine();

        if (category.equals("d")) {
            displayDebts(input);
        } else if (category.equals("l")) {
            displayLoans(input);
        } else {
            displayNeutral(input);
        }
    }

    //TODO: PLS SPECIFY THIS
    public void displayDebts(int input) {
        if (input == 1) {
            displayContactsDebts();
        } else {
            System.out.println("Contact's name: ");
            String name = scanner.nextLine();
            findContactDebts(name);
        }
    }

    //TODO: SPECIFY THIS DUDE
    public void displayLoans(int input) {
        if (input == 1) {
            displayContactsLoans();
        } else {
            System.out.println("Contact's name:");
            String name = scanner.nextLine();
            findContactsLoans(name);
        }
    }

    //TODO: SPECIFY THIS HSIH FEIS FESIHFESIFHESIFHESIFJESIFJSEIJ
    public void displayNeutral(int input) {
        if (input == 1) {
            displayContactsNeutral();
        } else {
            System.out.println("Contact's name:");
            String name = scanner.nextLine();
            findContactsNeutral(name);
        }
    }


    //TODO: SPECIFY THIS
    public void displayContactsDebts() {
        LinkedList<Contact> debtsList = debts.getContactsList();
        String contactNames = "";

        for (Contact c : debtsList) {
            String name = c.getName();
            contactNames += name + "\n";
        }
        System.out.println(contactNames);
        returnToCategories();
    }

    //TODO: PLS SPECIFY THIS MAN
    public void displayContactsLoans() {
        LinkedList<Contact> loansList = loans.getContactsList();
        String contactNames = "";

        for (Contact c : loansList) {
            String name = c.getName();
            contactNames += name + "\n";
        }
        System.out.println(contactNames);
        returnToCategories();
    }

    //TODO: SPECIFY THIS TOO DUD
    public void displayContactsNeutral() {
        LinkedList<Contact> neutralList = neutral.getContactsList();
        String contactNames = "";

        for (Contact c : neutralList) {
            String name = c.getName();
            contactNames += name + "\n";
        }
        System.out.println(contactNames);
        returnToCategories();
    }

    //TODO: SPECIFY THIS SHIT MAN WHA ARE U DOING
    public void findContactDebts(String name) {
        LinkedList<Contact> debtsList = debts.contactsList;
        Contact contact = null;
        if (debts.isContactInList(name)) {
            for (Contact c : debtsList) {
                if (c.getName().equals(name)) {
                    contact = c;
                }
            }
            contactDetailsDebts(contact);
        } else {
            System.out.println("There is no Contact with that name in this Category.");
        }
    }

    //TODO: E
    public void findContactsLoans(String name) {
        LinkedList<Contact> loansList = loans.contactsList;
        Contact contact = null;
        if (loans.isContactInList(name)) {
            for (Contact c : loansList) {
                if (c.getName().equals(name)) {
                    contact = c;
                }
            }
            contactDetailsLoans(contact);
        } else {
            System.out.println("There is no Contact with that name in this Category.");
        }
    }

    //TODO: B
    public void findContactsNeutral(String name) {
        LinkedList<Contact> neutralList = neutral.contactsList;
        Contact contact = null;
        if (neutral.isContactInList(name)) {
            for (Contact c : neutralList) {
                if (c.getName().equals(name)) {
                    contact = c;
                }
            }
            contactDetailsNeutral(contact);
        } else {
            System.out.println("There is no Contact with that name in this Category.");
        }
    }

    //TODO: IM BEGGING USEUGSEUGSEUG
    public void selectOptionsContactDetails() {
        System.out.println("Select an option: ");
        System.out.println("\t[1] <-- Details of the Contact");       //ABSTRACT
        System.out.println("\t[2] <-- Remove the Contact");
        System.out.println("\t[3] <-- Update the Contact's Transaction Amount");
        System.out.println("\t[4] <-- Return to Categories");                       //ABSTRACT
    }

    //TODO: SPECIFY MAN
    public void contactDetailsDebts(Contact c) {
        selectOptionsContactDetails();
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            getDetails(c);
        } else if (input == 2) {
            removeContactsDebts(c);
        } else if (input == 3) {
            updateTransactionAmountDebts(c);
        } else {
            returnToCategories();
        }
    }

    //TODO: egg
    public void contactDetailsLoans(Contact c) {
        selectOptionsContactDetails();
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            getDetails(c);
        } else if (input == 2) {
            removeContactsLoans(c);
        } else if (input == 3) {
            updateTransactionAmountLoans(c);
        } else {
            returnToCategories();
        }
    }

    //TODO: BREAD
    public void contactDetailsNeutral(Contact c) {
        selectOptionsContactDetails();
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            getDetails(c);
        } else if (input == 2) {
            removeContactsNeutral(c);
        } else if (input == 3) {
            updateTransactionAmountNeutral(c);
        } else {
            returnToCategories();
        }
    }

    //TODO: SPECIFY THIS PLS DUD
    public void returnToCategories() {
        chooseCategory();
    }

    //TODO: SPEICYF I SDO SFISJFISJ
    public void returnToMenu() {
        runDisplay();
    }

    //TODO: SPIEFJIFSIFJSEFSFSIOIFWJFOIJOJIWOIJWOIWOIFOIWFI
    public void removeContactsDebts(Contact c) {
        debts.removeContact(c);
    }

    //TODO: sdasidjadijadaidSADDA
    public void removeContactsLoans(Contact c) {
        loans.removeContact(c);
    }

    //TODO: SDIASJDAIJDAIDJAIJIj
    public void removeContactsNeutral(Contact c) {
        neutral.removeContact(c);
    }

    //TODO: DON'T RUSH
    public void addContactToNeutral(Contact c) {
        neutral.addContact(c);
    }

    //TODO: IASDIAJDIADJA
    public void addContactToDebts(Contact c) {
        debts.addContact(c);
    }

    //TODO: ITS 1:01
    public void addContactToLoans(Contact c) {
        loans.addContact(c);
    }

    //TODO: GOOD SHIT LETS GOOOOOO
    public void debtsToNeutral(Contact c) {
        if (c.getTransAmount() == 0) {
            removeContactsDebts(c);
            addContactToNeutral(c);
        }
    }

    //TODO: GRIND DONT STOP
    public void loansToNeutral(Contact c) {
        if (c.getTransAmount() == 0) {
            removeContactsLoans(c);
            addContactToNeutral(c);
        }
    }

    //TODO: sadidjaidajJDAJSIDAIDJA
    public void getDetails(Contact c) {
        int year = c.getYear();
        int month = Integer.valueOf(c.getMonth());
        int day = c.getDay();
        String transType = c.getTransType();
        System.out.println("Name: " + c.getName());
        System.out.println("Description: " + c.getDescription());
        System.out.println("Transaction Amount: " + c.getTransAmount());
        System.out.println("Date the Transaction occurred: " + year + "/" + month + "/" + day);
        if (transType.equals("d")) {
            System.out.println("Transaction Type: Debt");
        } else if (transType.equals("l")) {
            System.out.println("Transaction Type: Loan");
        } else {
            System.out.println("Transaction Type: Neutral");
        }
    }

    //TODO: NICE U GOT THIS
    public void updateTransactionAmountOptions() {
        System.out.println("Would you like to increase or decrease the Transaction Amount?");
        System.out.println("[d] <-- Decrease");
        System.out.println("[i] <-- Increase");
    }

    //TODO: PLS PLS PLS ~~ABSTRACT~~ THIS UPDATETRANSACTIONAMOUNTDEBTS ITS SO FUCKIN BIG

    //TODO: IM BEGGING U
    public void updateTransactionAmountDebts(Contact c) {
        updateTransactionAmountOptions();
        String input = scanner.nextLine();

        if (input.equals("d")) {
            System.out.println("How much would you like to decrease the Contact's Transaction Amount by?");
            System.out.println("Note: The Contact's current Transaction Amount is " + c.getTransAmount());
            double amount = scanner.nextDouble();
            scanner.nextLine();
            c.decTransAmount(amount);
            debtsToNeutral(c);
        } else {
            System.out.println("How much would you like to increase the Contact's Transaction Amount by?");
            System.out.println("Note: The Contact's current Transaction amount is " + c.getTransAmount());
            double amount = scanner.nextDouble();
            scanner.nextLine();
            c.incTransAmount(amount);
        }
    }

    public void updateTransactionAmountLoans(Contact c) {
        updateTransactionAmountOptions();
        String input = scanner.nextLine();

        if (input.equals("d")) {
            System.out.println("How much would you like to decrease the Contact's Transaction Amount by?");
            System.out.println("Note: The Contact's current Transaction Amount is " + c.getTransAmount());
            double amount = scanner.nextDouble();
            scanner.nextLine();
            c.decTransAmount(amount);
            loansToNeutral(c);
        } else {
            System.out.println("How much would you like to increase the Contact's Transaction Amount by?");
            System.out.println("Note: The Contact's current Transaction amount is " + c.getTransAmount());
            double amount = scanner.nextDouble();
            scanner.nextLine();
            c.incTransAmount(amount);
        }
    }

    public void updateTransactionAmountNeutral(Contact c) {
        updateTransactionAmountOptions();
        String input = scanner.nextLine();

        System.out.println("How much would you like to increase the Contact's Transaction Amount by?");
        System.out.println("Note: The Contact's current Transaction amount is " + c.getTransAmount());
        double amount = scanner.nextDouble();
        scanner.nextLine();
        c.incTransAmount(amount);
        System.out.println("Debt or Loan?");
        System.out.println("[d] <-- Debt");
        System.out.println("[l] <-- Loan");
        String type = scanner.nextLine();

        neutralToCategory(c, type);

    }

    //TODO: DO IT LETS GOOOOO
    public void neutralToCategory(Contact c, String type) {
        if (type.equals("d")) {
            removeContactsNeutral(c);
            addContactToDebts(c);
        } else {
            removeContactsNeutral(c);
            addContactToLoans(c);
        }
    }
}



