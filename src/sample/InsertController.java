package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class InsertController {
    @FXML private TextField dejeuner;
    @FXML private TextField midi;
    @FXML private TextField diner;
    @FXML private TextField courses;
    @FXML private TextField taxi;
    @FXML private TextField factures;
    @FXML private TextField other_titre;
    @FXML private TextField other_prix;
    @FXML private Button btn_save_insert;

    @FXML public void initialize(){
        dejeuner.setText("0");
        midi.setText("0");
        diner.setText("0");
        courses.setText("0");
        taxi.setText("0");
        factures.setText("0");
        other_prix.setText("0");
    }

    @FXML public void SaveData(ActionEvent e) {
        int dej = Integer.parseInt(dejeuner.getText());
        int md = Integer.parseInt(midi.getText());
        int dn= Integer.parseInt(diner.getText());
        int tx = Integer.parseInt(taxi.getText());
        int cs = Integer.parseInt(courses.getText());
        int fc = Integer.parseInt(factures.getText());
        int ot = Integer.parseInt(other_prix.getText());
//        System.out.println(dej,md,dn,tx,cs,fc);
//        SimpleDateFormat formatter= new SimpleDateFormat("dd--MM--yyyy");
        try{
            Date date_j = new Date(System.currentTimeMillis());
            OracleConnect.InsertSaving(date_j,dej,md,dn,cs,tx,fc,ot);
            if(!other_titre.getText().trim().isEmpty()){
                OracleConnect.InsertOther(date_j,other_titre.getText(),ot);
            }
            JOptionPane.showMessageDialog(null,"Les données sont enregistrées");
            redirect(btn_save_insert);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    public void redirect(Button btn) throws IOException {
        Stage window = (Stage) btn.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setScene(new Scene(scene, 615, 395));
        window.show();
    }




}
