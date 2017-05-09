package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.Worker;
import utilities.Core;
import utilities.PWEncrypt;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph and Ders on 3/22/2017.
 */
public class ModifyWorkerViewController implements Initializable{

    private String frenchDate = Core.formatDateToEnglish(Core.generateDate());
    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private ObservableList<String> statusList =
            FXCollections.observableArrayList(language.getProperty("Active"), language.getProperty("Inactive"));
    private ObservableList<String> credentialsList =
            FXCollections.observableArrayList(language.getProperty("Administrator"), language.getProperty("Ordinary"));

    @FXML private Text bannerId, password, firstName, lastName, contactPhone, email, alertMessage;
    @FXML private Text credentials, dateOfLatestCredentialsStatus, dateOfHire, status;
    @FXML private TextField BannerId, Password, FirstName, LastName, ContactPhone, CountryCode;
    @FXML private TextField Email, DateOfLatestCredentialsStatus, DateOfHire;
    @FXML private ComboBox<String> Status, Credentials;
    @FXML private Button submit;

    private PWEncrypt pwEncrypt = PWEncrypt.getInstance();

    public ModifyWorkerViewController() throws GeneralSecurityException {
    }

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
        try {
            Password.setText(pwEncrypt.decryptKicker(core.getModWorker().getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        FirstName.setText(core.getModWorker().getFirstName());
        LastName.setText(core.getModWorker().getLastName());
        ContactPhone.setText(core.getModWorker().getContactPhone().substring(4));
        CountryCode.setText(core.getModWorker().getContactPhone().substring(0,3));
        Email.setText(core.getModWorker().getEmail());
        Credentials.setItems(credentialsList);
        Status.setItems(statusList);
        if (core.getUser().getCredentials().equals("Ordinary"))
            Credentials.setDisable(true);
        Credentials.setValue(core.getModWorker().getCredentials());
        Status.setValue(core.getModWorker().getStatus());
        DateOfLatestCredentialsStatus.setText(core.getModWorker().getDateOfLatestCredentialsStatus());
        //DateOfLatestCredentialsStatus.setDisable(true);
        DateOfHire.setText(core.getModWorker().getDateOfHire());
        //DateOfHire.setDisable(true);
    }

    public void submit(ActionEvent event) {
        try {
            alertMessage.setText("");

            String phoneNum = ContactPhone.getText();
            Worker worker = core.getModWorker();

            if (phoneNum.contains("-")) {
                phoneNum = phoneNum.replaceAll("-", "");
            }

            phoneNum = CountryCode.getText() + "-" + phoneNum;

            worker.setPassword(pwEncrypt.encryptKicker(Password.getText()));
            worker.setFirstName(FirstName.getText());
            worker.setLastName(LastName.getText());
//            worker.setContactPhone(ContactPhone.getText());

            if (phoneNum.length() != 14 || phoneNum.charAt(3) != '-' ||
                    !Core.isNumeric(phoneNum.substring(0, 3)) || !Core.isNumeric(phoneNum.substring(5))) {
                alertMessage.setText(language.getProperty("invalidPhoneFormat"));
                alertMessage.setFill(Paint.valueOf("dcc404"));
                return;
            } else worker.setContactPhone(phoneNum);

            worker.setEmail(Email.getText());
            worker.setDateOfLatestCredentialsStatus(DateOfLatestCredentialsStatus.getText());
            worker.setDateOfHire(DateOfHire.getText());
            if (Credentials.getValue().equals(language.getProperty("Administrator")))
                worker.setCredentials("Administrator");
            else worker.setCredentials("Ordinary");
            if (!core.getModWorker().getCredentials().equals(worker.getCredentials())) {
                String date = Core.generateDate();
                if (core.getLanguage().equals("fr_FR"))
                    worker.setDateOfLatestCredentialsStatus(Core.formatDateToEnglish(date));
                else worker.setDateOfLatestCredentialsStatus(date);
            }
            if (Status.getValue().equals(language.getProperty("Active")))
                worker.setStatus("Active");
            else worker.setStatus("Inactive");
            worker.update();
            alertMessage.setText(language.getProperty("modifyWorkerSuccess"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
            core.getTableView().refresh();
        } catch (Exception e) {
            alertMessage.setText(language.getProperty("modifyWorkerFail"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
        }
    }
}