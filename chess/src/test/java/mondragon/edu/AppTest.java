package mondragon.edu;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import mondragon.edu.App;
import mondragon.edu.objects.ChessBoard;
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

/*     @Test(timeout = 3000)
    public void testOption1() throws Throwable {
        expectedException.expect(UnsupportedOperationException.class);
        expectedException.expectMessage("Unimplemented method 'showChessboard'");
        replayAll();
        System.setIn(new ByteArrayInputStream("1\n0\n".getBytes()));
        verifyAll();
    } */
    @Test(timeout = 3000)
    public void testOption2() throws Exception {
        // String out = runProgram("2\n0\n");
        
    }
    @Test(timeout = 3000)
    public void testOption0() throws Exception {
        // String out = runProgram("0\n");
        
    }
}
