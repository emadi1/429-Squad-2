package userinterface;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Worker;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/11/2017.
 */
public abstract class TransactionController implements Initializable {

    @FXML
    protected TableView tableView;
    @FXML
    protected ChoiceBox<String> searchChoice;
    @FXML
    protected TextField searchField;
    @FXML
    protected Button modify;
    private Core core = Core.getInstance();
    ObservableList<String> properties;


    public TransactionController() {

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

    // Displays the modify view
    protected abstract void showModifyDialog();

    // Used to ensure correct Object is created from table data
    protected abstract int getType();


    protected class AddModCell extends TableCell<Worker, Boolean> {

        final Button addButton = new Button("Modify");
        final StackPane paddedButton = new StackPane();


        /**
         * AddModCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new worker can be added.
         */
        AddModCell(final Stage stage, final TableView table) {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    table.getSelectionModel().select(getTableRow().getIndex());
                    Core core = Core.getInstance();

                    if (getType() == 0) {
                        Worker w = (Worker) table.getSelectionModel().getSelectedItems().get(0);
                        core.setModWorker(w);
                    }

                    showModifyDialog();

                }
            });
        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
