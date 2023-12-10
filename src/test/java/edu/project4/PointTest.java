package edu.project4;

import edu.project4.components.Point;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {
    @Test
    void testThatGetTwoCoordsAndPutThenToThePoint(){
        //given: two random coords
        Random rand = new Random();
        double x = rand.nextDouble();
        double y = rand.nextDouble();

        //when: we put coords to the class Point
        Point p = new Point(x,y);

        //then: coords don't change & lay in exactly expected order
        assertEquals(p.x(), x);
        assertEquals(p.y(), y);
    }
}
