/*
package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.StudentBorrower;
import models.StudentBorrowerCollection;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;


public class CheckOutBookController extends StudentBorrowerTransactionsController implements Initializable {

    private Core core = Core.getInstance();
    private Properties language = Core.getInstance().getLang();
    @FXML
    private Text checkOutBook;
    @FXML
    private Text bannerId;
    @FXML
    private TextField BannerId;
    @FXML
    private Text alertMessage;

    public void initialize(URL location, ResourceBundle resources) {
        checkOutBook.setText(language.getProperty("CheckOutBook"));
        bannerId.setText(language.getProperty("BannerId") + ":");
        BannerId.setPromptText(language.getProperty("BannerId"));
    }


    public void submit(ActionEvent event) throws NullPointerException, IOException {

        Properties prop = new Properties();
        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();

        if (BannerId.getText().length() != 9 || !isNumeric(BannerId.getText())) {
            alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
            return;
        } else prop.put(BannerId.getId(), BannerId.getText());


        int count = studentBorrowerCollection.findStudentsByBannerId(prop.getProperty(DBKey.BANNER_ID)).size();
        if (count == 0) {
            alertMessage.setText(language.getProperty("invalidBorrower"));
        } else alertMessage.setText(language.getProperty("existingBannerId") + prop.getProperty(DBKey.BANNER_ID));
    }
*/

    //show form to enter student banner id(save bannerID)
    //retrieve the students page ***call StudentBorrower- in student borrower
    //make sure borrower exist, matches and is in good standing(save borrowerID)
    //if does not exist display message to add borrower return to main screen
    //if not in good standing display message that the borrower may not borrow
/*
    protected ObservableList querySelector() {

        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();

        if (input != null || !input.equals("")) {

            if (search.equals(language.getProperty("BannerId"))) {
                if (input.length() == 9 && isNumeric(input)) {
                    Vector borrowers = studentBorrowerCollection.findStudentsByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(borrowers);
                } else alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
            }

            if (search.equals(language.getProperty("Status"))) {
                if (input.equals(language.getProperty("Active"))) {
                    Vector borrowers = studentBorrowerCollection.findStudentsByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(borrowers);
                } else alertMessage.setText(language.getProperty("invalidStatus"));
            }
        }
        if (input.equals("")) alertMessage.setText(language.getProperty("emptyField"));
        return null;
    }

}

*/
//show checkoutbookview to enter barcode of book(save barcode)

//find book and check to see if there is a rental page with that book already checked out
    //if page exist display message that book is already checked out
    //return to screen asking for bacode

//compute the due date based on current date plus 14 days(save date)

//Create a new rental page and update
    //-borrowerID,-bookID,-currentdate(for check out)
    //-checkInDate set to null, -checkInWorker set to null
    //then update rental folder

//Display screen with all information and a message of success


