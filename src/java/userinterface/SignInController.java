package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;
import utilities.PWEncrypt;

import javax.crypto.*;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Kevin, modified by Andeezy on 3/8/2017.
 */
public class SignInController implements Initializable {

    @FXML private ComboBox<String> language;
    @FXML private TextField bannerId;
    @FXML private TextField passwordPlain;
    @FXML private PasswordField password;
    @FXML private Circle pwUnhide;
    @FXML private Text alertMessage;
    @FXML private Text signInHeader;
    @FXML private Text banner;
    @FXML private Text pw;
    @FXML private Button signIn;
    private ObservableList<String> languages = FXCollections.observableArrayList("en_US", "fr_FR");
    private Core core = Core.getInstance();
    private Properties lang = core.getLang();
    private PWEncrypt pwEncrypt = PWEncrypt.getInstance();
    private WorkerCollection workerCollection = new WorkerCollection();
    private final String image = "https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png";

    public SignInController() throws GeneralSecurityException {

    }

    public void initialize(URL location, ResourceBundle resources) {
        passwordPlain.setVisible(false);
        passwordPlain.setManaged(false);
        language.setItems(languages);
        if (core.getLanguage().equals("fr_FR")) language.setValue("fr_FR");
        else language.setValue("en_US");
        signInHeader.setText(lang.getProperty("signIn"));
        banner.setText(lang.getProperty("PromptBannerId"));
        pw.setText(lang.getProperty("PromptPassword"));
        password.setPromptText(lang.getProperty("Password"));
        bannerId.setPromptText(lang.getProperty("BannerId"));
        System.out.println(password.getStyle());
        signIn.setText(lang.getProperty("SignIn"));
        pwUnhide.hoverProperty().addListener(l -> {
            unhidePassword();
        });
    }

    public void signIn(ActionEvent actionEvent) throws IOException, GeneralSecurityException {
        if (bannerId.getText().equals("") || password.getText().equals(""))
            alertMessage.setText(lang.getProperty("completeFields"));
        if (bannerId.getText().length() != 9 || !Core.isNumeric(bannerId.getText()))
            alertMessage.setText(lang.getProperty("invalidBannerIdFormat"));
        else {
            // Query DB to create worker object.
            try {
                Worker worker = (Worker) workerCollection.signInWorker(bannerId.getText()).elementAt(0);
                String pw = pwEncrypt.decryptKicker((String) worker.getState(DBKey.PASSWORD));
                if (pw.equals(password.getText())) {
                    password.clear();
                    core.setUser(worker);
                    core.setLanguage(language.getSelectionModel().getSelectedItem());
                    lang = core.getLang();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
                    Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.getIcons().add(new Image(image));
                    primaryStage.setScene(scene);
                    primaryStage.setTitle(lang.getProperty("welcome"));
                    primaryStage.setResizable(false);
                    core.getLastStage().close();
                    core.setStage(primaryStage);
                    primaryStage.show();
                } else alertMessage.setText(lang.getProperty("invalidPassword"));
            } catch (Exception e) {
                alertMessage.setText(lang.getProperty("noWorker"));
                System.out.println("Can't open new window.");
            }

        }
    }

    public void skipAsAdmin(ActionEvent actionEvent) throws IOException {
        dummyWorker(1);
        core.setLanguage(language.getSelectionModel().getSelectedItem());
        lang = core.getLang();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle(lang.getProperty("welcome"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Can't open new window.");
        }
    }

    public void skipAsOrdinary(ActionEvent actionEvent) {
        dummyWorker(2);
        core.setLanguage(language.getSelectionModel().getSelectedItem());
        lang = core.getLang();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle(lang.getProperty("welcome"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Can't open new window.");
        }
    }


    private void dummyWorker(int type) {
        String[] workerArray = {"BannerId", "Password", "FirstName", "LastName", "ContactPhone",
                "Email", "Credentials", "DateOfLatestCredentialsStatus", "DateOfHire", "Status"};
        String[] workerData;
        if (type == 1) {
            workerData = new String[]{"800123456", "123456", "John", "Doe", "555-666-1234", "DaMan@gmail.com",
                    "Administrator", "3-21-2017", "4-17-2007", "Active"};
            Properties props = new Properties();
            for (int i = 0; i < workerArray.length; i++) {
                props.put(workerArray[i], workerData[i]);
            }
            Worker worker = new Worker(props);
            core.setUser(worker);
        } else if (type == 2) {
            workerData = new String[]{"800123456", "123456", "John", "Doe", "555-666-1234", "DaMan@gmail.com",
                    "Ordinary", "3-21-2017", "4-17-2007", "Active"};
            Properties props = new Properties();
            for (int i = 0; i < workerArray.length; i++) {
                props.put(workerArray[i], workerData[i]);
            }
            Worker worker = new Worker(props);
            core.setUser(worker);
        }
    }


    private void unhidePassword() {

        // Bind the textField and passwordField text values bidirectionally.
        passwordPlain.textProperty().bindBidirectional(password.textProperty());
        passwordPlain.setManaged(true);
        passwordPlain.setVisible(true);
        password.setManaged(false);
        password.setVisible(false);

        if (!pwUnhide.isHover()) {
            passwordPlain.setManaged(false);
            passwordPlain.setVisible(false);
            password.setManaged(true);
            password.setVisible(true);
            passwordPlain.textProperty().bindBidirectional(password.textProperty());
        }
    }
}
