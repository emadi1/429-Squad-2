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
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;


public class CheckOutBookController implements Initializable {

    private Core core = Core.getInstance();
    private Properties language = Core.getInstance().getLang();
    @FXML private Text checkOutBook;
    @FXML private Text bannerId;
    @FXML private TextField BannerIdField;
    @FXML private Text alertMessage;

    public void initialize(URL location, ResourceBundle resources) {
        checkOutBook.setText(language.getProperty("CheckOutBook"));
        bannerId.setText(language.getProperty("BannerId") + ":");
        BannerIdField.setPromptText(language.getProperty("BannerId"));
    }

}
//open worker folder and make sure password matches and is active
/*
    public void submit(ActionEvent event) throws NullPointerException, IOException {

        Properties prop = new Properties();
        WorkerCollection workerCollection = new WorkerCollection();

        if (bannerId.getText().length() != 9) {
            alertMessage.setText(language.getProperty("invalidBannerIdLength"));
            return;
        }

        int count = workerCollection.findWorkersByBannerId(prop.getProperty(DBKey.BANNER_ID)).size();
        if (count == 0) {
            alertMessage.setText(language.getProperty("invalidWorker"));
        } else alertMessage.setText(language.getProperty("existingBannerId") + prop.getProperty(DBKey.BANNER_ID));
    }


    protected ObservableList querySelector() {

        WorkerCollection workerCollection = new WorkerCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();

        if (input != null || !input.equals("")) {

            if (search.equals(language.getProperty("BannerId"))) {
                if (input.length() == 9 && isNumeric(input)) {
                    Vector workers = workerCollection.findWorkersByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
            }

            if (search.equals(language.getProperty("Status"))) {
                if (input.equals(language.getProperty("Active"))) {
                    Vector workers = workerCollection.findWorkerByStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText(language.getProperty("invalidStatus"));
            }
        }
        if (input.equals("")) alertMessage.setText(language.getProperty("emptyField"));
        return null;
    }

*/

//show form to enter student banner id(save bannerID)
    //retrieve the students page ***call StudentBorrower- in student borrower
    //make sure borrower exist, matches and is in good standing(save borrowerID)
    //if does not exist display message to add borrower return to main screen
    //if not in good standing display message that the borrower may not borrow





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


