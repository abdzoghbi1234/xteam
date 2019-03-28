/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import Entities.User;
import Utils.DatabaseConnection;
import Utils.LocalDatePersistenceConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZOGHBI
 */
public class ReclamationService {
    
     Connection cnx;

    public ReclamationService() {
         try {
            this.cnx = DatabaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(SignalisationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void Reclamer(Reclamation r, User u){        
        try {
            String query  = "INSERT INTO Reclamation (date, description, type, etat, idUser)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            
            statement.setTimestamp(1, LocalDatePersistenceConverter.convertToDatabaseColumn(r.getDate()));
            statement.setString(2, r.getDescription());
            statement.setString(3, r.getType().name());
            statement.setString(4, r.getEtat().name());
            statement.setLong(5, u.getId());
           
            statement.executeUpdate();
            
            System.out.println(r.getType().toString() + " Réclamation envoyoé");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
