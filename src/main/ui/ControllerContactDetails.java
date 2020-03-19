package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import model.Category;
import model.Contact;

import javax.swing.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerContactDetails implements Initializable {


    //TODO: IMPLEMENT THE DEBTS, LOANS, AND NEUTRAL LIST

    private Contact contact;

    @FXML
    private SceneManager sceneManager = new SceneManager();

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label transAmountLabel;

    @FXML
    private Label transTypeLabel;

    @FXML
    private Label transDateLabel;

    @FXML
    private Button returnButton;

    @FXML
    private Button deleteContact;

    @FXML
    private TextField newTransAmount;

    @FXML
    private Button updateTransAmount;

    @FXML
    private TextField transTypeTextField;

    private String transAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager.hoverEffect(returnButton);
        sceneManager.hoverEffect(deleteContact);
        sceneManager.hoverEffect(updateTransAmount);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the labels based on contact's details
    public void init(Contact c) {
        contact = c;
        transAmount = "$" + contact.getTransAmount();
        int year = contact.getYear();
        int month = Integer.valueOf(contact.getMonth());
        int day = contact.getDay();
        String transType = contact.getTransType();
        if (transType.equals("d")) {
            transType = "Debt";
        } else if (transType.equals("l")) {
            transType = "Loan";
        } else {
            transType = "Neutral";
        }
        nameLabel.setText(contact.getName());
        descriptionLabel.setText(contact.getDescription());
        transAmountLabel.setText(transAmount);
        transTypeLabel.setText(transType);
        transDateLabel.setText(month +  "/" + day + "/" + year);
        returnButton.setText("Return to " + transType + "s");
        centerText();
    }

    //EFFECTS: Centers all the text in their respective labels within Scenebuilder
    public void centerText() {
        nameLabel.setContentDisplay(ContentDisplay.CENTER);
        descriptionLabel.setContentDisplay(ContentDisplay.CENTER);
        transAmountLabel.setContentDisplay(ContentDisplay.CENTER);
        transTypeLabel.setContentDisplay(ContentDisplay.CENTER);
        transDateLabel.setContentDisplay(ContentDisplay.CENTER);
    }

    @FXML
    //EFFECTS: Changes the scene to the specified transtype scene
    public void returnScene(ActionEvent actionEvent) throws IOException {
        String debts = "d";
        String loans = "l";
        String transType = contact.getTransType();
        if (debts.equals(transType)) {
            sceneManager.updateScene(sceneManager.debtsScene, actionEvent);
        } else if (loans.equals(transType)) {
            sceneManager.updateScene(sceneManager.loansScene, actionEvent);
        } else {
            sceneManager.updateScene(sceneManager.neutralScene, actionEvent);
        }
    }

    @FXML
    //TODO: IMPLEMENT THIS
    //MODIFIES: this
    //EFFECTS: Removes the contact from their corresponding category
    public void deleteContact(ActionEvent actionEvent) throws IOException {
        removeContact();
        returnScene(actionEvent);
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Changes the contact's transamount to newTransAmount
    public void updateTransAmount() {
        String transType = transTypeTextField.getText();
        String debt = "Debt";
        String loan = "Loan";
        String neutral = "Neutral";
        if (debt.equals(transType) || loan.equals(transType) || neutral.equals(transType)) {
            transAmount = "$" + newTransAmount.getText();
            transAmountLabel.setText(transAmount);
            Double updatedTransAmount = Double.parseDouble(newTransAmount.getText());
            contact.setTransAmount(updatedTransAmount);
            centerText();
        } else {
            transTypeTextField.setText("Valid Type Required");
        }
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Changes the contact's transType to "d" after choosing Debt from the drop-down menu
    //         and moves them into the Debts Category
    public void choseDebt() {
        transTypeTextField.setText("Debt");
        contact.setTransType("d");
        removeContact();
        DataState.putIntoCategory(contact);
        String transType = contact.getTransType();
        if (transType.equals("d")) {
            returnButton.setText("Return to Debts");
        } else if (transType.equals("l")) {
            returnButton.setText("Return to Loans");
        } else {
            returnButton.setText("Return to Neutral");
        }
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Changes the contact's transType to "l" after choosing L from the drop-down menu
    //         and moves them into the Loans Category
    public void choseLoan() {
        Double transAmount = Double.parseDouble(newTransAmount.getText());

        if (transAmount == 0) {
            transTypeTextField.setText("Error: non-zero amount");
        } else {
            transTypeTextField.setText("Loan");
            contact.setTransType("l");
            removeContact();
            DataState.putIntoCategory(contact);
            String transType = contact.getTransType();
            if (transType.equals("d")) {
                returnButton.setText("Return to Debts");
            } else if (transType.equals("l")) {
                returnButton.setText("Return to Loans");
            } else {
                returnButton.setText("Return to Neutral");
            }
        }

    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Changes the contact's transType to "n" after choosing Neutral from the drop-down menu
    //         and moves them into the Neutral Category
    public void choseNeutral() {
        String transType = transTypeTextField.getText();
        Double transAmount = Double.parseDouble(newTransAmount.getText());

        if (transAmount == 0) {
            transTypeTextField.setText("Neutral");
            contact.setTransType("n");
            removeContact();
            DataState.putIntoCategory(contact);
            if (transType.equals("d")) {
                returnButton.setText("Return to Debts");
            } else if (transType.equals("l")) {
                returnButton.setText("Return to Loans");
            } else {
                returnButton.setText("Return to Neutral");
            }
        } else {
            transTypeTextField.setText("Error: amount not 0");
        }

    }

    //MODIFIES: this
    //EFFECTS: Removes the contact from the current Category they are in
    private void removeContact() {
        String transType = transTypeLabel.getText();
        Category debts = DataState.getState().getDebts();
        Category loans = DataState.getState().getLoans();
        Category neutral = DataState.getState().getNeutral();

        if (transType.equals("Debt")) {
            debts.removeContact(contact);
        } else if (transType.equals("Loan")) {
            loans.removeContact(contact);
        } else {
            neutral.removeContact(contact);
        }
    }
}
