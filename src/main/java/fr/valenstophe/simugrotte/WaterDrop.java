package fr.valenstophe.simugrotte;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.valenstophe.simugrotte.geometry.Position2D;
import fr.valenstophe.simugrotte.util.RandomUtils;

public class WaterDrop extends Entity implements Growable, Fallable {

    private int weight;

    public WaterDrop(Position2D position) {
        super(position);
        this.weight = 0;
    }

    @Override
    public void grow() {
        this.weight += RandomUtils.randomBetween(0, 2);
    }

    @Override
    public boolean shouldFall() {
        int chance = RandomUtils.randomBetween(1, 100);
        if (weight < 3) {
            return false;
        }

        if (weight < 5) {
            return chance <= 5;
        }

        if (weight < 6) {
            return chance <= 25;
        }

        if (weight < 7) {
            return chance <= 35;
        }

        if (weight < 8) {
            return chance <= 50;
        }

        return true;
    }

    @Override
    public void tick(int currentTick) {
    }

    public int getDiameter() {
        return 5;
    }
}
