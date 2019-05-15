import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {
    @Test
    public void testsizeof() {
        UnionFind t = new UnionFind(10);
        int expected = t.sizeOf(1);
        assertEquals(expected, 1);

    }
}
