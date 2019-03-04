package com.projet9.microservicewebapp.managers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

@Component
public class ImagesManager {

    public static final String entiteeUser = "user";
    public static final String entiteeAventure = "aventure";

    // Value des images par defaut
    @Value("classpath:/static/img/user.jpg")
    private Resource defaultImageUser;
    @Value("classpath:/static/img/icon4.png")
    private Resource defaultImageAventure;


    public byte[] getDefaultImage(String entitee) throws Exception{
        File f;
        if(entiteeUser.equals(entitee)){
            f = defaultImageUser.getFile();
        }else{
            f = defaultImageAventure.getFile();
        }
        byte[] imageBytes = new byte[(int) f.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(f));
        dis.readFully(imageBytes);
        return imageBytes;
    }
}
