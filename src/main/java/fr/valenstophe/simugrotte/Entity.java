package fr.valenstophe.simugrotte;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.valenstophe.simugrotte.geometry.Position2D;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class Entity {

    private Position2D position;
    private boolean isRemoved = false;

    public Entity(Position2D position) {
        this.position = position;
    }

    public abstract void tick(int currentTick);

    public abstract int getDiameter();

    public String getType() {
        return getClass().getSimpleName();
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
