//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

public class Rabbit extends Animal {
    public Rabbit() {
    }

    protected Location moveToNewLocation() {
        return this.field.freeAdjacentLocation(this.getLocation());
    }

    protected double getBreedingProbability() {
        return 0.12D;
    }

    protected int getMaxLitterSize() {
        return 4;
    }

    public int getMaxAge() {
        return 40;
    }

    protected int getBreedingAge() {
        return 5;
    }
}
