package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ControllerLoans implements Initializable {

    private SceneManager sceneManager;

    //TODO: IMPLEMENT THE LOANS LIST

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

    //TODO: WHEN IMPLEMENTING, REPLACING THE "contactDetails" Field with the "debts" FIELDS
    @FXML
    private Category contactDetails;

    @FXML
    private Button menu;

    @FXML
    private Button category;

    //TODO: INSPIRATION TAKEN FROM YOUTUBE VIDEO: https://www.youtube.com/watch?v=FeTrcNBVWtg
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
        //dates.setCellValueFactory(new PropertyValueFactory<Contact, String>("date"));
        //contacts.setCellValueFactory(new PropertyValueFactory<Contact, Button>("Contact"));

//        //TODO: REMOVE THESE DURING PRODUCTION
//        //Creates contacts to visualize if things are working as intended
//        Contact c1 = new Contact("Wash", "cool guy", 56.55, 2020, 3, 13, "d");
//        Contact c2 = new Contact("Eric", "tall guy", 75.87, 2020, 1, 15, "d");
//        Contact c3 = new Contact("Pam", "pokemon trainer", 45.47, 2020, 2, 17, "d");
//        Contact c4 = new Contact("Daniel", "good cop", 30, 2020, 3, 5, "d");

//        //TODO: WHEN IMPLEMENTING, REPLACING THE "contactDetails" Field with the "debts" FIELDS
//        contactDetails.add(c1);
//        contactDetails.add(c2);
//        contactDetails.add(c3);
//        contactDetails.add(c4);

        contactDetails = DataState.getState().getLoans();
        LinkedList<Contact> loansList = contactDetails.getContactsList();
        for (Contact c : loansList) {
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
