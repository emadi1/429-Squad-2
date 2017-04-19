package userinterface;

import database.DBKey;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Rental;
import models.RentalCollection;
import models.Worker;
import utilities.Core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URL;
import java.util.*;

/**
 * Created by JJ on 4/13/2017.
 */
public class CheckInBookViewController implements Initializable {

    @FXML
    private Text barcode, alertMessage;
    @FXML
    private TextField Barcode;
    @FXML
    private Button submit;

    private Core core = Core.getInstance();
    private Properties lang = core.getLang();
    private Worker user = core.getUser();

    public void initialize(URL location, ResourceBundle resources) {
        barcode.setText(lang.getProperty("PromptBarcode"));
        Barcode.setPromptText(lang.getProperty("Barcode"));
        submit.setText(lang.getProperty("Submit"));
    }

    public void verify() {

        String barcode = Barcode.getText();
        alertMessage.setText("");

        if (barcode.length() == 5) {
            RentalCollection rentalCollection = new RentalCollection();
            Vector rc = rentalCollection.findRentalsByBookId(barcode);


            if (rc.isEmpty()) {
                System.out.print("Rental not found");
            } else {
                try {
                    Vector<Rental> rentals = rentalCollection.findRentalsByBookId(Barcode.getText());
                    Rental rental = rentals.get(0);
                    String checkOutDate = rental.getCheckOutDate();
                    String checkInDate = rental.getCheckInDate();

                    if (checkOutDate != null|| checkOutDate != "") {
                     if (checkInDate != null|| checkInDate != "") {
                            rental.setCheckInDate(Core.formatDateToEnglish(Core.generateDate()));
                            rental.setCheckInWorkerId(Core.getInstance().getUser().getBannerId());
                            alertMessage.setText(lang.getProperty("checkInBookSuccess"));
                     }
                    } else {
                        alertMessage.setText(lang.getProperty("bookAlreadyCheckedOut"));
                    }

                } catch (Exception e) {
                    alertMessage.setText(lang.getProperty("checkInBookFail"));
                }
            }
        } else {
            alertMessage.setText(lang.getProperty("invalidBarcodeLength"));
        }
    }
}
