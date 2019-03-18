package com.projet9.microservicewebapp.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ImagesManager {

    public static final String entiteeUser = "user";
    public static final String entiteeAventure = "aventure";


    private static final String userImage = "static/img/user.jpg";
    private static final String aventureImage = "static/img/icon4.png";

    @Autowired
    ResourceLoader resourceLoader;

    public byte[] getDefaultImage(String entitee) throws Exception{
        InputStream is;
        if(entiteeUser.equals(entitee)){
            is = resourceLoader.getClassLoader().getResourceAsStream(userImage);
        }else{
            is = resourceLoader.getClassLoader().getResourceAsStream(aventureImage);
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
