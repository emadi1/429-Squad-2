package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/22/2017.
 */
public class AddWorkerViewController implements Initializable {

    @FXML private Text bannerId;
    @FXML private Text password;
    @FXML private Text firstName;
    @FXML private Text lastName;
    @FXML private Text contactPhone;
    @FXML private Text email;
    @FXML private Text credentials;
    @FXML private Text dateOfLatestCredentialsStatus;
    @FXML private Text dateOfHire;
    @FXML private Text status;
    @FXML private TextField BannerId;
    @FXML private TextField Password;
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private TextField ContactPhone;
    @FXML private TextField Email;
    @FXML private ComboBox<String> Credentials;
    @FXML private TextField DateOfLatestCredentialsStatus;
    @FXML private TextField DateOfHire;
    @FXML private ComboBox<String> Status;
    @FXML private Button submit;
    @FXML private Text alertMessage;
    Core core = Core.getInstance();
    ArrayList<TextField> textFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Properties lang = core.getLanguageFile();
        ObservableList<String> statusList = FXCollections.observableArrayList(lang.getProperty("Active"), lang.getProperty("Inactive"));
        ObservableList<String> credentialsList = FXCollections.observableArrayList(lang.getProperty("Administrator"), lang.getProperty("Ordinary"));
        submit.setText(lang.getProperty("Add"));
        bannerId.setText(lang.getProperty("BannerID"));
        password.setText(lang.getProperty("Password"));
        firstName.setText(lang.getProperty("FirstName"));
        lastName.setText(lang.getProperty("LastName"));
        contactPhone.setText(lang.getProperty("ContactPhone"));
        email.setText(lang.getProperty("Email"));
        credentials.setText(lang.getProperty("Credentials"));
        dateOfLatestCredentialsStatus.setText(lang.getProperty("DateOfLatestCredentialsStatus"));
        dateOfHire.setText(lang.getProperty("DateOfHire"));
        status.setText(lang.getProperty("Status"));
        textFieldList = new ArrayList<>();
        textFieldList.add(BannerId);
        textFieldList.add(Password);
        textFieldList.add(FirstName);
        textFieldList.add(LastName);
        textFieldList.add(ContactPhone);
        textFieldList.add(Email);
        Credentials.setValue(lang.getProperty("Ordinary"));
        Credentials.setItems(credentialsList);
        textFieldList.add(DateOfLatestCredentialsStatus);
        textFieldList.add(DateOfHire);
        Status.setValue(lang.getProperty("Active"));
        Status.setItems(statusList);
    }


    public void submit(ActionEvent event) {

        Properties prop = new Properties();
        alertMessage.setText("");
        for (TextField textField : textFieldList) {

            if (textField.getText().equals("")) {
                alertMessage.setText("Please complete all fields");
                return;
            } else {
                prop.put(textField.getId(), textField.getText());
            }
        }
        prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
        prop.put(Credentials.getId(), Credentials.getSelectionModel().getSelectedItem());
        WorkerCollection workerCollection = new WorkerCollection();
        int count = workerCollection.findWorkersByBannerId(prop.getProperty("BannerId")).size();
        if (count == 0) {
            Worker newWorker = new Worker(prop);
            newWorker.update();
            alertMessage.setText("Worker has been submitted");
        } else {
            alertMessage.setText("Worker with BannerID: " + prop.getProperty("BannerID") + " already exists");
        }
        for (TextField t : textFieldList) { t.clear(); }
    }
}