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
    private Text barcode, alertMessage, CheckInDate, CheckOutDate;
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
        CheckInDate.setText(lang.getProperty("CheckInDate"));
        CheckOutDate.setText(lang.getProperty("CheckOutDate"));

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
                Vector<Rental> rentals = rentalCollection.findRentalsByBookId(Barcode.getText());

                for (Rental rental : rentals) {
                    if (!rental.getCheckOutDate().equals(null) || !rental.getCheckOutDate().equals("")) {
                        if (rental.getCheckInDate().equals(null)|| rental.getCheckInDate().equals("")) {
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                                Date checkInDate = new Date();
                                String stringDueDate = rental.getDueDate();
                                Date dueDate = format.parse(stringDueDate);

                                if (checkInDate.equals(dueDate) || checkInDate.before(dueDate)) {
                                    DateFormat stringformat = new SimpleDateFormat("dd-MMM-yyyy");
                                    String stringcheckInDate = stringformat.format(checkInDate);
                                    rental.setCheckInDate(stringcheckInDate);
                                    alertMessage.setText(lang.getProperty("Book has been checked in."));

                                }else {
                                    DateFormat stringformat = new SimpleDateFormat("dd-MMM-yyyy");
                                    String stringcheckInDate = stringformat.format(checkInDate);
                                    rental.setCheckInDate(stringcheckInDate);
                                    alertMessage.setText(lang.getProperty("Book checked in, but it is late."));
                                }

                            }catch (ParseException e) {
                                alertMessage.setText(lang.getProperty("Due date error!"));
                            }

                            Properties data = new Properties();
                            data.put(DBKey.CHECK_OUT_WORKER_ID, user.getBannerId());
                            data.put(DBKey.CHECK_IN_DATE, rental.getCheckInDate() );
                            rental.insert();

                        }
                    } else {
                        alertMessage.setText(lang.getProperty("This book is not checked out."));
                    }
                }
            }
        }
        else{
            alertMessage.setText(lang.getProperty("Barcode must be 5 digits."));
        }
    }
}
