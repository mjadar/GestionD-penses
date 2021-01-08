package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class SwitchScene {
    @FXML private Button btn_back;
    public void SwitchScene(ActionEvent e) throws IOException {
        Object o = e.getSource();
        Parent scene = null;
        Stage window=null;
        if(o.equals(btn_back)){ //rediriger vers la vue Sample.fxml (choix)
            window = (Stage) btn_back.getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("sample.fxml"));
            window.setTitle("Insert Data");
        }
        window.setScene(new Scene(scene));
        window.show();
    }
}
