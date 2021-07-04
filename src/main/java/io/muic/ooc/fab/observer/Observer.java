package io.muic.ooc.fab.observer;

import io.muic.ooc.fab.Field;

public interface Observer {
    void update(int step, Field field);
}
