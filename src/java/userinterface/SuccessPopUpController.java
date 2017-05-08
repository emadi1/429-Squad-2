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

    @FXML private Text success;

    public void initialize(URL location, ResourceBundle resources) {

        success.setText(core.getModBook().getTitle() + " " + language.getProperty("CheckOutSuccess") + " " +
                core.getModStudentBorrower().getFirstName() + " " + core.getModStudentBorrower().getLastName());
        success.setFont(Font.font(24));
        success.setTextAlignment(TextAlignment.CENTER);
    }
}
