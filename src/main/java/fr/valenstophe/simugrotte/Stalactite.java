package fr.valenstophe.simugrotte;

import fr.valenstophe.simugrotte.geometry.Position2D;
import fr.valenstophe.simugrotte.util.RandomUtils;

public class Stalactite extends Entity implements Fallable, Growable {
    private boolean isSealed = false;

    private int height = 0;

    public Stalactite(Position2D position) {
        super(position);
    }

    @Override
    public void tick(int currentTick) {}


    @Override
    public void grow() {
        this.height += RandomUtils.randomBetween(0, 2);
        int chanceToSeal = RandomUtils.randomBetween(1, 10);
        if (chanceToSeal == 1) {
            this.setSealed(true);
        }
    }

    @Override
    public boolean shouldFall() {
        if (this.isSealed()) {
            return false;
        }

        int chance = RandomUtils.randomBetween(1, 100);
        if (height < 3) {
            return false;
        }

        if (height < 5) {
            return chance <= 5;
        }

        if (height < 6) {
            return chance <= 25;
        }

        if (height < 7) {
            return chance <= 50;
        }

        return true;
    }

    @Override
    public int getDiameter() {
        return 5;
    }

    public boolean isSealed() {
        return isSealed;
    }

    public void setSealed(boolean sealed) {
        isSealed = sealed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
