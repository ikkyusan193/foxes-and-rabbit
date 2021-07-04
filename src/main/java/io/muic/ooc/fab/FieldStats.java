//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Iterator;

public class FieldStats {
    private HashMap<Class, Counter> counters = new HashMap();
    private boolean countsValid = true;

    public FieldStats() {
    }

    public String getPopulationDetails(Field field) {
        StringBuffer buffer = new StringBuffer();
        if (!this.countsValid) {
            this.generateCounts(field);
        }

        Iterator var3 = this.counters.keySet().iterator();

        while(var3.hasNext()) {
            Class key = (Class)var3.next();
            Counter info = (Counter)this.counters.get(key);
            buffer.append(info.getName());
            buffer.append(": ");
            buffer.append(info.getCount());
            buffer.append(' ');
        }

        return buffer.toString();
    }

    public void reset() {
        this.countsValid = false;
        Iterator var1 = this.counters.keySet().iterator();

        while(var1.hasNext()) {
            Class key = (Class)var1.next();
            Counter count = (Counter)this.counters.get(key);
            count.reset();
        }

    }

    public void incrementCount(Class animalClass) {
        Counter count = (Counter)this.counters.get(animalClass);
        if (count == null) {
            count = new Counter(animalClass.getName());
            this.counters.put(animalClass, count);
        }

        count.increment();
    }

    public void countFinished() {
        this.countsValid = true;
    }

    public boolean isViable(Field field) {
        int nonZero = 0;
        if (!this.countsValid) {
            this.generateCounts(field);
        }

        Iterator var3 = this.counters.keySet().iterator();

        while(var3.hasNext()) {
            Class key = (Class)var3.next();
            Counter info = (Counter)this.counters.get(key);
            if (info.getCount() > 0) {
                ++nonZero;
            }
        }

        return nonZero > 1;
    }

    private void generateCounts(Field field) {
        this.reset();

        for(int row = 0; row < field.getDepth(); ++row) {
            for(int col = 0; col < field.getWidth(); ++col) {
                Object animal = field.getObjectAt(row, col);
                if (animal != null) {
                    this.incrementCount(animal.getClass());
                }
            }
        }

        this.countsValid = true;
    }
}
