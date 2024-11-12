package mondragon.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.easymock.EasyMockSupport;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
    }

    public String runProgram(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(ps);
        app.main(null);
        return os.toString();
    }

    @Test(timeout = 3000)
    public void testMenuOpt0(){
        runProgram("0\n");
        assertTrue(true);
    }

    @Test(timeout = 3000)
    public void testMenuOpt1(){
        runProgram("1\n0\n");
        ChessBoard chessBoard = new ChessBoard();
        String newChessboard = String.join(System.lineSeparator(),
                "♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ ",
                "♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ ",
                "                ",
                "                ",
                "                ",
                "                ",
                "♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ ",
                "♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖ ");
        assertEquals(newChessboard, chessBoard.toString());
    }

    @Test(timeout = 3000)
    public void testMenuOptInvalid(){
        String out = runProgram("-1\n0\n");
        assertThat(out, CoreMatchers.containsString("Invalid option"));

    }
}