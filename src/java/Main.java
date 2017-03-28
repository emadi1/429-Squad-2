import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.Core;

import java.io.IOException;
import java.util.Properties;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Core core = Core.getInstance();
        core.setLanguage("en_US");
        Properties lang = core.getLang();
        primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
        Parent root = FXMLLoader.load(getClass().getResource("signinview.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(lang.getProperty("welcome"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}