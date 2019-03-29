/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Notification;
import Entities.Reclamation;
import Entities.User;
import Utils.DatabaseConnection;
import Utils.LocalDatePersistenceConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZOGHBI
 */
public class NotificationService {
    
     Connection cnx;

    public NotificationService() {
         try {
            this.cnx = DatabaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(SignalisationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void notifier(User u, Reclamation r){
        try {
            String query  = "INSERT INTO notifications (date, description, idUser, idRec)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            
            statement.setTimestamp(1, LocalDatePersistenceConverter.convertToDatabaseColumn(r.getDate()));
            statement.setString(2, "Réclamation tratié");
            statement.setLong(3, u.getId());
            statement.setLong(4, r.getIdReclamation());
           
            statement.executeUpdate();
            
            System.out.println(r.getType().toString() + " Notification envoyoé");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Notification> getAllNotifications(){
        ArrayList<Notification> listNot = new ArrayList<>();
        try {
            String query  = "SELECT * "
                          + "FROM notification";
            PreparedStatement statement = cnx.prepareStatement(query);
           
            ResultSet rs = statement.executeQuery();    
            Notification n;
            while(rs.next()){
                n = new Notification.Builder()
                        .id(rs.getLong("id"))
                        .date(LocalDatePersistenceConverter.convertToEntityAttribute(rs.getTimestamp("date")))
                        .description(rs.getString("description"))                        
                        .idUser(rs.getLong("idUser"))
                        .idRec(rs.getLong("idRec"))
                        .build();
                listNot.add(n);
            }
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listNot;
    }
}
