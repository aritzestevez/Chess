package mondragon.edu;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AppTest extends EasyMockSupport{
    
    App app;
    ByteArrayOutputStream os;
    PrintStream ps;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        app = new App();
    }

    public String runProgram(String input) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(ps);
        app.main(null);
        return os.toString();
    }


    @Test(timeout = 3000)
    public void testMain(){
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        app.main(null);
        assertTrue(true);
    }
}
