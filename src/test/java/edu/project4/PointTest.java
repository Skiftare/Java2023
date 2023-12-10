package edu.project4;

import edu.project4.components.Point;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {
    @Test
    void testThatGetTwoCoordsAndPutThenToThePoint(){
        Random rand = new Random();
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        Point p = new Point(x,y);
        assertEquals(p.x(), x);
        assertEquals(p.y(), y);
    }
}
