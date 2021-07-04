//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ActorFactory {
    private static Map<ActorType, Class> actorClassMap = new HashMap<ActorType, Class>() {
        {
            ActorType[] actorTypes = ActorType.values();

            for(int i = 0; i < actorTypes.length; ++i) {
                this.put(actorTypes[i], actorTypes[i].getAnimalClass());
            }

        }
    };

    public ActorFactory() {
    }

    public static Actor createActor(ActorType actorType, Field field, Location location) {
        Class actorClass = (Class)actorClassMap.get(actorType);
        return createActor(actorClass, field, location);
    }

    public static Actor createActor(Class actorClass, Field field, Location location) {
        if (actorClass != null) {
            try {
                Actor actor = (Actor)actorClass.getDeclaredConstructor().newInstance();
                actor.initialize(true, field, location);
                return actor;
            } catch (InstantiationException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            } catch (NoSuchMethodException var6) {
                var6.printStackTrace();
            } catch (InvocationTargetException var7) {
                var7.printStackTrace();
            }
        }

        throw new IllegalArgumentException("Unknown animalType");
    }
}
