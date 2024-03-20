package org.example.creational.singleton.singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {

    /*
     * The static block allows for more robust exception handling during singleton initialization.
     * You can catch exceptions that might occur during the creation of the singleton instance and
     * handle them appropriately within the static block itself.
     * */
    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    /*
     * An instance is not a constant because it is not a final variable, which requires immediate
     * initialization upon creation.
     * */
    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (IOException e) {
            System.err.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}

class DemoStaticBlockSingleton {

    public static void main(String[] args) {
        StaticBlockSingleton singleton1 = StaticBlockSingleton.getInstance();
        StaticBlockSingleton singleton2 = StaticBlockSingleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}
