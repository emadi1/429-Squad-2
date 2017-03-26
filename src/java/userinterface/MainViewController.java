package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/5/2017.
 */
public class MainViewController implements Initializable {
    Core core = Core.getInstance();
    Properties lang = core.getLang();
    @FXML private Pane root;
    @FXML private Text welcome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcome.setText(lang.getProperty("welcome"));
    }

    public void menuClickListener(ActionEvent event) throws IOException {
        if (root != null) {
            Button button = (Button) event.getSource();
            System.out.println(button.getId());
            Parent pane = null;

            switch (button.getId()) {
                case "bookTransactions":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("booktransactionsview.fxml"));
                    break;

                case "studentBorrowerTransactions":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("studentborrowertransactionsview.fxml"));
                    break;

                case "workerTransactions":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("workertransactionsview.fxml"));
                    break;

                case "checkOutBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("checkoutbookview.fxml"));
                    break;

                case "checkInBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("checkinbookview.fxml"));
                    break;

                case "listCheckedBooks":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("listcheckedbooksview.fxml"));
                    break;
            }
            if (pane != null) {
                root.getChildren().clear();
                root.getChildren().setAll(pane);
            }
        }
    }
}
















