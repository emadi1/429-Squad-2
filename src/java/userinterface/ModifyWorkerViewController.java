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
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph and Ders on 3/22/2017.
 */
public class ModifyWorkerViewController implements Initializable{

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private ObservableList<String> statusList =
            FXCollections.observableArrayList(language.getProperty("Active"), language.getProperty("Inactive"));
    private ObservableList<String> credentialsList =
            FXCollections.observableArrayList(language.getProperty("Administrator"), language.getProperty("Ordinary"));

    @FXML private Text bannerId, password, firstName, lastName, contactPhone, email, alertMessage;
    @FXML private Text credentials, dateOfLatestCredentialsStatus, dateOfHire, status;
    @FXML private TextField BannerId, Password, FirstName, LastName, ContactPhone;
    @FXML private TextField Email, DateOfLatestCredentialsStatus, DateOfHire;
    @FXML private ComboBox<String> Status, Credentials;
    @FXML private Button submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setText(language.getProperty("Modify"));
        bannerId.setText(language.getProperty("PromptBannerId"));
        password.setText(language.getProperty("PromptPassword"));
        firstName.setText(language.getProperty("PromptFirstName"));
        lastName.setText(language.getProperty("PromptLastName"));
        contactPhone.setText(language.getProperty("PromptContactPhone"));
        email.setText(language.getProperty("PromptEmail"));
        credentials.setText(language.getProperty("PromptCredentials"));
        dateOfLatestCredentialsStatus.setText(language.getProperty("PromptDateOfLatestCredentialsStatus"));
        dateOfHire.setText(language.getProperty("PromptDateOfHire"));
        status.setText(language.getProperty("PromptStatus"));

        BannerId.setText(core.getModWorker().getBannerId());
        BannerId.setDisable(true);
        Password.setText(core.getModWorker().getPassword());
        FirstName.setText(core.getModWorker().getFirstName());
        LastName.setText(core.getModWorker().getLastName());
        ContactPhone.setText(core.getModWorker().getContactPhone());
        Email.setText(core.getModWorker().getEmail());
        if (core.getUser().getCredentials().equals("Ordinary"))
            Credentials.setDisable(true);
        Credentials.setValue(core.getModWorker().getCredentials());
        DateOfLatestCredentialsStatus.setText(core.getModWorker().getDateOfLatestCredentialsStatus());
        DateOfHire.setText(core.getModWorker().getDateOfHire());
        Status.setValue(core.getModWorker().getStatus());
        Credentials.setItems(credentialsList);
        Status.setItems(statusList);
    }

    public void submit(ActionEvent event) {
        try {
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
            alertMessage.setText(language.getProperty("modifyWorkerSuccess"));
        } catch (Exception e) {
            alertMessage.setText(language.getProperty("modifyWorkerFail"));
        }
    }
}