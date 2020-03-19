package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Category;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerCreateContact implements Initializable {

    private SceneManager sceneManager;

    //TODO: IMPLEMENT FIELDS FOR TEXT BOXES
    //TODO: IMPLEMENT A FIELD FOR DATE

    @FXML
    private Button menu;

    @FXML
    private Button createContact;

    @FXML
    private TextField transactionType;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField transAmountField;

    @FXML
    private DatePicker transDateField;

    private Category debts;
    private Category loans;
    private Category neutral;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        sceneManager.hoverEffect(menu);
        sceneManager.hoverEffect(createContact);
        debts = DataState.getState().getDebts();
        loans = DataState.getState().getLoans();
        neutral = DataState.getState().getNeutral();
    }

    @FXML
    //EFFECTS: Changes scene to menu scene
    public void returnToMenu(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Creates a new contact based on user inputs and adds it to the appropriate category
    public void createContact(ActionEvent actionEvent) throws IOException {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double transAmount = Double.parseDouble(transAmountField.getText());
        LocalDate transDate = transDateField.getValue();
        int year = transDate.getYear();
        int month = transDate.getMonthValue();
        int day = transDate.getDayOfMonth();
        String transType = transactionType.getText();
        String transTypeField = "";
        if (transType.equals("Debt")) {
            transTypeField = "d";
        } else {
            transTypeField = "l";
        }

        Contact newContact = new Contact(name, description, transAmount, year, month, day, transTypeField);
        DataState.putIntoCategory(newContact);

        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }

    @FXML
    //EFFECTS: Sets Contact's transaction type to debt if user chose debt
    public void choseDebt() {
        transactionType.setText("Debt");
        //TODO: IMPLEMENT THIS WITH THE CONTACT CONSTRUCTOR SOMEHOW?????????
        //TODO: HELPER METHOD THAT RETURNS STRING????
    }

    @FXML
    //EFFECTS: Sets Contact's transaction type to loan if user chose loan
    public void choseLoan() {
        transactionType.setText("Loan");
        //TODO: IMPLEMENT THIS WITH THE CONTACT CONSTRUCTOR SOMEHOW????????
        //TODO: HELPER METHOD THAT RETURNS STRING????
    }
}
