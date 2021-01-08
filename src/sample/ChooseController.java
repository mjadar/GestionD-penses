package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseController {
    @FXML private Button btn_update;
    @FXML private Button btn_insert;
    @FXML private Button btn_load;

     @FXML public void SwitchScene(ActionEvent e) throws IOException {
        Object o = e.getSource();
        Parent scene = null;
        Stage window=null;
        if(o.equals(btn_insert)){ //rediriger vers la vue InsertView.fxml
            window = (Stage) btn_insert.getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("insertView.fxml"));
            window.setTitle("Insert Data");
        }
        else if (o.equals(btn_load)){ //rediriger vers la vue LoadView.fxml
            window = (Stage) btn_load.getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("LoadView.fxml"));
            window.setTitle("Loading Data");
        }
        window.setScene(new Scene(scene));
        window.show();
    }
}
