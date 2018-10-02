package cs5800.algorithms.hashing;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HashingFunctionTest {
    @Test
    public void hashIndexTest() {
        final int SIZE = 100;
        try {
            int hashIndex = (int) HashingFunction.hashing("count", 100);
            assertTrue(String.format("Assertion failed. Hash Index: %d", hashIndex), hashIndex >= 0 && hashIndex <= 99);
        } catch (Exception e) {
        }
    }
}
