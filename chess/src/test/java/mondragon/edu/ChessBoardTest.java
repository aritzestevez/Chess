package mondragon.edu;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;

public class ChessBoardTest {
    ChessBoard board;

    @Before
    public void setup() {
        board = new ChessBoard();
    }

    @Test
    public void testInitializePieces() throws Exception {
        Method method = ChessBoard.class.getDeclaredMethod("initializePieces");
        method.setAccessible(true);
        method.invoke(board);
        assertNotNull(board);
    }

    @Test
    public void testVisualizePieces(){
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

}
