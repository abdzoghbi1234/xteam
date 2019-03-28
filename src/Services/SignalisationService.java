/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Signalisation;
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
public class SignalisationService {
    
    Connection cnx;

    public SignalisationService() {
        try {
            this.cnx = DatabaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(SignalisationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void Signaler(Signalisation e, User u){        
        try {
            String query  = "INSERT INTO signalisation (date, description, type, idSignaled, idUser)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            
            statement.setTimestamp(1, LocalDatePersistenceConverter.convertToDatabaseColumn(e.getDate()));
            statement.setString(2, e.getDescription());
            statement.setString(3, e.getsType().toString());
            statement.setLong(4, e.getidSignaled());
            statement.setLong(5, u.getId());
           
            statement.executeUpdate();
            
            System.out.println(e.getsType().toString() + " Signalé");
            
            // delete item if signaled more than 3 time
            if(getNumberOfSignalisation(e) == 4){
                deleteSignaled(e);
                System.out.println(e.getsType().toString() + " deleted");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Signalisation> getAllSignalisation(){
        ArrayList<Signalisation> listSign = new ArrayList<>();
        try {
            String query  = "SELECT * "
                          + "FROM signalisation";
            PreparedStatement statement = cnx.prepareStatement(query);
           
            ResultSet rs = statement.executeQuery();    
            Signalisation s;
            while(rs.next()){
                s = new Signalisation.Builder()
                        .idSignalisation(rs.getLong("id"))
                        .idSignaled(rs.getLong("idSignaled"))
                        .idUser(rs.getLong("idUser"))
                        .date(LocalDatePersistenceConverter.convertToEntityAttribute(rs.getTimestamp("date")))
                        .description(rs.getString("description"))
                        .sType(rs.getString("type"))
                        .build();
                listSign.add(s);
            }
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listSign;
    }
    
    private int getNumberOfSignalisation(Signalisation e){  
        int count = 0;
        try {
            String query  = "SELECT * "
                          + "FROM signalisation "
                          + "WHERE idSignaled = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setLong(1, e.getidSignaled());
            ResultSet rs = statement.executeQuery();            
            while(rs.next()){
                count++;
            }
            System.out.println(count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    
    private void deleteSignaled(Signalisation e){
        try {
            String query  = "DELETE FROM " + e.getsType()
                          + " WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setLong(1, e.getidSignaled());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       
    }
    
    
    
}
