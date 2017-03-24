package userinterface;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Worker;
import userinterface.SingletonTesting;

import java.io.IOException;

/**
 * Created by Ders on 3/23/2017.
 */
class AddWorkerCell extends TableCell<Worker, Boolean> {

    final Button addButton = new Button("Modify");
    final StackPane paddedButton = new StackPane();
    SingletonTesting sing;

    /**
     * AddPersonCell constructor
     *
     * @param stage the stage in which the table is placed.
     * @param table the table to which a new worker can be added.
     */
    AddWorkerCell(final Stage stage, final TableView table) {
        paddedButton.setPadding(new Insets(3));
        paddedButton.getChildren().add(addButton);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showModifyPersonDialog();
                table.getSelectionModel().select(getTableRow().getIndex());
                ObservableList obl = table.getSelectionModel().getSelectedItems();
                //String s = table.getSelectionModel().getSelectedItems().toArray()[0].toString();
                SingletonTesting.setModWorkerBanner((Worker) table.getSelectionModel().getSelectedItems().get(1));
                //sing.setModWorkerBanner(s);
                //sing.setModWorkerBanner("df");
                //System.out.println(sing.getModWorkerBanner());
               // System.out.println(s);
                System.out.println( SingletonTesting.getModWorkerBanner().getBannerId());

            }
        });
    }

    /**
     * places an add button in the row only if the row is not empty.
     */
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(paddedButton);
        } else {
            setGraphic(null);
        }
    }

    private void showModifyPersonDialog() {

        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyworkerview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Brockport Library System");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
