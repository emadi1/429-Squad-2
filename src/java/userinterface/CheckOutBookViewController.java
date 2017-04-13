
package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.*;
import utilities.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;


public class CheckOutBookViewController implements Initializable {

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private Worker user = core.getUser();

    @FXML private Text bannerId;
    @FXML private TextField BannerId;
    @FXML private Button submitId;
    @FXML private TextField barcodeTextField;
    @FXML private Text barcodePromptText;
    @FXML private Button submitBarcode;
    @FXML private Text alertMessage;

    public void initialize(URL location, ResourceBundle resources) {
        bannerId.setText(language.getProperty("PromptBannerId"));
        BannerId.setPromptText(language.getProperty("BannerId"));
        barcodeTextField.setPromptText(language.getProperty("Barcode"));
        submitId.setText(language.getProperty("Search"));
        submitBarcode.setText(language.getProperty("Search"));
        barcodeTextField.setVisible(false);
        barcodePromptText.setText(language.getProperty("PromptBarcode"));
        barcodePromptText.setVisible(false);

//      if (user.getCredentials().equals("Ordinary")) submit.setDisable(true);
    }

    /**
     * Make sure that we're going to have
     *
     */
    /**
     * Creates student borrower collection given a BannerId.
     * If student's BorrowerStatus is in good standing.
     *      - Launch barcode window
     * Else launch override/cancel window.
     */


    public void submitId() {

        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
        barcodeTextField.setVisible(true);
        barcodePromptText.setVisible(true);

        Vector<StudentBorrower> students = studentBorrowerCollection.findStudentsByBannerId(BannerId.getText());

        if (students == null) {
            System.out.print("students null");
        } else {
            if (students.isEmpty() == true) {
                System.out.print("students empty");
            }

            if (students.size() == 0){
                alertMessage.setText("No Student by that Banner ID.");
            } else {
                StudentBorrower test = students.firstElement();
                System.out.print(test.getBorrowerStatus());
                //String borrowerStatus = (String) students.get(0);
                if (test.getBorrowerStatus().equals(language.getProperty("GoodStanding"))) {
                    // launch barcode
                    this.submitId.setDisable(true);

                } else {
                    // ALERT WINDOW
                  alertMessage.setText("Dis guy cant rent shit");
                    // Administrator override?
                }
            }
        }
    }

    public void submitBarcode()
    {
       RentalCollection rentalCollection = new RentalCollection();

        Vector rental= rentalCollection.findRentalsByBookId(rentalCollection.toString());

        Vector books = rentalCollection.findRentalsByBookId(barcodeTextField.getText());
            if (books.isEmpty() == true) {
                System.out.print("book not found");
            }




        }




    public void changedMyMind()
    {
        this.submitId.setDisable(false);
        barcodeTextField.setVisible(false);
        barcodePromptText.setVisible(false);
    }

}
