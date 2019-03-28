/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class NavigationController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane navBar;
    @FXML
    private AnchorPane Content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadProfile(ActionEvent event) throws Exception {
        Content = FXMLLoader.load(getClass().getResource("/Gui/Profile/Profile.fxml"));
        root.getChildren().set(1, Content);
        Content.setLayoutY(70);
    }

    @FXML
    private void loadEvents(ActionEvent event) throws Exception{
        Content = FXMLLoader.load(getClass().getResource("/Gui/Events/allEvents.fxml"));
        root.getChildren().set(1, Content);
        Content.setLayoutY(70);
    }
    
}
