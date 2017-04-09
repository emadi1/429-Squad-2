package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utilities.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by JJ on 4/7/2017.
 */
public class CheckOutBookViewController implements Initializable {


    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private ArrayList<TextField> textFieldList;

    @FXML private Text barcode;
    @FXML private TextField Barcode;
    @FXML private Button submit;

    public void initialize(URL location, ResourceBundle resources) {
        barcode.setText(language.getProperty("PromptBarcode"));
    }


}
