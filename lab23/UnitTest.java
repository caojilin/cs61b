import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTest {

    @Test
    public void testEdge() {
        Graph a = new Graph(5);
        a.addEdge(0,1);
        a.addEdge(0,2);

    }
}
