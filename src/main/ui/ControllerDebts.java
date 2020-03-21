package ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Category;
import model.Contact;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

//Controller for the debts scene
public class ControllerDebts implements Initializable {

    private SceneManager sceneManager;

    //TODO: IMPLEMENT THE DEBTS LIST

    @FXML
    private TableView<Contact> tableView;

    //The search text field
    @FXML
    private TextField filteredField;

    @FXML
    private TableColumn<Contact, String> names;

    @FXML
    private TableColumn<Contact, String> transAmounts;


    @FXML
    private TableColumn<Contact, String> contacts;

    @FXML
    private Category contactDetails;

    @FXML
    private Button menu;

    @FXML
    private Button category;

    //Observable list to store data
    private final ObservableList<Contact> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneManager = new SceneManager();
        sceneManager.hoverEffect(menu);
        sceneManager.hoverEffect(category);

        //Associating the data model property value to the column ??????????????????????????
        names.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        transAmounts.setCellValueFactory(new PropertyValueFactory<Contact, String>("transAmount"));
        contacts.setCellValueFactory(new PropertyValueFactory<Contact, String>("button"));

        contactDetails = DataState.getState().getDebts();
        LinkedList<Contact> debtsList = contactDetails.getContactsList();

        for (Contact c : debtsList) {
            dataList.add(c);
            Button b = c.getButton();
            b.setOnAction(event -> {
                try {
                    sceneManager.assignAction(event, c);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        //Wrap the ObservableList in a FilteredList (while initially displaying ALL the contacts)
        sceneManager.createSearchBar(dataList, filteredField, tableView);
    }

    @FXML
    //EFFECTS: Changes the scene to the menu scene
    public void menu(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes the scene to the categories scene
    public void categories(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.categoriesScene, actionEvent);
    }
}

