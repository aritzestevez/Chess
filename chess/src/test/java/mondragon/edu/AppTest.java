package mondragon.edu;

import static org.junit.Assert.assertEquals;
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
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Pawn;

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
    public void testMenuOptInvalid(){
        String out = runProgram("-1\n0\n");
        assertThat(out, CoreMatchers.containsString("Invalid option"));

    }

    @Test(timeout = 3000)
    public void testMenuOpt0(){
        runProgram("0\n");
        assertTrue(true);
    }

    @Test(timeout = 3000)
    public void testMenuOpt1(){
        runProgram("1\n0\n");
        ChessBoard chessBoard = new ChessBoard(false);
        String newChessboard = String.join(System.lineSeparator(),
                "♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖ ",
                "♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ ",
                "                ",
                "                ",
                "                ",
                "                ",
                "♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ ",
                "♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ ");
        assertEquals(newChessboard, chessBoard.toString());
    }

    @Test(timeout = 3000)
    public void testMenuOpt2ValidOption(){
        String out = runProgram("2\n10\n2\n1\n0\n");

        ChessBoard chessBoard = new ChessBoard(true);
        Position position = new Position(6, 1);

        Pawn pawn = new Pawn(new Position(6, 1), Color.WHITE);
        pawn.movePiece(chessBoard, position);

        assertThat(out, CoreMatchers.containsString("New piece position: (2, 1)"));
        assertEquals(pawn.getPosition(), position);
    }

    @Test(timeout = 3000)
    public void testMenuOpt2InvalidOption(){
        String out = runProgram("2\n10\n2\n1\n0\n");

        ChessBoard chessBoard = new ChessBoard(true);
        Position position = new Position(2, 1);

        Pawn pawn = new Pawn(new Position(1, 1), Color.BLACK);
        pawn.movePiece(chessBoard, position);

        assertThat(out, CoreMatchers.containsString("New piece position: (2, 1)"));
        assertEquals(pawn.getPosition(), position);
    }

    @Test(timeout = 3000)
    public void testMenuOpt2InvalidMove(){
        String out = runProgram("2\n10\n1\n2\n0\n");
        assertThat(out, CoreMatchers.containsString("Invalid position"));
    }

    @Test(timeout = 3000)
    public void testMenuOpt2InvalidPiece0(){
        String out = runProgram("2\n0\n0\n");
        assertThat(out, CoreMatchers.containsString("Invalid selection. Please try again."));
    }

    @Test(timeout = 3000)
    public void testMenuOpt2InvalidPieceHighNumber(){
        String out = runProgram("2\n50\n0\n");
        assertThat(out, CoreMatchers.containsString("Invalid selection. Please try again."));
    }
}