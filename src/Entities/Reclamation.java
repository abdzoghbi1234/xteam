/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Enum.ReclamationEtat;
import Enum.ReclamationType;
import java.time.LocalDateTime;

/**
 *
 * @author ZOGHBI
 */
public class Reclamation {
    
    private long idReclamation;   
    
    private ReclamationType type;
    private LocalDateTime date;
    private String description;
    private ReclamationEtat etat;    
    private long idUser;

    private Reclamation() {
    }

    private Reclamation(Reclamation.Builder builder){
        idReclamation = builder.idReclamation;
        idUser = builder.idUser;
        date = builder.date;
        description = builder.description;
        type = builder.type;
    }

    public long getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(long idReclamation) {
        this.idReclamation = idReclamation;
    }

    public ReclamationType getType() {
        return type;
    }

    public void setType(ReclamationType type) {
        this.type = type;
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

    public ReclamationEtat getEtat() {
        return etat;
    }

    public void setEtat(ReclamationEtat etat) {
        this.etat = etat;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", type=" + type + ", date=" + date + ", description=" + description + ", etat=" + etat + ", idUser=" + idUser + '}';
    }
    
    
    public void print(){
        System.out.println(toString());
    }
    
    public static class Builder{
    
        private long idReclamation = 0;     
        private ReclamationType type = null;
        private LocalDateTime date = LocalDateTime.now();
        private String description = "";
        private ReclamationEtat etat = null;
        private long idUser = 0;

        
         public Reclamation.Builder idReclamation(long val){
            this.idReclamation = val;
            return this;
        }   
              
        
        public Reclamation.Builder idUser(long val){
            this.idUser = val;
            return this;
        }
        
        public Reclamation.Builder date(LocalDateTime val){
            this.date = val;
            return this;
        }
                
        public Reclamation.Builder description(String val){
            this.description = val;
            return this;
        }
        
          public Reclamation.Builder etat(String val){
            switch (val) {
                case "treated":
                    this.etat = ReclamationEtat.treated;
                    break;
                case "pending":
                    this.etat = ReclamationEtat.pending;
                    break;
                default:
                    break;
            }
            return this;
        } 
        
        public Reclamation.Builder type(String val){
            switch (val) {
                case "a":
                    this.type = ReclamationType.a;
                    break;
                case "b":
                    this.type = ReclamationType.b;
                    break;
                case "c":
                    this.type = ReclamationType.c;
                    break;
                default:
                    break;
            }
            return this;
        }   
        
        public Reclamation build(){
          return new Reclamation(this);
        }
        
    }
}
