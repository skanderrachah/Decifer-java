/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Planinng;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.PlaninngService;

/**
 * FXML Controller class
 *
 * @author skanr
 */
public class ModifierPlaninngController implements Initializable {

    private TextField nomplaninngmodif;
    Planinng p=new Planinng();
    @FXML
    private TextField nomplanningmodif;
    @FXML
    private TextField periodeplanningmodif;
    @FXML
    private TextField prixplanningmodif;
    @FXML
    private DatePicker dateDebutplanningmodif;
    @FXML
    private DatePicker dateFinplanningmodif;
    @FXML
    private TextField destinationplanningmodif;
    @FXML
    private TextArea descriptionplanningmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  @FXML
    private void modifier(ActionEvent event) throws FileNotFoundException, IOException {
        if (p == null) {

            System.out.println("Choisir un planinng");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modifier planinng");
            alert.setHeaderText(null);
            alert.setContentText("Le planinng n'est pas modifié!");

            alert.showAndWait();
        }else {
        p.setNomPlanning(nomplanningmodif.getText());
        p.setDateDebutPlanning(Date.valueOf(dateDebutplanningmodif.getValue()));
        p.setDateFinPlanning(Date.valueOf(dateFinplanningmodif.getValue()));
        p.setDestinationPlanning(destinationplanningmodif.getText());
        p.setDescriptionPlanning(descriptionplanningmodif.getText());
        p.setPeriodePlanning(Integer.parseInt(periodeplanningmodif.getText()));
        p.setPrixPlanning(Integer.parseInt(prixplanningmodif.getText()));
            PlaninngService ps = new PlaninngService();
             try{
             ps.updatePlaninng(p);
             System.out.println("ok");}
             catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("Modification terminé");}
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification terminée avec succès.");
        alert.setHeaderText(null);
    alert.setContentText("Votre planinng a été modifié avec succés.");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichePlaninng.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
     void setData(int id, String sub,Date db,Date df, String content, String des, int pr, int pe) {
        p.setId(id);
        nomplaninngmodif.setText(sub);
       // p.setDateDebutPlanning(Date.valueOf(dateDebutplanningmodif.getValue()));
       // p.setDateFinPlanning(Date.valueOf(dateFinplanningmodif.getValue()));
        destinationplanningmodif.setText(content);
        descriptionplanningmodif.setText(des);
        p.setPrixPlanning(pr);
        p.setPeriodePlanning(pe);
    }
}
