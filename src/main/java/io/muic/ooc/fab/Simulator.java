//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.muic.ooc.fab;


import io.muic.ooc.fab.view.SimulatorView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import io.muic.ooc.fab.observer.SimulatorViewerStatusUpdaterObserver;
import io.muic.ooc.fab.observer.Observable;


public class Simulator extends Observable{
    private static final int DEFAULT_WIDTH = 120;
    private static final int DEFAULT_DEPTH = 80;
    private List<Actor> actors;
    private Field field;
    private int step;
    private SimulatorView view;

    public Simulator() {
        this(80, 120);
    }

    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be >= zero.");
            System.out.println("Using default values.");
            depth = 80;
            width = 120;
        }

        this.actors = new ArrayList();
        this.field = new Field(depth, width);
        this.view = new SimulatorView(depth, width);
        ActorType[] actorTypes = ActorType.values();

        for(int i = 0; i < actorTypes.length; ++i) {
            this.view.setColor(actorTypes[i].getAnimalClass(), actorTypes[i].getColor());
        }
        SimulatorViewerStatusUpdaterObserver simulatorViewerStatusUpdaterObserver = new SimulatorViewerStatusUpdaterObserver(view);
        addObserver(simulatorViewerStatusUpdaterObserver);
        this.reset();
    }

    public void runLongSimulation() {
        this.simulate(4000);
    }

    public void simulate(int numSteps) {
        for(int step = 1; step <= numSteps && this.view.isViable(this.field); ++step) {
            this.simulateOneStep();
        }

    }

    public void simulateOneStep() {
        ++this.step;
        List<Actor> newAnimals = new ArrayList();
        Iterator it = this.actors.iterator();

        while(it.hasNext()) {
            Actor animal = (Actor)it.next();
            animal.act(newAnimals);
            if (!animal.isAlive()) {
                it.remove();
            }
        }

        this.actors.addAll(newAnimals);
        notifyAllObservers(this.step, this.field);
//        this.view.showStatus(this.step, this.field);
    }


    public void reset() {
        this.step = 0;
        this.actors.clear();
        (new FieldPopulator()).populate(this.field, this.actors);
        notifyAllObservers(this.step, this.field);
//        this.view.showStatus(this.step, this.field);
    }

    private void delay(int millisec) {
        try {
            Thread.sleep((long)millisec);
        } catch (InterruptedException var3) {
        }

    }
}
