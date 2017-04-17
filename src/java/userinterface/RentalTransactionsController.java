package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevtr0n on 4/15/17.
 */

public abstract class RentalTransactionsController implements Initializable {

    private Core core = Core.getInstance();
    @FXML protected Text checkInHeader, checkOutHeader;
    @FXML protected TableView tableView;
    @FXML protected TextField searhField;
    @FXML protected Button submit;

    public RentalTransactionsController() {

    }
    public void initialize(URL location, ResourceBundle resources) {

    }
    protected abstract void setTableView() throws IOException;
    protected abstract int submit();

}
