package cs5800.algorithms.table;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HashTableTest {
    @Test
    public void testInsertAndFind() throws IOException {
        HashMap<String, Integer> hashMap = getMap();
        final String NAME = "Sibendu";
        final Integer COUNT = 1;
        hashMap.insert(NAME, COUNT);
        assertTrue(hashMap.find(NAME).equals(COUNT));
    }

    @Test
    public void testInsertAndFindMultiple() throws IOException {
        HashMap<String, Integer> hashMap = getMap();
        final String WORD = "WORD";
        final Integer COUNT = 1;
        final String WORD2 = "WORD2";
        hashMap.insert(WORD, COUNT);
        hashMap.insert(WORD2, COUNT);
        assertTrue(hashMap.find(WORD).equals(COUNT));
        assertTrue(hashMap.find(WORD2).equals(COUNT));
    }

    @Test
    public void testDelete() throws IOException {
        HashMap<String, Integer> hashMap = getMap();
        final String WORD = "WORD";
        final Integer COUNT = 1;
        hashMap.insert(WORD, COUNT);
        hashMap.delete(WORD);
        assertEquals(hashMap.find(WORD), null);
    }

    /**
     * Inserting an node with a key already present
     * in hash should result in updation
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException {
        HashMap<String, Integer> hashMap = getMap();
        final String WORD = "WORD";
        final Integer COUNT = 1;
        hashMap.insert(WORD, COUNT);
        hashMap.insert(WORD, COUNT + 1);
        assertEquals(hashMap.find(WORD), new Integer(COUNT + 1));
    }

    @Test
    public void testIncrease() throws IOException {
        HashMap<String, Integer> hashMap = getMap();
        final String WORD = "WORD";
        final Integer COUNT = 1;
        hashMap.insert(WORD, COUNT);
        hashMap.increase(WORD);
        assertEquals(hashMap.find(WORD), new Integer(COUNT + 1));
    }

    @Test
    public void testListAllKeys() throws IOException {
        HashMap<String, Integer> hashMap = getMap();
        final String WORD = "WORD";
        final String WORD2 = "WORD2";
        final String WORD3 = "WORD3";
        final String WORD4 = "WORD4";
        final Integer COUNT = 1;

        hashMap.insert(WORD, COUNT);
        hashMap.insert(WORD2, COUNT);
        hashMap.insert(WORD3, COUNT);
        hashMap.insert(WORD4, COUNT);

        List<String> result = hashMap.listAllKeys();
        Collections.sort(result);
        assertEquals(Arrays.asList(WORD, WORD2, WORD3, WORD4), result);
    }

    private static HashMap<String, Integer> getMap() {
        final int MAX_SIZE = 100;
        return new HashMap<>(MAX_SIZE);
    }
}
