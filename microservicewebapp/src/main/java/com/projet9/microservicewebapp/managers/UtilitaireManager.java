package com.projet9.microservicewebapp.managers;

import org.springframework.stereotype.Component;

import java.net.DatagramSocket;
import java.net.InetAddress;

@Component
public class UtilitaireManager {

    // Astuce pour récupérer l'adresse IP de la machine (serveur)
    public String getCurrentIPAddress(){
        String ip;
        try{
            final DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        }catch(Exception e){
            ip = "";
        }
        return ip;
    }

}
