//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class FieldPopulator {
    private static final Random RANDOM = new Random();
    private Map<ActorType, Double> probabilityMap = new HashMap<ActorType, Double>() {{
        {
            ActorType[] animalTypes = ActorType.values();

            for(int i = 0; i < animalTypes.length; ++i) {
                this.put(animalTypes[i], animalTypes[i].getBreedingProbability());
            }

        }
    }};

    public void populate(Field field, List<Actor> animals) {
        field.clear();

        for(int row = 0; row < field.getDepth(); ++row) {
            for(int col = 0; col < field.getWidth(); ++col) {
                Iterator var5 = this.probabilityMap.entrySet().iterator();

                while(var5.hasNext()) {
                    Entry<ActorType, Double> entry = (Entry)var5.next();
                    if (RANDOM.nextDouble() <= (Double)entry.getValue()) {
                        Location location = new Location(row, col);
                        Actor animal = ActorFactory.createActor((ActorType)entry.getKey(), field, location);
                        animals.add(animal);
                        break;
                    }
                }
            }
        }

    }
}
