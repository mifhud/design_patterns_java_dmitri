package org.example.creational.singleton.singleton;

import java.util.HashMap;

/*
 * Multiton allows for multiple instances of a class, each identified by a unique key.
 * Each instance is typically created on-demand based on the provided key.
 * */
enum Subsystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer {

    private static int instanceCount = 0;

    private Printer() {
        instanceCount++;
        System.out.println("A total of " + instanceCount + " instances created so far.");
    }

    private static HashMap<Subsystem, Printer> instances = new HashMap<>();

    public static Printer get(Subsystem ss) {
        if (instances.containsKey(ss)) {
            return instances.get(ss);
        }

        Printer instance = new Printer();
        instances.put(ss, instance);
        return instance;
    }
}

class Multiton {

    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        Printer aux2 = Printer.get(Subsystem.AUXILIARY);
    }
}

