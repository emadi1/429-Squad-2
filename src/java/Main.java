/**
 * Created by kevph on 3/7/2017.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
        Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brockport Library System");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}