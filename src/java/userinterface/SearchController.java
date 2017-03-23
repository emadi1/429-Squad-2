package userinterface;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/11/2017.
 */
public abstract class SearchController implements Initializable {

    @FXML
    protected TableView tableView;
    @FXML
    protected ChoiceBox<String> searchChoice;
    @FXML
    protected TextField searchField;

    ObservableList<String> properties;


    public SearchController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        properties = itemsSearchChoiceArray();
        searchChoice.setItems(properties);
        searchChoice.getSelectionModel().selectFirst();
        setTableView();
    }

    public void search(ActionEvent actionEvent) {
        ObservableList observableList = querySelector();
        if (observableList != null) {
            tableView.setItems(observableList);
        }

//        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
//        actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Boolean>, ObservableValue<Boolean>>() {
//            @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> features) {
//                return new SimpleBooleanProperty(features.getValue() != null);
//            }
//        });
//
//        // create a cell value factory with an add button for each row in the table.
//        actionCol.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
//            @Override public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> personBooleanTableColumn) {
//                return new AddPersonCell(stage, table);
//            }











    }

    protected static boolean isNumeric(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    protected abstract ObservableList<String> itemsSearchChoiceArray();

    protected abstract void setTableView();

    protected abstract ObservableList querySelector();
}
