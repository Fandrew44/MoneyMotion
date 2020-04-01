package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Category;
import model.Contact;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

//Controller for the Create Contact scene
public class ControllerCreateContact implements Initializable {

    private SceneManager sceneManager;
    private ButtonManager buttonManager;

    private File audioFile = new File("./data/ding.wav");

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

    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        buttonManager = new ButtonManager();
        buttonManager.hoverEffect(menu);
        buttonManager.hoverEffect(createContact);

        try {
            FileInputStream imageInput = new FileInputStream("./data/Logo.png");
            Image image = new Image(imageInput);
            logo.setImage(image);
        } catch (FileNotFoundException e) {
            sceneManager.quitProgram();
        }
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
        playDing();

        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }

    //EFFECTS: Plays the "ding" sound effect
    private void playDing() {
        try {
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(AudioSystem.getAudioInputStream(audioFile));
            audioClip.start();
        } catch (Exception e) {
            sceneManager.quitProgram();
        }

    }

    @FXML
    //EFFECTS: Sets Contact's transaction type to debt if user chose debt
    public void choseDebt() {
        transactionType.setText("Debt");
    }

    @FXML
    //EFFECTS: Sets Contact's transaction type to loan if user chose loan
    public void choseLoan() {
        transactionType.setText("Loan");
    }
}
