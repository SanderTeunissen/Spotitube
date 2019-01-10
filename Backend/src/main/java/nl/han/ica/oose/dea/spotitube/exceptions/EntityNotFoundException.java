package nl.han.ica.oose.dea.spotitube.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class classType) {
        super(String.format("Couldn't find instance of class: %s", classType.getName()));
    }
}