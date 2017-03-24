package userinterface;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/11/2017.
 */
public abstract class SearchController implements Initializable {

    @FXML protected TableView tableView;
    @FXML protected ChoiceBox<String> searchChoice;
    @FXML protected TextField searchField;
    @FXML protected Button modify;
    private Core core = Core.getInstance();
    ObservableList<String> properties;


    public SearchController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        properties = itemsSearchChoiceArray();
        searchChoice.setItems(properties);
        searchChoice.getSelectionModel().selectFirst();
        if (core.getUser().getCredentials().equals("Ordinary")) {
            modify.setDisable(true);
        }
        setTableView();
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

    protected abstract ObservableList<String> itemsSearchChoiceArray();

    protected abstract void setTableView();

    protected abstract ObservableList querySelector();
}
