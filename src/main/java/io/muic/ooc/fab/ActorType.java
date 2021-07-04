//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.awt.Color;

public enum ActorType {
    RABBIT(0.08D, Rabbit.class, Color.ORANGE),
    FOX(0.05D, Fox.class, Color.BLUE),
    Tiger(0.002D, Tiger.class, Color.RED),
    Hunter(0.001D, Hunter.class, Color.MAGENTA);

    private double breedingProbability;
    private Class animalClass;
    private Color color;

    private ActorType(double breedingProbability, Class animalClass, Color color) {
        this.breedingProbability = breedingProbability;
        this.animalClass = animalClass;
        this.color = color;
    }

    public double getBreedingProbability() {
        return this.breedingProbability;
    }

    public Class getAnimalClass() {
        return this.animalClass;
    }

    public Color getColor() {
        return this.color;
    }
}
