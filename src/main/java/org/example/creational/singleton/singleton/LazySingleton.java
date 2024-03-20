package org.example.creational.singleton.singleton;

class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing a lazy singleton");
    }

    /*
     * Ensures thread safety using explicit synchronization mechanisms such as the synchronized
     * keyword or double-checked locking.
     * */

    // synchronized keyword
    // better than double-checked locking in sonarlint
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    // double-checked locking
    /*public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }*/
}

class DemoLazySingleton {

    public static void main(String[] args) {
        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}
