
package sample;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.Date;
        import java.sql.SQLException;
        import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    private TableView<Saving> table;

    @FXML
    private TableColumn<Saving, Integer> pos;

    @FXML
    private TableColumn<Saving, Date> date_j;

    @FXML
    private TableColumn<Saving, Integer> dejeuner;

    @FXML
    private TableColumn<Saving, Integer> midi;

    @FXML
    private TableColumn<Saving, Integer> diner;

    @FXML
    private TableColumn<Saving, Integer> courses;

    @FXML
    private TableColumn<Saving, Integer> taxi;

    @FXML
    private TableColumn<Saving, Integer> factures;

    @FXML
    private TableColumn<Saving, Integer> other;

    @FXML
    private TableColumn<Saving, Integer> total;

    private ObservableList<Saving> SavListe = FXCollections.observableArrayList(OracleConnect.dbGetSavings(""));

    @FXML
    private RadioButton days30;

    @FXML
    private ToggleGroup daysView;

    @FXML
    private RadioButton days14;

    @FXML
    private RadioButton days7;

    @FXML
    private Button btn_upd_view;

    @FXML
    private Button btn_accueil ;


    public LoadController() throws SQLException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(SavListe);
        Optimizer();
    }
    public void Optimizer(){
        pos.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("pos"));
        date_j.setCellValueFactory(new PropertyValueFactory<Saving,Date>("date_jour"));
        dejeuner.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("dejeuner"));
        midi.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("midi"));
        diner.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("diner"));
        courses.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("courses"));
        taxi.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("taxi"));
        factures.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("factures"));
        other.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("other"));
        total.setCellValueFactory(new PropertyValueFactory<Saving,Integer>("total"));
    }

    public void UpdateListView(ActionEvent actionEvent) {
        table.getItems().clear();
        try{
            if(days7.isSelected()==true){
                ObservableList<Saving> tmp = FXCollections.observableArrayList(OracleConnect.dbGetSavings("7"));
                table.setItems(tmp);
            }else if(days14.isSelected()==true){
                ObservableList<Saving> tmp = FXCollections.observableArrayList(OracleConnect.dbGetSavings("14"));
                table.setItems(tmp);

            }else if(days30.isSelected()==true){
                ObservableList<Saving> tmp = FXCollections.observableArrayList(OracleConnect.dbGetSavings("30"));
                table.setItems(tmp);
            } Optimizer();
        }catch(Exception er){
            er.printStackTrace();
        }
    }


    @FXML public void SwitchSceneL(ActionEvent e) throws IOException {
        Object o = e.getSource();
        Parent scene = null;
        Stage window=null;
        if(o.equals(btn_accueil)){ //rediriger vers la vue Sample.fxml (choix)
            window = (Stage) btn_accueil.getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("sample.fxml"));
            window.setTitle("Accueil");
        }
        window.setScene(new Scene(scene));
        window.show();
    }
}
