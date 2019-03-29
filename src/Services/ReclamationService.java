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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
     
    public void reclamer(Reclamation r, User u){        
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
    
    public ArrayList<Reclamation> getAllReclamation(){
        ArrayList<Reclamation> listRec = new ArrayList<>();
        try {
            String query  = "SELECT * "
                          + "FROM reclamation";
            PreparedStatement statement = cnx.prepareStatement(query);
           
            ResultSet rs = statement.executeQuery();    
            Reclamation s;
            while(rs.next()){
                s = new Reclamation.Builder()
                        .idReclamation(rs.getLong("id"))
                        .etat(rs.getString("etat"))
                        .date(LocalDatePersistenceConverter.convertToEntityAttribute(rs.getTimestamp("date")))
                        .description(rs.getString("description"))
                        .type(rs.getString("type"))
                        .idUser(rs.getLong("idUser"))
                        .build();
                listRec.add(s);
            }
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listRec;
    }
    
    public void traiter(Reclamation r, User u){        
        try {
            String query  = "UPDATE Reclamation SET etat = treated where id = ? ";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.executeUpdate();            
            System.out.println(r.getType().toString() + " Réclamation traité");    
            NotificationService ns = new NotificationService();
            ns.notifier(u, r);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
