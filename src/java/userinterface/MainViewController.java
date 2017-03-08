package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/5/2017.
 */
public class MainViewController implements Initializable{
    @FXML
    private Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void menuClickListener(ActionEvent event) throws IOException {
        if (root != null) {
            Button button = (Button) event.getSource();
            //System.out.println(button.getId());
            Parent pane = null;

            switch (button.getId()) {
                case "addBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("addbookview.fxml"));
                    break;

                case "modifyBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("modifybookview.fxml"));
                    break;

                case "deleteBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("deletebookview.fxml"));
                    break;

                case "addStudent":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("addstudentview.fxml"));
                    break;

                case "modifyStudent":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("modifystudentview.fxml"));
                    break;

                case "deleteStudent":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("deletestudentview.fxml"));
                    break;

                case "addWorker":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("addworkerview.fxml"));
                    break;

                case "modifyWorker":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("modifyworkerview.fxml"));
                    break;

                case "deleteWorker":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("deleteworkerview.fxml"));
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
















