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
import java.util.*;

/**
 * Created by Eli and kevtr0n on 4/15/17.
 */
public class CheckOutBookViewController extends RentalTransactionsController implements Initializable {

    @FXML private Text title, alertMessage, barcodeText, bannerIdText;
    @FXML private TextField barcodeField, bannerIdField;
    @FXML private Button submit, verify, override;
    @FXML private TableView tableView;
    private String student, bannerId;
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
        bannerIdField.setPromptText(language.getProperty("BannerId"));
        barcodeField.setPromptText(language.getProperty("Barcode"));
        bannerIdText.setText(language.getProperty("BannerId") + ":");
        barcodeText.setText(language.getProperty("Barcode") + ":");
        title.setText(language.getProperty("CheckOutBook"));
        override.setText(language.getProperty("Override"));
        submit.setText(language.getProperty("Submit"));
        verify.setText(language.getProperty("Verify"));
        barcodeField.setDisable(true);
        override.setDisable(true);
        submit.setDisable(true);
        try {
            setTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    @FXML private int verify() {
        student = bannerIdField.getText();
        bannerId = bannerIdField.getText();
        try {
            if (bannerId.length() == 9 && Core.isNumeric(bannerId)) {
                StudentBorrower studentBorrower = (StudentBorrower) studentBorrowerCollection.findStudentsByBannerId(bannerId).get(0);
                tableView.setItems(FXCollections.observableList(rentalCollection.findRentalsByBorrowerId(student)));
                if (studentBorrower.getBorrowerStatus().equals("Good Standing")) {
                    barcodeField.setDisable(false);
                    submit.setDisable(false);
                } else {
                    alertMessage.setText(language.getProperty("invalidBorrowerStatus"));
                    if (Core.getInstance().getUser().getCredentials().equals("Administrator"))
                        override.setDisable(false);
                }
            } else alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    @FXML private void override() {
        barcodeField.setDisable(false);
        submit.setDisable(false);
        alertMessage.setText(language.getProperty("AdministratorOverride"));
    }
    @Override @FXML protected int submit() {
        Properties data = new Properties();
        String barcode = barcodeField.getText();
        Vector<Book> books = bookCollection.findBooksByBarcode(barcode);
        if (books.size() == 1) {
            if (barcode.length() == 5 && Core.isNumeric(barcode)) {
                boolean canRent = true;
                Vector<Rental> rentals = rentalCollection.findRentalsByBookId(barcode);
                for (Rental rental : rentals) {
                    if (rental.getCheckInDate() != null && !rental.getCheckInDate().equals("")) {
                        canRent = false;
                    }
                }
                if (canRent) {
                    data.put(DBKey.BORROWER_ID, student);
                    data.put(DBKey.BOOK_ID, barcode);
                    data.put(DBKey.CHECK_OUT_WORKER_ID, Core.getInstance().getUser().getBannerId());
                    data.put(DBKey.CHECK_OUT_DATE, Core.generateEnglishDate());
                    data.put(DBKey.DUE_DATE, generateDueDate());
                    System.out.println(data.toString());
                    Rental rental = new Rental(data);
                    rental.insert();
                    alertMessage.setText(language.getProperty("CheckOutSuccess"));
                    tableView.setItems(FXCollections.observableList(rentalCollection.findRentalsByBorrowerId(student)));
                }
            }
        } else alertMessage.setText(language.getProperty("NoBookWithId"));
        return 1;
    }
    private String generateDueDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 15);
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        if (Integer.parseInt(day) < 10) day = '0' + day;
        if (Integer.parseInt(month) < 10) month = '0' + month;
        return month + '-' + day + '-' + year;
    }
}
