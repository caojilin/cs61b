import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanSetTest {

    @Test
    public void testConstructor() {
        BooleanSet aSet = new BooleanSet(100);
        SimpleSet anotherSet = aSet;
        SimpleCollection anotherCollection = aSet;
    }

    @Test
    public void testBasics() {
        BooleanSet aSet = new BooleanSet(100);
        assertEquals(0, aSet.size());
        for (int i = 0; i < 100; i += 2) {
            aSet.add(i);
            assertTrue(aSet.contains(i));
        }
        assertEquals(50, aSet.size());

        for (int i = 0; i < 100; i += 2) {
            aSet.remove(i);
            assertFalse(aSet.contains(i));
        }
        assertTrue(aSet.isEmpty());
        assertEquals(0, aSet.size());
    }
    /*@Test
    public void testArray(){
        BooleanSet b = new BooleanSet(10);
        b.add(3);
        b.add(7);
        b.add(3);
        System.out.println(b.size());
        int[] a = b.toIntArray();
        assertEquals(a, new int[]{3,7});
    }*/
}
