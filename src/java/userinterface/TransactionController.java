package userinterface;

import database.Persistable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.EntityBase;
import models.StudentBorrower;
import models.Worker;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph, I did some stuff too I swear, on 3/11/2017.
 */
public abstract class TransactionController implements Initializable {

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    ObservableList<String> properties;
    @FXML protected Text workerHeader;
    @FXML protected Text bookHeader;
    @FXML protected Text studentHeader;
    @FXML protected TableView tableView;
    @FXML protected ChoiceBox<String> searchChoice;
    @FXML protected TextField searchField;
    @FXML protected Button modify;
    @FXML protected Button search;
    @FXML protected Button add;

    public TransactionController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void search(ActionEvent actionEvent) {
        ObservableList observableList = querySelector();
        if (observableList != null) {
            tableView.setItems(observableList);
        }
    }

    protected static boolean isNumeric(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    protected void modify(ActionEvent actionEvent) throws IOException {
    }

    protected abstract ObservableList<String> dedicatedColumnHeaders();

    protected abstract ObservableList<String> itemsSearchChoiceArray();

    protected abstract void setTableView();

    protected abstract ObservableList querySelector();

    // Displays the modify view
    protected abstract void showModifyDialog();

}
