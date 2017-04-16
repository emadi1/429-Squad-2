package userinterface;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * Created by kevph on 3/7/2017.
 */

public class CheckInBookController implements Initializable {

    private Properties language = Core.getInstance().getLang();
    @FXML private Text checkInBook;

    public void initialize(URL location, ResourceBundle resources) {
        checkInBook.setText(language.getProperty("CheckInBook"));

    }

    public void submit() {

    }
}
