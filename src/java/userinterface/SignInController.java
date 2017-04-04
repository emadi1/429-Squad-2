package userinterface;

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

import java.io.IOException;
import java.net.URL;
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

    public SignInController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        passwordPlain.setVisible(false);
        passwordPlain.setManaged(false);
        language.setItems(languages);
        language.setValue("en_US");
        signInHeader.setText(lang.getProperty("signIn"));
        banner.setText(lang.getProperty("PromptBannerId"));
        pw.setText(lang.getProperty("PromptPassword"));
        password.setPromptText(lang.getProperty("Password"));
        bannerId.setPromptText(lang.getProperty("BannerId"));
        System.out.println(password.getStyle());
        signIn.setText(lang.getProperty("SignIn"));
        pwUnhide.hoverProperty().addListener(l->{
            unhidePassword();
        });
    }

    public void signIn(ActionEvent actionEvent) throws IOException {
        if (bannerId.getText().equals("") || password.getText().equals(""))
            alertMessage.setText("Please enter BannerID/Password");
        else {
            // Query DB to create worker object.
            WorkerCollection workerCollection = new WorkerCollection();
            Worker worker = (Worker)workerCollection.signInWorker(bannerId.getText()).elementAt(0);
            String pw = (String)worker.getState("Password");
            if (!pw.equals(password.getText()))
                alertMessage.setText(lang.getProperty("invalidPassword"));
            if (pw.equals(password.getText())) {
                try {
                    password.clear();
                    core.setUser(worker);
                    core.setLanguage(language.getSelectionModel().getSelectedItem());
                    lang = core.getLang();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
                    Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                    primaryStage.setScene(scene);
                    primaryStage.setTitle(lang.getProperty("welcome"));
                    primaryStage.setResizable(false);
                    core.getLastStage().close();
                    core.setStage(primaryStage);
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println("Can't open new window.");
                }
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

        passwordPlain.setManaged(true);
        passwordPlain.setVisible(true);

        password.setManaged(false);
        password.setVisible(false);

        // Bind the textField and passwordField text values bidirectionally.
        passwordPlain.textProperty().bindBidirectional(password.textProperty());

        if (! pwUnhide.isHover()) {
            passwordPlain.setManaged(false);
            passwordPlain.setVisible(false);

            password.setManaged(true);
            password.setVisible(true);

            passwordPlain.textProperty().bindBidirectional(password.textProperty());
        }
    }
}
