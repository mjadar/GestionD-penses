package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class LoginController {
    @FXML private PasswordField txt_password ;
    @FXML private TextField txt_username ;
    @FXML private Button btn_signin;

    @FXML public void checkLogin(ActionEvent e ) throws IOException, SQLException {
       String password = txt_password.getText().trim();
       String username = txt_username.getText().trim();
        String hashed_password = getMd5(password);
 if(OracleConnect.dbCheckLog(username,hashed_password)==true){
//       if(true){
          Stage window = (Stage) btn_signin.getScene().getWindow();
          Parent scene = FXMLLoader.load(getClass().getResource("sample.fxml"));
           window.setScene(new Scene(scene));
           window.show();
       }else {
           JOptionPane.showMessageDialog(null,"Password incorrect");
       }
    }
    
    public String getMd5(String input)
    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
