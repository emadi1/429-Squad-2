package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.crypto.*;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;
import java.net.URL;
import java.util.*;

/**
 * Created by Ders & kevph on 3/22/2017.
 */
public class AddWorkerViewController implements Initializable {

    private Core core = Core.getInstance();
    private Properties lang = core.getLang();
    private ArrayList<TextField> textFieldList;

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

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] salt = new String("12345678").getBytes();
    int keyLength = 128;
    int iterations = 40000;
    String masterPassword = "thisisnotsafe";
    SecretKeySpec key = createKey(masterPassword.toCharArray(), salt, iterations, keyLength);


    public AddWorkerViewController() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> statusList =
                FXCollections.observableArrayList(lang.getProperty("Active"), lang.getProperty("Inactive"));
        ObservableList<String> credentialsList =
                FXCollections.observableArrayList(lang.getProperty("Administrator"), lang.getProperty("Ordinary"));

        // Set Text's text
        submit.setText(lang.getProperty("Add"));
        bannerId.setText(lang.getProperty("PromptBannerId"));
        password.setText(lang.getProperty("PromptPassword"));
        firstName.setText(lang.getProperty("PromptFirstName"));
        lastName.setText(lang.getProperty("PromptLastName"));
        contactPhone.setText(lang.getProperty("PromptContactPhone"));
        email.setText(lang.getProperty("PromptEmail"));
        credentials.setText(lang.getProperty("PromptCredentials"));
        dateOfLatestCredentialsStatus.setText(lang.getProperty("PromptDateOfLatestCredentialsStatus"));
        dateOfHire.setText(lang.getProperty("PromptDateOfHire"));
        status.setText(lang.getProperty("PromptStatus"));

        // Set PromptText in text fields
        BannerId.setPromptText(lang.getProperty("BannerId"));
        Password.setPromptText(lang.getProperty("Password"));
        FirstName.setPromptText(lang.getProperty("FirstName"));
        LastName.setPromptText(lang.getProperty("LastName"));
        ContactPhone.setPromptText(lang.getProperty("ContactPhone"));
        Email.setPromptText(lang.getProperty("Email"));
        DateOfLatestCredentialsStatus.setText(Core.generateDate());
        DateOfHire.setText(Core.generateDate());
        DateOfLatestCredentialsStatus.setDisable(true);
        DateOfHire.setDisable(true);

        // Add TextFields to ArrayList
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
    public void submit(ActionEvent event) throws IllegalBlockSizeException, BadPaddingException, InvalidParameterSpecException, InvalidKeyException, UnsupportedEncodingException {

        Properties prop = new Properties();
        WorkerCollection workerCollection = new WorkerCollection();
        String phoneNum = ContactPhone.getText();

        if (BannerId.getText().length() != 9) {
            alertMessage.setText(lang.getProperty("invalidBannerIdLength"));
            return;
        }

        if (DateOfHire.getText().length() != 10 || DateOfLatestCredentialsStatus.getText().length() != 10
                || DateOfHire.getText().charAt(2) != '-' || DateOfHire.getText().charAt(5) != '-'
                || DateOfLatestCredentialsStatus.getText().charAt(2) != '-'
                || DateOfLatestCredentialsStatus.getText().charAt(5) != '-') {
            alertMessage.setText(lang.getProperty("invalidDateFormat"));
            return;
        }

        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText(lang.getProperty("completeFields"));
                return;
            } else prop.put(textField.getId(), textField.getText());
        }

        if (core.getLanguage().equals("fr_FR")) {
            String hireDay = DateOfHire.getText().substring(3, 5);
            String hireMonth = DateOfHire.getText().substring(0, 2);
            String hireYear = DateOfHire.getText().substring(6);
            String credDay = DateOfLatestCredentialsStatus.getText().substring(3, 5);
            String credMonth = DateOfLatestCredentialsStatus.getText().substring(0, 2);
            String credYear = DateOfLatestCredentialsStatus.getText().substring(6);
            prop.setProperty("DateOfHire", hireDay + "-" + hireMonth + "-" + hireYear);
            prop.setProperty("DateOfLatestCredentialsStatus", credDay + "-" + credMonth + "-" + credYear);
        }

        if (phoneNum.length() != 14 || phoneNum.charAt(3) != '-' ||
                !Core.isNumeric(phoneNum.substring(0, 3)) || !Core.isNumeric(phoneNum.substring(5))) {
            alertMessage.setText(lang.getProperty("invalidPhoneFormat"));
            return;
        } else prop.put(DBKey.CONTACT_PHONE, ContactPhone.getText());

        int count = workerCollection.findWorkersByBannerId(prop.getProperty(DBKey.BANNER_ID)).size();
        if (count == 0) {
            prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
            prop.put(Credentials.getId(), Credentials.getSelectionModel().getSelectedItem());
            prop.put("Password", encrypt(Password.getText(), key, cipher));
            Worker newWorker = new Worker(prop);
            newWorker.insert();
            alertMessage.setText(lang.getProperty("addWorkerSuccess"));
        } else alertMessage.setText(lang.getProperty("existingBannerId") + prop.getProperty(DBKey.BANNER_ID));

        for (TextField t : textFieldList) t.clear();
        Credentials.setValue(lang.getProperty("Ordinary"));
        Status.setValue(lang.getProperty("Active"));
    }


    private static SecretKeySpec createKey (char[] password, byte[] salt, int iteration, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iteration, keyLength);
        SecretKey tempKey = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(tempKey.getEncoded(), "AES");
    }


    private static String encrypt(String unencryptedPass, SecretKeySpec key, Cipher cipher) throws InvalidKeyException, InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = cipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = cipher.doFinal(unencryptedPass.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }


    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}