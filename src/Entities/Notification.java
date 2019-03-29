/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDateTime;

/**
 *
 * @author ZOGHBI
 */
public class Notification {
    
    private long id;    
    private String description;    
    private long idUser;
    private long idRec;
    private LocalDateTime date;
    
    private Notification() {
    }

    private Notification(Notification.Builder builder){
        id = builder.id;
        idUser = builder.idUser;
        description = builder.description;
        idRec = builder.idRec;
        date = builder.date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    
    
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdRec() {
        return idRec;
    }

    public void setIdRec(long idRec) {
        this.idRec = idRec;
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", description=" + description + ", idUser=" + idUser + ", idRec=" + idRec + '}';
    }

    
    public void print(){
        System.out.println(toString());
    }
    
    public static class Builder{
    
        private long id = 0;
        private long idUser = 0;
        private String description = "";
        private long idRec = 0;
        private LocalDateTime date;
        
         public Notification.Builder id(long val){
            this.id = val;
            return this;
        }   
       
        public Notification.Builder idUser(long val){
            this.idUser = val;
            return this;
        }
        
        
        public Notification.Builder date(LocalDateTime val){
            this.date = val;
            return this;
        }
                       
        public Notification.Builder description(String val){
            this.description = val;
            return this;
        }
               
        public Notification.Builder idRec(long val){
            this.idRec = val;
            return this;
        }
        
        public Notification build(){
          return new Notification(this);
        }
        
    }
}
