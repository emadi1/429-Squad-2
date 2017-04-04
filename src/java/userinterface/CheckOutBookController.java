package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/7/2017.
 */
public class CheckOutBookController implements Initializable {

    private Properties language = Core.getInstance().getLang();
    @FXML private Text checkOutBook;
    @FXML private Text bannerId;
    @FXML private TextField BannerIdField;

    public void initialize(URL location, ResourceBundle resources) {
        checkOutBook.setText(language.getProperty("CheckOutBook"));
        bannerId.setText(language.getProperty("BannerId") + ":");
        BannerIdField.setPromptText(language.getProperty("BannerId"));
    }


}
