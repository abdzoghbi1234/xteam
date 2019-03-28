/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import Utils.SingletonNavigation;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class LoginController implements Initializable {
//
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private ImageView imgblur;
    private static final Effect frostEffect =
    new BoxBlur(10, 10, 10);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgblur.setEffect(frostEffect);
    }    

    @FXML
    private void Login(ActionEvent event) {
        UserService us = new UserService();
        if(us.authentication(email.getText(), password.getText()))
        {
            User u = us.getByEmail(email.getText());
            if(u.getToken().equals("active"))
            {
                SingletonNavigation MyNavigation = SingletonNavigation.getInstance();
                MyNavigation.setLoggedInUser(u);
                //MyNavigation.getLoggedInUser().setContactList(us.getContactList(SingletonNavigation.getInstance().getLoggedInUser().getId()));
                //MyNavigation.getNavCont().initToolBar();
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow() ;
                stage.setScene(new Scene(MyNavigation.getRoot()));
                stage.show();
            }
        }
    }

    @FXML
    private void Inscrit(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow() ;
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml"))));
        stage.show();
    }
    
}
