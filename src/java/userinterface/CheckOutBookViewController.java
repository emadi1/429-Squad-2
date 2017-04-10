
package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.StudentBorrower;
import models.StudentBorrowerCollection;
import models.Worker;
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
    @FXML private Button submit;
    @FXML private Text alertMessage;

    public void initialize(URL location, ResourceBundle resources) {
        bannerId.setText(language.getProperty("PromptBannerId"));
        BannerId.setPromptText(language.getProperty("BannerId"));
        submit.setText(language.getProperty("Search"));
//      if (user.getCredentials().equals("Ordinary")) submit.setDisable(true);
    }

    /**
     * Creates student borrower collection given a BannerId.
     * If student's BorrowerStatus is in good standing.
     *      - Launch barcode window
     * Else launch override/cancel window.
     */
    public void submit() {
        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
        Vector students = studentBorrowerCollection.findStudentsByBannerId(BannerId.getText());
        if (students.size() == 0){
            alertMessage.setText("No Student by that Banner ID.");
        } else {
            String borrowerStatus = (String)students.get(4);
            if (borrowerStatus.equals(language.getProperty("GoodStanding"))) {
                // launch new view
            } else {
                // ALERT WINDOW
                alertMessage.setText("Dis guy cant rent shit");
                // Administrator override?
            }
        }

    }


}
