//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Animal extends Actor {
    public static final Random RANDOM = new Random();
    protected int age = 0;
    public static final int RABBIT_FOOD_VALUE = 9;
    public static final int FOX_FOOD_VALUE = 10;

    public Animal() {
    }

    public abstract int getMaxAge();

    protected void incrementAge() {
        ++this.age;
        if (this.age > this.getMaxAge()) {
            this.setDead();
        }

    }

    protected void setDead() {
        this.setAlive(false);
        if (this.location != null) {
            this.field.clear(this.location);
            this.location = null;
            this.field = null;
        }

    }

    protected int breed() {
        int births = 0;
        if (this.canBreed() && RANDOM.nextDouble() <= this.getBreedingProbability()) {
            births = RANDOM.nextInt(this.getMaxLitterSize()) + 1;
        }

        return births;
    }

    public void act(List<Actor> newAnimals) {
        this.incrementAge();
        if (this.isAlive()) {
            this.giveBirth(newAnimals);
            Location newLocation = this.moveToNewLocation();
            if (newLocation != null) {
                this.setLocation(newLocation);
            } else {
                this.setDead();
            }
        }

    }

    protected abstract double getBreedingProbability();

    protected abstract int getMaxLitterSize();

    private boolean canBreed() {
        return this.age >= this.getBreedingAge();
    }

    protected abstract int getBreedingAge();

    protected Actor createYoung(boolean randomAge, Field field, Location location) {
        return ActorFactory.createActor(this.getClass(), field, location);
    }

    protected void giveBirth(List newRabbits) {
        List<Location> free = this.field.getFreeAdjacentLocations(this.location);
        int births = this.breed();

        for(int b = 0; b < births && free.size() > 0; ++b) {
            Location loc = (Location)free.remove(0);
            Actor young = this.createYoung(false, this.field, loc);
            newRabbits.add(young);
        }

    }

    public void initialize(boolean randomAge, Field field, Location location) {
        this.field = field;
        this.setLocation(location);
        this.setAlive(true);
        if (randomAge) {
            this.age = RANDOM.nextInt(this.getMaxAge());
        }

    }
}
