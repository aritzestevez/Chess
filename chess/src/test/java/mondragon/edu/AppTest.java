package mondragon.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.easymock.EasyMockSupport;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Rook;

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
        String out = runProgram("2\n2\n5\n1\n0\n");

        ChessBoard chessBoard = new ChessBoard(true);
        Position position = new Position(2, 1);

        Pawn pawn = new Pawn(new Position(6, 1), Color.WHITE);
        pawn.movePiece(chessBoard, position);

        assertThat(out, CoreMatchers.containsString("New piece position: (5, 1)"));
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

    @Test(timeout = 3000)
    public void testGameOver() throws Exception {
        System.setOut(ps);

        Field isGameOverField = App.class.getDeclaredField("isGameOver");
        isGameOverField.setAccessible(true);
        isGameOverField.set(app, true);
    
        app.run();
        String out = os.toString();

        assertThat(out, CoreMatchers.containsString("Game is over. Restart to play again."));
    }

    @Test
    public void testCheckForCheckmateWhite() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        System.setOut(ps);

        Piece piecekingw = new King(new Position(6, 0), Color.WHITE);
        Piece piecekingb = new King(new Position(0, 4), Color.BLACK);
        Piece piecepawn1 = new Pawn(new Position(7, 1), Color.WHITE);
        Piece piecepawn2 = new Pawn(new Position(6, 1), Color.WHITE);
        Piece piecepawn3 = new Pawn(new Position(5, 1), Color.WHITE);
        Piece piecepawn4 = new Pawn(new Position(7, 0), Color.WHITE);
        Piece piecerook = new Rook(new Position(5, 0), Color.BLACK);
    
        // Initialize the board with no pieces
        ChessBoard chessBoard = new ChessBoard(true);
    
        // Set up the pieces on the board for this test
        chessBoard.setPiece(6, 0, piecekingw);
        chessBoard.setPiece(0, 4, piecekingb);
        chessBoard.setPiece(7, 1, piecepawn1);
        chessBoard.setPiece(6, 1, piecepawn2);
        chessBoard.setPiece(5, 1, piecepawn3);
        chessBoard.setPiece(7, 0, piecepawn4);
        chessBoard.setPiece(0, 0, piecerook);

        Field cb = App.class.getDeclaredField("chessBoard");
        cb.setAccessible(true);
        cb.set(app, chessBoard);
    
        app.checkForCheckmate();
        String out = os.toString();

        assertThat(out, CoreMatchers.containsString("White is in checkmate! Black wins!"));
    }

    @Test
    public void testCheckForCheckmateBlack() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        System.setOut(ps);

        Piece piecekingw = new King(new Position(6, 0), Color.BLACK);
        Piece piecekingb = new King(new Position(0, 4), Color.WHITE);
        Piece piecepawn1 = new Pawn(new Position(7, 1), Color.BLACK);
        Piece piecepawn2 = new Pawn(new Position(6, 1), Color.BLACK);
        Piece piecepawn3 = new Pawn(new Position(5, 1), Color.BLACK);
        Piece piecepawn4 = new Pawn(new Position(7, 0), Color.BLACK);
        Piece piecerook = new Rook(new Position(5, 0), Color.WHITE);
    
        // Initialize the board with no pieces
        ChessBoard chessBoard = new ChessBoard(true);
    
        // Set up the pieces on the board for this test
        chessBoard.setPiece(6, 0, piecekingw);
        chessBoard.setPiece(0, 4, piecekingb);
        chessBoard.setPiece(7, 1, piecepawn1);
        chessBoard.setPiece(6, 1, piecepawn2);
        chessBoard.setPiece(5, 1, piecepawn3);
        chessBoard.setPiece(7, 0, piecepawn4);
        chessBoard.setPiece(0, 0, piecerook);

        Field cb = App.class.getDeclaredField("chessBoard");
        cb.setAccessible(true);
        cb.set(app, chessBoard);
    
        app.checkForCheckmate();
        String out = os.toString();

        assertThat(out, CoreMatchers.containsString("Black is in checkmate! White wins!"));
    }
}