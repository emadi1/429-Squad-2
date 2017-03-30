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
    private Properties lang = Core.getInstance().getLang();

    public static void main(String[] args) {
        Core core = Core.getInstance();
        core.setLanguage("fr_FR");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Core core = Core.getInstance();
        core.setLanguage("en_US");
        primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
        Parent root = FXMLLoader.load(getClass().getResource("signinview.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        System.out.println(lang.getProperty("welcome"));
        primaryStage.setTitle(lang.getProperty("welcome"));
        primaryStage.setResizable(false);
        core.setStage(primaryStage);
        primaryStage.show();
    }
}