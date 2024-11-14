package mondragon.edu;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.Position;

public class PositionTest {

    Position position;

    @Before
    public void setup() {
        position = new Position(0, 0);
    }

    @Test(timeout = 3000)
    public void testGet() {
        int x = position.getX();
        int y = position.getY();
        assertEquals(0,x);
        assertEquals(0,y);
    }

    @Test(timeout = 3000)
    public void testSet(){
        position.setX(1);
        position.setY(1);
        assertEquals(1,position.getX());
        assertEquals(1,position.getY());

    }
}
