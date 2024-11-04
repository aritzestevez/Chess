package mondragon.edu;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
public class MainTest {
    
    App app;

    @Before
    public void setup(){
        app = new App();
    }

    @Test
    public void testMain(){
        app.main(null);
        assertTrue(true);
    }
}
