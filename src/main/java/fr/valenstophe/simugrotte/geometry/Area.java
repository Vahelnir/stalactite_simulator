package fr.valenstophe.simugrotte.geometry;

import fr.valenstophe.simugrotte.util.RandomUtils;

public class Area {
    private Position2D position;

    private int height;
    private int width;

    public Area(int height, int width) {
        this(Position2D.origin(), height, width);
    }

    public Area(Position2D position, int height, int width) {
        this.position = position;
        this.height = height;
        this.width = width;
    }

    public boolean isInside(Position2D position) {
        return position.getX() >= getPosition().getX() && position.getX() <= getPosition().getX() + getWidth() &&
            position.getY() >= getPosition().getY() && position.getY() <= getPosition().getY() + getHeight();
    }

    public Position2D randomPosition() {
        int randomX = RandomUtils.randomBetween(0, getWidth());
        int randomY = RandomUtils.randomBetween(0, getHeight());
        return new Position2D(randomX, randomY);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }
}
