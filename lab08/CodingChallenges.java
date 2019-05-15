import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class CodingChallenges {

    /**
     * Return the missing number from an array of length N - 1 containing all
     * the values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        Set<Integer> set = new HashSet<>();
        for (int i : values) {
            set.add(i);
        }
        for (int i = 0; i < values.length; i += 1) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if and only if two integers in the array sum up to n.
     */
    public static boolean sumTo(int[] values, int n) {
        Set<Integer> set = new HashSet<>();
        for (int i : values) {
            set.add(n - i);
            if (set.contains(i)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testSumto() {
        int[] v = new int[]{5, 7, 3, 12, 3};
        boolean expected = sumTo(v, 6);
        assertTrue(expected);
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        char[] charArray1 = s1.toCharArray();
        for (char i : charArray1) {
            if (!map1.containsKey(i)) {
                map1.put(i, 1);
            } else {
                map1.put(i, map1.get(i) + 1);
            }
        }
        List<Character> keys = new ArrayList<>(map1.keySet());
        char[] charArray2 = s2.toCharArray();
        for (char i : charArray2) {
            if (map1.containsKey(i)) {
                map1.put(i, map1.get(i) - 1);
            }
        }
        if (keys.size() != charArray2.length) {
            return false;
        }
        for (char i : keys) {
            if (!(map1.get(i) == 0)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void ispermu() {
        boolean expected = isPermutation("catf", "actf");
        assertTrue(expected);
    }
}
