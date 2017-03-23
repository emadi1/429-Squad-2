package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.Worker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/22/2017.
 */
public class WorkerDataFieldViewController implements Initializable{

    private ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    private ObservableList<String> credentialsList = FXCollections.observableArrayList("Administrator", "Ordinary");

    @FXML private TextField ContactPhone;
    @FXML private ComboBox<String> Status;
    @FXML private TextField Email;
    @FXML private Button submit;
    @FXML private TextField DateOfHire;
    @FXML private TextField FirstName;
    @FXML private TextField BannerId;
    @FXML private TextField LastName;
    @FXML private TextField DateOfLatestCredentialsStatus;
    @FXML private ComboBox<String> Credentials;
    @FXML private TextField Password;
    @FXML private Text alertMessage;

    ArrayList<TextField> textFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(BannerId);
        textFieldList.add(Password);
        textFieldList.add(FirstName);
        textFieldList.add(LastName);
        textFieldList.add(ContactPhone);
        textFieldList.add(Email);
        Credentials.setValue("Ordinary");
        Credentials.setItems(credentialsList);
        textFieldList.add(DateOfLatestCredentialsStatus);
        textFieldList.add(DateOfHire);
        Status.setValue("Active");
        Status.setItems(statusList);
    }


    public void submit(ActionEvent event) {

        Properties prop = new Properties();

        alertMessage.setText("");

        // TODO field checks
        for (TextField textField : textFieldList) {

            if (textField.getText().equals("")) {
                alertMessage.setText("Please complete all fields");
                return;
            }
            else {
                prop.put(textField.getId(), textField.getText());
            }
        }

        prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
        prop.put(Credentials.getId(), Credentials.getSelectionModel().getSelectedItem());

        Worker newWorker = new Worker(prop);
        newWorker.update();

        alertMessage.setText("Worker has been submitted");

        for (TextField t : textFieldList) { t.clear(); }

    }

    public void modify(ActionEvent event) {

        Properties prop = new Properties();

        alertMessage.setText("");

        // TODO field checks
        for (TextField textField : textFieldList) {

            if (textField.getText().equals("")) {
                alertMessage.setText("Please complete all fields");
                return;
            }
            else {
                prop.put(textField.getId(), textField.getText());
            }
        }

        prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
        prop.put(Credentials.getId(), Credentials.getSelectionModel().getSelectedItem());

        Worker newWorker = new Worker(prop);
        newWorker.update();

        alertMessage.setText("Worker has been modified");

        for (TextField t : textFieldList) { t.clear(); }



    }

}






