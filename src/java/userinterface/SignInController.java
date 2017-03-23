package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Worker;
import models.WorkerCollection;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Kevin on 3/8/2017.
 */
public class SignInController implements Initializable {

    @FXML
    TextField bannerId;
    @FXML
    TextField password;
    @FXML
    private Text alertMessage;
    @FXML
    private Button signIn;

    public SignInController() {

    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * @param actionEvent
     */
    public void signIn(ActionEvent actionEvent) throws IOException {
        signIn = (Button)actionEvent.getSource();
        if (bannerId.getText().equals("") || password.getText().equals(""))
            alertMessage.setText("Please enter BannerID/Password");
        else {
            // Query DB to create worker object.
            WorkerCollection workerCollection = new WorkerCollection();
            Worker worker = (Worker)workerCollection.findWorkersByBannerId(bannerId.getText()).elementAt(0);
            String pw = (String)worker.getState("Password");
            if (!pw.equals(password.getText()))
                alertMessage.setText("Password Invalid");
            if (pw.equals(password.getText())) {
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
                    Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Brockport Library System");
                    primaryStage.setResizable(false);
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println("Can't open new window.");
                }
            }
        }
    }

    /**
     * @param actionEvent
     */
    public void skip(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brockport Library System!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
