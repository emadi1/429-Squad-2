package userinterface;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevph, I did some stuff too I swear, on 3/11/2017.
 */
public abstract class TransactionController implements Initializable {

    ObservableList<String> properties;
    @FXML protected Text workerHeader;
    @FXML protected Text bookHeader;
    @FXML protected Text studentHeader;
    @FXML protected TableView tableView;
    @FXML protected ChoiceBox<String> searchChoice;
    @FXML protected TextField searchField;
    @FXML protected Button search;
    @FXML protected Button add;

    public TransactionController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void search(ActionEvent actionEvent) {
        ObservableList observableList = querySelector();
        if (observableList != null) {
            tableView.setItems(observableList);
        }

    }

    protected static boolean isNumeric(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    protected abstract void add(ActionEvent actionEvent) throws NullPointerException, IOException;

    protected abstract ObservableList<String> itemsSearchChoiceArray();

    protected abstract void setTableView() throws IOException;

    protected abstract ObservableList querySelector();

}
