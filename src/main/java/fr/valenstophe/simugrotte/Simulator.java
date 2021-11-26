package fr.valenstophe.simugrotte;

import fr.valenstophe.simugrotte.geometry.Area;
import fr.valenstophe.simugrotte.geometry.Position2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Simulator {
    private int currentTick = 0;
    private Area area;

    private final List<Entity> entities = new ArrayList<>();

    public Simulator(Area area) {
        this.area = area;
    }

    public void tick() {
        spawnWaterDrop();

        // copy the entities array to avoid concurrency exceptions
        List<Entity> entitiesCopy = new ArrayList<>(entities);

        for (Entity currentEntity : entitiesCopy) {
            // ignore entities that have been removed
            if (currentEntity.isRemoved()) {
                continue;
            }

            currentEntity.tick(currentTick);

            if (currentEntity instanceof Fallable && ((Fallable) currentEntity).shouldFall()) {
                if (currentEntity instanceof WaterDrop) {
                    WaterDrop waterDrop = (WaterDrop) currentEntity;

                    Optional<Entity> sodaStrawOptional = entities.stream()
                        .filter(entity -> entity.getPosition().equals(waterDrop.getPosition()))
                        .findFirst();

                    Stalactite stalactite = sodaStrawOptional.stream()
                        .filter(entity -> entity instanceof Stalactite)
                        .map(entity -> (Stalactite) entity)
                        .findFirst()
                        .orElseGet(spawnStalactiteFromWaterDrop(waterDrop));

                    stalactite.grow();
                }
                currentEntity.setRemoved(true);
                entities.remove(currentEntity);
            }
        }
        currentTick++;
    }

    private Supplier<Stalactite> spawnStalactiteFromWaterDrop(WaterDrop waterDrop) {
        return () -> {
            Stalactite newStalactite = new Stalactite(waterDrop.getPosition());
            entities.add(newStalactite);
            return newStalactite;
        };
    }

    private void spawnWaterDrop() {
        Position2D position = getArea().randomPosition();
        WaterDrop waterDrop = new WaterDrop(position);

        Optional<Entity> nearestWaterDropOptional = entities.stream()
            .filter(getNearestEntityPredicate(position))
            .filter(entity -> entity instanceof WaterDrop)
            .findFirst();
        if (nearestWaterDropOptional.isPresent()) {
            Growable nearestWaterDrop = (Growable) nearestWaterDropOptional.get();
            nearestWaterDrop.grow();
            return;
        }

        Optional<Entity> nearestEntity = entities.stream()
            .filter(getNearestEntityPredicate(position))
            .findFirst();
        if (nearestEntity.isPresent()) {
            Entity entity = nearestEntity.get();
            waterDrop.setPosition(entity.getPosition().clone());
        }
        entities.add(waterDrop);
        waterDrop.grow();
    }

    private Predicate<Entity> getNearestEntityPredicate(Position2D position) {
        return entity -> position.isInRadiusOf(entity.getPosition(), entity.getDiameter());
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
