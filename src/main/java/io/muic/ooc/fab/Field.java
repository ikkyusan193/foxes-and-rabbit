//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Field {
    private static final Random RANDOM = new Random();
    private int depth;
    private int width;
    private Object[][] field;

    public Field(int depth, int width) {
        this.depth = depth;
        this.width = width;
        this.field = new Object[depth][width];
    }

    public void clear() {
        for(int row = 0; row < this.depth; ++row) {
            for(int col = 0; col < this.width; ++col) {
                this.field[row][col] = null;
            }
        }

    }

    public void clear(Location location) {
        this.field[location.getRow()][location.getCol()] = null;
    }

    public void place(Object animal, int row, int col) {
        this.place(animal, new Location(row, col));
    }

    public void place(Object animal, Location location) {
        this.field[location.getRow()][location.getCol()] = animal;
    }

    public Object getObjectAt(Location location) {
        return this.getObjectAt(location.getRow(), location.getCol());
    }

    public Object getObjectAt(int row, int col) {
        return this.field[row][col];
    }

    public Location randomAdjacentLocation(Location location) {
        List<Location> adjacent = this.adjacentLocations(location);
        return (Location)adjacent.get(0);
    }

    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new LinkedList();
        List<Location> adjacent = this.adjacentLocations(location);
        Iterator var4 = adjacent.iterator();

        while(var4.hasNext()) {
            Location next = (Location)var4.next();
            if (this.getObjectAt(next) == null) {
                free.add(next);
            }
        }

        return free;
    }

    public Location freeAdjacentLocation(Location location) {
        List<Location> free = this.getFreeAdjacentLocations(location);
        return free.size() > 0 ? (Location)free.get(0) : null;
    }

    public List<Location> adjacentLocations(Location location) {
        assert location != null : "Null location passed to adjacentLocations";

        List<Location> locations = new LinkedList();
        if (location != null) {
            int row = location.getRow();
            int col = location.getCol();

            for(int roffset = -1; roffset <= 1; ++roffset) {
                int nextRow = row + roffset;
                if (nextRow >= 0 && nextRow < this.depth) {
                    for(int coffset = -1; coffset <= 1; ++coffset) {
                        int nextCol = col + coffset;
                        if (nextCol >= 0 && nextCol < this.width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }

            Collections.shuffle(locations, RANDOM);
        }

        return locations;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getWidth() {
        return this.width;
    }
}
