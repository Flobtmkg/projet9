package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.dataexchange.proxies.ProxyUser;
import com.projet9.microservicewebapp.managers.ImagesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class ImagesController {

    @Autowired
    ProxyAventure proxyAventure;

    @Autowired
    ProxyUser proxyUser;

    @Autowired
    ImagesManager imagesManager;


    @PostMapping("/upload")
    public RedirectView userFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        User user = (User)request.getSession().getAttribute("userGuest");
        if(file!=null && !file.isEmpty()){
            user.setImage(file.getBytes());
            proxyUser.update(user);
        }
        return new RedirectView("/espaceutilisateur#ModalValidation");
    }


    @GetMapping("/images/aventure/{id}")
    public void getImageByAventureId(@PathVariable("id") int idAventure, HttpServletResponse response){
        // Si on ne veut afficher que l'image par défaut on peut l'appeller avec /images/aventure/0
        if(idAventure!=0){
            imageProcedure(response, proxyAventure.getImageById(idAventure),ImagesManager.entiteeAventure);
        }else{
            imageProcedure(response, null,ImagesManager.entiteeAventure);
        }
    }

    @GetMapping("/images/user/{id}")
    public void getImageByUserId(@PathVariable("id") int idUser, HttpServletResponse response){
        // On get l'image en byte[] depuis l'entité user
        imageProcedure(response, proxyUser.getImageById(idUser),ImagesManager.entiteeUser);
    }


    private void imageProcedure(HttpServletResponse response, byte[] image, String entitee){
        try{
            addImageToResponse(response, image);
        }catch (Exception e){
            try{
                addImageToResponse(response, imagesManager.getDefaultImage(entitee));
            }catch(Exception e2){
                response.setStatus(404);
            }
        }
    }


    private void addImageToResponse(HttpServletResponse response, byte[] image) throws Exception{
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(image);
        response.getOutputStream().close();
    }


    /*private byte[] getDefaultImage(String entitee) throws Exception{
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
    }*/
}
