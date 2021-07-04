//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.List;

public abstract class Actor {
    private boolean alive = true;
    protected Location location;
    protected Field field;

    public Actor() {
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Location getLocation() {
        return this.location;
    }

    public void initialize(boolean randomAge, Field field, Location location) {
        this.field = field;
        this.setLocation(location);
        this.setAlive(true);
    }

    protected void setLocation(Location newLocation) {
        if (this.location != null) {
            this.field.clear(this.location);
        }

        this.location = newLocation;
        this.field.place(this, newLocation);
    }

    protected abstract Location moveToNewLocation();

    protected void act(List<Actor> newAnimals) {
        if (this.isAlive()) {
            Location newLocation = this.moveToNewLocation();
            if (newLocation != null) {
                this.setLocation(newLocation);
            }
        }

    }
}
