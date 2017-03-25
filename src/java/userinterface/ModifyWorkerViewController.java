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
import utilities.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/22/2017.
 */
public class ModifyWorkerViewController implements Initializable{

    private ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    private ObservableList<String> credentialsList = FXCollections.observableArrayList("Administrator", "Ordinary");
    private Core core = Core.getInstance();

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

        BannerId.setText(core.getModWorker().getBannerId());
        BannerId.setDisable(true);
        Password.setText(core.getModWorker().getPassword());
        FirstName.setText(core.getModWorker().getFirstName());
        LastName.setText(core.getModWorker().getLastName());
        ContactPhone.setText(core.getModWorker().getContactPhone());
        Email.setText(core.getModWorker().getEmail());
        if (core.getUser().getCredentials().equals("Ordinary")) {
            Credentials.setDisable(true);
        }
        Credentials.setValue(core.getModWorker().getCredentials());
        DateOfLatestCredentialsStatus.setText(core.getModWorker().getDateOfLatestCredentialsStatus());
        DateOfHire.setText(core.getModWorker().getDateOfHire());
        Status.setValue(core.getModWorker().getStatus());
        Credentials.setItems(credentialsList);
        Status.setItems(statusList);
    }

    public void submit(ActionEvent event) {
        alertMessage.setText("");
        Worker worker = core.getModWorker();
        worker.setPassword(Password.getText());
        worker.setFirstName(FirstName.getText());
        worker.setLastName(LastName.getText());
        worker.setContactPhone(ContactPhone.getText());
        worker.setEmail(Email.getText());
        worker.setCredentials(Credentials.getValue());
        worker.setDateOfLatestCredentialsStatus(DateOfLatestCredentialsStatus.getText());
        worker.setDateOfHire(DateOfHire.getText());
        worker.setStatus(Status.getValue());
        worker.update();
        System.out.println(worker.toString());
        alertMessage.setText("Worker successfully updated");
    }
}