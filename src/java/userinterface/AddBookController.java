package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/7/2017.
 */
public class AddBookController implements Initializable {

    ArrayList<TextField> textFieldList;
    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    ObservableList<String> conditionList = FXCollections.observableArrayList("Good", "Damaged");
    @FXML
    private TextField barcode;
    @FXML
    private TextField title;
    @FXML
    private TextField discipline;
    @FXML
    private TextField authors;
    @FXML
    private TextField publisher;
    @FXML
    private TextField yearOfPublication;
    @FXML
    private TextField isbn;
    @FXML
    private TextField suggestedPrice;
    @FXML
    private TextField notes;
    @FXML
    private ComboBox<String> status;
    @FXML
    private ComboBox<String> condition;

    public AddBookController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(barcode);
        textFieldList.add(title);
        textFieldList.add(discipline);
        textFieldList.add(authors);
        textFieldList.add(publisher);
        textFieldList.add(yearOfPublication);
        textFieldList.add(isbn);
        textFieldList.add(suggestedPrice);
        textFieldList.add(notes);
        status.setValue("Active");
        status.setItems(statusList);
        condition.setValue("Good");
        condition.setItems(conditionList);
    }

    private static boolean isNumeric(String string) {
        try {
            double doub = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param actionEvent
     */
    public void submit(ActionEvent actionEvent) {

    }
}
