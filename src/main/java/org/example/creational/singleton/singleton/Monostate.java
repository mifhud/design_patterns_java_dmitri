package org.example.creational.singleton.singleton;

/*
 * It's crucial to carefully consider the consequences of shared state. Shared mutable state
 * can bring about complexity and potential concurrency problems in multi-threaded environments.
 * */
class ChiefExecutiveOfficer {

    private static String name;
    private static int age;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        ChiefExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return "ChiefExecutiveOfficer{" +
                "name='" + name + "'" +
                ", age=" + age + "}";
    }
}

class Monostate {

    public static void main(String[] args) {
        ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
        ceo.setName("Adam Smith");
        ceo.setAge(55);

        ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
        System.out.println(ceo2);
    }
}
