//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.Iterator;
import java.util.List;

public class Hunter extends Actor {
    public Hunter() {
    }

    protected Location moveToNewLocation() {
        Location newLocation = this.findFood();
        if (newLocation == null) {
            newLocation = this.field.freeAdjacentLocation(this.getLocation());
        }

        return newLocation;
    }

    public void act(List<Actor> newAnimals) {
        super.act(newAnimals);
    }

    private Location findFood() {
        List<Location> adjacent = this.field.adjacentLocations(this.location);
        Iterator it = adjacent.iterator();

        while(it.hasNext()) {
            Location where = (Location)it.next();
            Object animal = this.field.getObjectAt(where);
            if (animal instanceof Animal) {
                Animal animals = (Animal)animal;
                if (animals.isAlive()) {
                    animals.setDead();
                    return where;
                }
            }
        }

        return null;
    }
}
