//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.Iterator;
import java.util.List;

public class Fox extends Animal {
    private int foodLevel;

    public Fox() {
    }

    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge, field, location);
        this.foodLevel = RANDOM.nextInt(9);
    }

    protected Location moveToNewLocation() {
        Location newLocation = this.findFood();
        if (newLocation == null) {
            newLocation = this.field.freeAdjacentLocation(this.getLocation());
        }

        return newLocation;
    }

    public void act(List<Actor> newAnimals) {
        this.incrementHunger();
        super.act(newAnimals);
    }

    private void incrementHunger() {
        --this.foodLevel;
        if (this.foodLevel <= 0) {
            this.setDead();
        }

    }

    private Location findFood() {
        List<Location> adjacent = this.field.adjacentLocations(this.location);
        Iterator it = adjacent.iterator();

        while(it.hasNext()) {
            Location where = (Location)it.next();
            Object animal = this.field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit)animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    this.foodLevel = 9;
                    return where;
                }
            }
        }

        return null;
    }

    public int getMaxAge() {
        return 150;
    }

    protected double getBreedingProbability() {
        return 0.08D;
    }

    protected int getMaxLitterSize() {
        return 2;
    }

    protected int getBreedingAge() {
        return 15;
    }
}
