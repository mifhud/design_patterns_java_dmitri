package org.example.creational.singleton.singleton;

class BasicSingleton {

    private static final BasicSingleton INSTANCE = new BasicSingleton();
    private int value;

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
}

class Demo {

    public static void main(String[] args) {
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(123);
        System.out.println(singleton.getValue());
    }
}
