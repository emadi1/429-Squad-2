package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Kevin on 3/8/2017.
 */
public class SignInController implements Initializable {

    ArrayList<TextField> textFieldList;
    @FXML
    TextField bannerId;
    @FXML
    TextField password;

    public SignInController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(bannerId);
        textFieldList.add(password);
    }

    /**
     *
     * @param actionEvent
     */
    public void submit(ActionEvent actionEvent) {

    }

    /**
     *
     * @param actionEvent
     */
    public void skip(ActionEvent actionEvent) {

    }
}
