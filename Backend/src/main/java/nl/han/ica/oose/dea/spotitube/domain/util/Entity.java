package nl.han.ica.oose.dea.spotitube.domain.util;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IEntity;

public class Entity implements IEntity {
    private int ID;

    public Entity(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
}
