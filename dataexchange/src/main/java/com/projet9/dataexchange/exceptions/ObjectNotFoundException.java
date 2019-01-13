package com.projet9.dataexchange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

    // Exception pour gérer les erreurs "not found" dans les API's
    //
    // déclanchée une première fois dans le service métier pour informer Feign de l'erreur
    // elle est déclanchée une seconde fois en écrasant le comportement du FeignErrorDecoder
    // pour caractériser l'erreur lors de la propagation aux autres couches


    // un objet n'a pas été trouvé en base
    public ObjectNotFoundException(int i, Class c){
        super("L'objet "+ c.getSimpleName() + " portant l'identifiant n°" + i + " n'a pas pu être trouvé dans la base de donnée");
    }

    public ObjectNotFoundException(String strErr){
        super(strErr);
    }
}
