package org.example.creational.singleton.singleton;

class InnerStaticSingleton {

    private InnerStaticSingleton() {
    }

    /*
     * Inner static singletons inherently provide thread safety without the need for explicit
     * synchronization mechanisms such as the synchronized keyword or double-checked locking.
     * */
    private static class Impl {

        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}

class DemoInnerStaticSingleton {

    public static void main(String[] args) {
        InnerStaticSingleton singleton1 = InnerStaticSingleton.getInstance();
        InnerStaticSingleton singleton2 = InnerStaticSingleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}