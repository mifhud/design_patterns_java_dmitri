package org.example.creational.singleton.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable {

    private static final BasicSingleton INSTANCE = new BasicSingleton();
    private int value;


    /*
     * Converting a private constructor to a public constructor using reflection can pose a vulnerability.
     * */
    private BasicSingleton() {

    }

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /*
     * The readResolve function serves to fix the reference of a singleton to the same instance.
     * */
    protected Object readResolve() {
        return INSTANCE;
    }
}

class Demo {

    static void saveToFile(BasicSingleton singleton, String filename) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput)) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
         * vulnerabilities:
         * 1. reflection
         * 2. serialization
         * */
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(111);

        String filename = "singleton.bin";
        saveToFile(singleton, filename);

        singleton.setValue(222);

        BasicSingleton singleton2 = readFromFile(filename);

        System.out.println(singleton == singleton2);

        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());
    }
}
