package userinterface;
import database.DBKey;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.*;
import utilities.Core;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by Jen and kevtr0n on 4/15/17.
 */
public class CheckInBookViewController extends RentalTransactionsController implements Initializable {

    @FXML private Text title, alertMessage, barcodeText;
    @FXML private TextField barcodeField;
    @FXML private TableView tableView;
    @FXML private Button submit;
    private Properties language = Core.getInstance().getLang();
    private BookCollection bookCollection = new BookCollection();
    private RentalCollection rentalCollection = new RentalCollection();
    private StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
    private TableColumn<Rental, String> id = new TableColumn<>(language.getProperty("Id"));
    private TableColumn<Rental, String> borrowerId = new TableColumn<>(language.getProperty("BorrowerId"));
    private TableColumn<Rental, String> bookId = new TableColumn<>(language.getProperty("BookId"));
    private TableColumn<Rental, String> checkOutDate = new TableColumn<>(language.getProperty("CheckOutDate"));
    private TableColumn<Rental, String> checkOutWorkerId = new TableColumn<>(language.getProperty("CheckOutWorkerId"));
    private TableColumn<Rental, String> dueDate = new TableColumn<>(language.getProperty("DueDate"));
    private TableColumn<Rental, String> checkInDate = new TableColumn<>(language.getProperty("CheckInDate"));
    private TableColumn<Rental, String> checkInWorkerId = new TableColumn<>(language.getProperty("CheckInWorkerId"));

    public void initialize(URL location, ResourceBundle resources) {
        barcodeField.setPromptText(language.getProperty("Barcode"));
        barcodeText.setText(language.getProperty("Barcode") + ":");
        title.setText(language.getProperty("CheckInBook"));
        submit.setText(language.getProperty("Submit"));
        try {
            setTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override @FXML protected int submit() throws RuntimeException {
        // Check for barcode format
        String barcode = barcodeField.getText();
        if (barcode.length() != 5 || !Core.isNumeric(barcode)) {
            alertMessage.setText(language.getProperty("invalidBarcodeLength"));
            return 0;
        }
        try {
            Vector<Book> books = bookCollection.findBooksByBarcode(barcode);
            // Check to see if book exists in database
            if (books.size() == 1) {
                Vector<Rental> rentals = rentalCollection.findRentalsByBookId(barcode);
                Rental oldRental = null;
                if (rentals.size() != 0) {
                    for (Rental rental : rentals)
                        if (rental.getCheckInDate() == null)
                            oldRental = rental;
                    if (oldRental == null) {
                        alertMessage.setText(language.getProperty("BookNotCheckedOut") + barcode);
                        return 0;
                    }
                    oldRental.setCheckInDate(Core.generateEnglishDate());
                    oldRental.setCheckInWorkerId(Core.getInstance().getUser().getBannerId());
                    oldRental.update();
                    rentals = rentalCollection.findRentalsByBookId(barcode);
                    tableView.setItems(FXCollections.observableList(rentals));
                    StudentBorrower studentBorrower = (StudentBorrower) studentBorrowerCollection.findStudentsByBannerId(oldRental.getBorrowerId()).get(0);
                    if (studentBorrower.getBorrowerStatus().equals(language.getProperty("Delinquent"))) {
                        rentals = rentalCollection.findRentalsByBorrowerId(studentBorrower.getBannerId());
                        boolean isDelinquent = false;
                        for (Rental rental : rentals)
                            if (rental.getCheckInDate().equals("")) isDelinquent = true;
                        if (!isDelinquent) {
                            studentBorrower.setBorrowerStatus("Good Standing");
                            studentBorrower.setDateOfLatestBorrowerStatus(Core.generateEnglishDate());
                        }
                    }
                    alertMessage.setText(language.getProperty("CheckInSuccess"));
                }
            } else alertMessage.setText(language.getProperty("NoBookWithId") + barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    @Override protected void setTableView() throws IOException {
        id.setMinWidth(100);
        borrowerId.setMinWidth(100);
        bookId.setMinWidth(100);
        checkOutDate.setMinWidth(100);
        checkOutWorkerId.setMinWidth(100);
        dueDate.setMinWidth(100);
        checkInDate.setMinWidth(100);
        checkInWorkerId.setMinWidth(100);

        id.setCellValueFactory(new PropertyValueFactory<>(DBKey.ID));
        borrowerId.setCellValueFactory(new PropertyValueFactory<>(DBKey.BORROWER_ID));
        bookId.setCellValueFactory(new PropertyValueFactory<>(DBKey.BOOK_ID));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_DATE));
        checkOutWorkerId.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_WORKER_ID));
        dueDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.DUE_DATE));
        checkInDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_IN_DATE));
        checkInWorkerId.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_IN_WORKER_ID));

        tableView.getColumns().add(id);
        tableView.getColumns().add(borrowerId);
        tableView.getColumns().add(bookId);
        tableView.getColumns().add(checkOutDate);
        tableView.getColumns().add(checkOutWorkerId);
        tableView.getColumns().add(dueDate);
        tableView.getColumns().add(checkInDate);
        tableView.getColumns().add(checkInWorkerId);

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
