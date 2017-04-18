
package userinterface;

import database.DBKey;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.*;
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import static userinterface.TransactionController.isNumeric;


public class CheckOutBookViewController implements Initializable {

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private Worker user = core.getUser();

    @FXML
    private Text bannerId;
    @FXML
    private TextField BannerId;
    @FXML
    private Button submitId;
    @FXML
    private TextField barcodeTextField;
    @FXML
    private Text barcodePromptText;
    @FXML
    private Button submitBarcode;
    @FXML
    private Button overrideButton;
    @FXML
    private Text alertMessage;
    @FXML
    private Text CheckOutBook;

    public void initialize(URL location, ResourceBundle resources) {
        bannerId.setText(language.getProperty("PromptBannerId"));
        BannerId.setPromptText(language.getProperty("BannerId"));
        barcodeTextField.setPromptText(language.getProperty("Barcode"));
        submitId.setText(language.getProperty("Search"));
        submitBarcode.setText(language.getProperty("Search"));
        barcodeTextField.setDisable(true);
        barcodePromptText.setText(language.getProperty("PromptBarcode"));
        overrideButton.setText(language.getProperty("Override"));
        overrideButton.setDisable(true);
        submitBarcode.setDisable(true);
        CheckOutBook.setText(language.getProperty("CheckOutBook"));

    }

    /**
     * Make sure that we're going to have
     * a rental available  translate books to rentals.
     */
    /**
     * Creates student borrower collection given a BannerId.
     * If student's BorrowerStatus is in good standing.
     * - Launch barcode window
     * Else launch override/cancel window.
     */


    public void submitId() {

        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();


        Vector<StudentBorrower> students = studentBorrowerCollection.findStudentsByBannerId(BannerId.getText());


        if (BannerId.getText().length() != 9 || !isNumeric(BannerId.getText())) {
            alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
            return;
        }
        if (students.isEmpty() == true) {
            System.out.print("students empty");
        }

        if (students.size() == 0) {
            alertMessage.setText("No Student by that Banner ID.");
        } else {
            StudentBorrower test = students.firstElement();
            System.out.print(test.getBorrowerStatus());
            //String borrowerStatus = (String) students.get(0);
            if (test.getBorrowerStatus().equals(language.getProperty("GoodStanding"))) {
                // launch barcode
                barcodeTextField.setDisable(false);
                submitBarcode.setDisable(false);
                alertMessage.setText("This student is in good standing");

            } else {
                // ALERT WINDOW
                alertMessage.setText("This student is unable to check out a book");
                barcodeTextField.setDisable(true);
                submitBarcode.setDisable(true);

                if (user.getCredentials().equals("Administrator")) {
                    overrideButton.setDisable(false);
                }
            }
        }


    }


    public int submitBarcode() {

        BookCollection bookCollection = new BookCollection();
        if (!barcodeTextField.getText().equals("") && !barcodeTextField.getText().equals(null)
                && Core.isNumeric(barcodeTextField.getText()) && barcodeTextField.getText().length() == 5) {
            Vector<Book> books = bookCollection.findBooksByBarcode(barcodeTextField.getText());
            if (books.size() != 0) {
                RentalCollection rentalCollection = new RentalCollection();

                Vector<Rental> rentals = rentalCollection.findRentalsByBookId(barcodeTextField.getText());
                for (Rental rental : rentals) {
                    if (rental.getCheckInDate() == null || rental.getCheckInDate().equals("")) {
                        alertMessage.setText("Book Currently Checked Out!");
                        return 0;
                    }
                }
                Properties data = new Properties();
                data.put(DBKey.BOOK_ID, barcodeTextField.getText());
                data.put(DBKey.BORROWER_ID, BannerId.getText());
                data.put(DBKey.CHECK_OUT_WORKER_ID, user.getBannerId());
                data.put(DBKey.CHECK_OUT_DATE, core.generateDate());
                data.put(DBKey.DUE_DATE, Core.computeDueDate());
                System.out.println(data.toString());
                Rental rental = new Rental(data);
                rental.insert();
                alertMessage.setText("Book Successfully Checked Out!");

            }

        }
                return 1;
    }


    public void changedMyMind() {
        this.submitId.setDisable(false);

    }

    public void overrideStatus() {
        submitBarcode.setDisable(false);
        barcodeTextField.setDisable(false);
        alertMessage.setText("Student Status Overriden");
    }
}
