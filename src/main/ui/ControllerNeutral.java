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

//Controller for the Neutral scene
public class ControllerNeutral implements Initializable {

    private SceneManager sceneManager;

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

        contactDetails = DataState.getState().getNeutral();
        LinkedList<Contact> neutralList = contactDetails.getContactsList();

        for (Contact c : neutralList) {
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
