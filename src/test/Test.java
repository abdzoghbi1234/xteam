/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entities.Event;
import Entities.Pub;
import Entities.Signalisation;
import Entities.User;
import Enum.SignalisationType;
import Services.SignalisationService;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ZOGHBI
 */
public class Test {
    
    public static void main(String [] args){
        
        SignalisationService sService = new SignalisationService();
        
        User user = new User();
        user.setId(1477);
       
        Event ev = new Event(4);
        
        Signalisation eventSignaled = new Signalisation.Builder()
                .idUser(user.getId())
                .idSignaled(ev.getId())
                .description("aaaaaa")
                .date(LocalDateTime.now())
                .sType(SignalisationType.Event.name())
                .build();
        
        eventSignaled.print();
        sService.Signaler(eventSignaled, user);
        
        sService.getAllSignalisation().forEach(System.out::println);
        
//        User user2 = new User();
//        user2.setId(47441);
//        
//        Signalisation u = new Signalisation.Builder()
//                .idUser(0)
//                .idSignaled(user2.getId())
//                .description("aaaaaa")
//                .date(LocalDateTime.now())
//                .sType(SignalisationType.User.name())
//                .build();
//        //u.print();
//        //sService.Signaler(u, user);
//        Pub pub = new Pub(147);
//        Signalisation p = new Signalisation.Builder()
//                .idUser(0)
//                .idSignaled(pub.getId())
//                .date(LocalDateTime.now())
//                .sType(SignalisationType.Pub.name())
//                .build();
       // p.print();
        //sService.Signaler(p, user);
        
       
    }
}
