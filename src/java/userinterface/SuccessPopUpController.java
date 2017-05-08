package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Elisha on 5/7/2017.
 */
public class SuccessPopUpController implements Initializable{

    private Core core = Core.getInstance();
    Properties language = core.getLang();

    @FXML private Text checkoutStatusPrompt;
    @FXML private Text checkoutStatusResponse;
    @FXML private Text titlePrompt;
    @FXML private Text titleResponse;
    @FXML private Text studentNamePrompt;
    @FXML private Text studentNameResponse;
    @FXML private Text currentDatePrompt;
    @FXML private Text currentDateResponse;
    @FXML private Text dueDatePrompt;
    @FXML private Text dueDateResponse;

    public void initialize(URL location, ResourceBundle resources) {

        checkoutStatusPrompt.setText(language.getProperty("CheckoutStatus"));
        checkoutStatusResponse.setText(language.getProperty("Successful"));
        titlePrompt.setText(language.getProperty("PromptTitle"));
        titleResponse.setText(core.getModBook().getTitle());
        studentNamePrompt.setText(language.getProperty("StudentBorrower"));
        studentNameResponse.setText(core.getModStudentBorrower().getFirstName() + " " + core
                .getModStudentBorrower().getLastName());
        currentDatePrompt.setText(language.getProperty("PromptCheckOutDate"));
        currentDateResponse.setText(core.generateDate());
        dueDatePrompt.setText(language.getProperty("PromptDueDate"));
        dueDateResponse.setText(core.getModRental().getDueDate());


    }
}
