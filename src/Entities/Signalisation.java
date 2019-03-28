/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Enum.SignalisationType;
import java.time.LocalDateTime;

/**
 *
 * @author ZOGHBI
 */
public class Signalisation {
    private long idSignalisation; 
    private LocalDateTime date;
    private String description;
    private SignalisationType sType;
    
    private long idSignaled;
    private long idUser;

    private Signalisation() {
    }

    private Signalisation(Signalisation.Builder builder){
        idSignalisation = builder.idSignalisation;
        idSignaled = builder.idSignaled;
        idUser = builder.idUser;
        date = builder.date;
        description = builder.description;
        sType = builder.sType;
    }

    public long getIdSignalisation() {
        return idSignalisation;
    }

    public void setIdSignalisation(long idSignalisation) {
        this.idSignalisation = idSignalisation;
    }

    public long getIdSignaled() {
        return idSignaled;
    }

    public void setIdSignaled(long idSignaled) {
        this.idSignaled = idSignaled;
    }

    
    
    public long getidSignaled() {
        return idSignaled;
    }

    public void setidSignaled(long idSignaled) {
        this.idSignaled = idSignaled;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SignalisationType getsType() {
        return sType;
    }

    public void setsType(SignalisationType sType) {
        this.sType = sType;
    }

    @Override
    public String toString() {
        return "Signalisation{" + "idSignaled=" + idSignaled + ", idUser=" + idUser + ", date=" + date + ", description=" + description + ", sType=" + sType + '}';
    }

    
    
    public void print(){
        System.out.println(toString());
    }
    
    public static class Builder{
    
        private long idSignalisation = 0;
        private long idSignaled = 0;
        private long idUser = 0;
        private LocalDateTime date = LocalDateTime.now();
        private String description = "";
        private SignalisationType sType = null;
        
         public Signalisation.Builder idSignalisation(long val){
            this.idSignalisation = val;
            return this;
        }   
       
        public Signalisation.Builder idSignaled(long val){
            this.idSignaled = val;
            return this;
        }        
        
        public Signalisation.Builder idUser(long val){
            this.idUser = val;
            return this;
        }
        
        public Signalisation.Builder date(LocalDateTime val){
            this.date = val;
            return this;
        }
                
        public Signalisation.Builder description(String val){
            this.description = val;
            return this;
        }
        
        public Signalisation.Builder sType(String val){
            switch (val) {
                case "Event":
                    this.sType = SignalisationType.Event;
                    break;
                case "Pub":
                    this.sType = SignalisationType.Pub;
                    break;
                case "User":
                    this.sType = SignalisationType.User;
                    break;
                default:
                    break;
            }
            return this;
        }   
        
        public Signalisation build(){
          return new Signalisation(this);
        }
        
    }
}
