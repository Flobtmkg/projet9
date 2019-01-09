package com.projet9.dataexchange.exceptions;

public class ObjectNotFoundException extends Exception{
    // un objet n'a pas été trouvé en base
    public ObjectNotFoundException(int i, Class c){
        super("L'objet de classe "+ c.getName() + " n°" + i + "n'a pas pu être trouvé dans la base de donnée");
    }
}
