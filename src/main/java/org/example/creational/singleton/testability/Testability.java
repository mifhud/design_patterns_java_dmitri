package org.example.creational.singleton.testability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.google.common.collect.Iterables;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

class SingletonDatabase {

    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;

    public static int getCount() {
        return instanceCount;
    }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing database");

        try {
            File f = new File(Testability.class.getProtectionDomain().getCodeSource().getLocation()
                    .getPath());
            Path fullPath = Paths.get(f.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));
        } catch (Exception e) {
            // handle it!
            System.err.println(e);
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }
}

class SingletonRecordFinder {

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names) {
            result += SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}

@RunWith(JUnit4.class)
public class Testability {

    @Test
    public void isSingletonTest() {
        SingletonDatabase db = SingletonDatabase.getInstance();
        SingletonDatabase db2 = SingletonDatabase.getInstance();
        assertSame(db, db2);
        assertEquals(1, SingletonDatabase.getCount());
    }

    @Test
    public void singletonTotalPopulationTest() {
        // testing on a live database
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(17500000 + 17400000, tp);
    }
}
