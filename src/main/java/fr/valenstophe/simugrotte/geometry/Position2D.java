package fr.valenstophe.simugrotte.geometry;

public class Position2D implements Cloneable {
    private int x;
    private int y;

    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position2D origin() {
        return new Position2D(0, 0);
    }

    public boolean isInRadiusOf(Position2D position, int radius) {
        return position.isInRadius(this, radius);
    }

    public boolean isInRadius(Position2D position, int radius) {
        int x = (int) Math.pow(getX() - position.getX(), 2);
        int y = (int) Math.pow(getY() - position.getY(), 2);
        return radius >= Math.sqrt(x + y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position2D position) {
        return position.x == x && position.y == y;
    }

    @Override
    public String toString() {
        return "Position2D {" +
            " x: " + x +
            ", y: " + y +
            " }";
    }

    @Override
    public Position2D clone() {
        try {
            Position2D clone = (Position2D) super.clone();
            clone.setX(getX());
            clone.setY(getY());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
