package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Rental;
import models.RentalCollection;
import utilities.Core;


import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/7/2017.
 */
public class ListCheckedBooksController implements Initializable {

    private Properties language = Core.getInstance().getLang();
    private TableColumn<Rental, String> id = new TableColumn<>(language.getProperty("Id"));
    private TableColumn<Rental, String> bookTitle = new TableColumn<>(language.getProperty("Title"));
    private TableColumn<Rental, String> borrower = new TableColumn<>(language.getProperty("BorrowedBy"));
    private TableColumn<Rental, String> worker = new TableColumn<>(language.getProperty("CheckedOutByWorker"));
    private TableColumn<Rental, String> checkOutDate = new TableColumn<>(language.getProperty("CheckOutDate"));
    private TableColumn<Rental, String> dueDate = new TableColumn<>(language.getProperty("DueDate"));
    @FXML private TableView tableView;

    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            setTableView();
//            RentalCollection rentalCollection = new RentalCollection();
//            tableView.setItems(FXCollections.observableList(rentalCollection));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        RentalCollection rentalCollection = new RentalCollection();
        System.out.println(rentalCollection.findAllCheckedOutBooks());
    }

    private void setTableView() throws IOException {
        id.setMinWidth(150);
        bookTitle.setMinWidth(150);
        borrower.setMinWidth(150);
        worker.setMinWidth(150);
        checkOutDate.setMinWidth(150);
        dueDate.setMinWidth(150);

        id.setCellValueFactory(new PropertyValueFactory<>(DBKey.ID));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>(DBKey.TITLE));


        checkOutDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_DATE));
        dueDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.DUE_DATE));

        tableView.getColumns().add(id);
        tableView.getColumns().add(bookTitle);


        tableView.getColumns().add(checkOutDate);
        tableView.getColumns().add(dueDate);

        tableView.setRowFactory(tableView -> {

            final TableRow<Rental> row = new TableRow<>();
            row.hoverProperty().addListener(observable -> {
                final Rental rental = row.getItem();
                if (rental != null) {
                    Tooltip tp = new Tooltip("at row tool");
                    Tooltip.install(row, tp);
                    tp.setText(rental.toolTipToString());
                }
            });
            return row;
        });
    }

}
