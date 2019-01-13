package com.projet9.dataexchange.decoder;

import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    // Ré-écriture du comportement de propagation des erreurs de Feign
    // Déclanché lors d'une erreur dans les services métiers gérés par Feign
    // Cette erreur est alors propagée aux autres couches

    @Override
    public Exception decode(String s, Response response) {
        // Exception type 400
        if(response.status()>=400 && response.status()<500){
            return new ObjectNotFoundException(response.reason());
        }
        return defaultErrorDecoder.decode(s,response);
    }
}
