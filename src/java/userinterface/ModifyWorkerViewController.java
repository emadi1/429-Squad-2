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

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    @FXML private TextField BannerId, Password, FirstName, LastName, ContactPhone;
    @FXML private TextField Email, DateOfLatestCredentialsStatus, DateOfHire;
    @FXML private ComboBox<String> Status, Credentials;
    @FXML private Button submit;

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] salt = new String("12345678").getBytes();
    int keyLength = 128;
    int iterations = 40000;
    String masterPassword = "thisisnotsafe";
    SecretKeySpec key = createKey(masterPassword.toCharArray(), salt, iterations, keyLength);

    public ModifyWorkerViewController() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
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
            Password.setText(decrypt(core.getModWorker().getPassword(), key, cipher));
        } catch (Exception e) {
            e.printStackTrace();
        }
        FirstName.setText(core.getModWorker().getFirstName());
        LastName.setText(core.getModWorker().getLastName());
        ContactPhone.setText(core.getModWorker().getContactPhone());
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
            Worker worker = core.getModWorker();
            worker.setPassword(encrypt(Password.getText(), key, cipher));
            worker.setFirstName(FirstName.getText());
            worker.setLastName(LastName.getText());
            worker.setContactPhone(ContactPhone.getText());
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
        } catch (Exception e) {
            alertMessage.setText(language.getProperty("modifyWorkerFail"));
        }
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


    private static String decrypt(String string, SecretKeySpec key, Cipher cipher) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(cipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
}