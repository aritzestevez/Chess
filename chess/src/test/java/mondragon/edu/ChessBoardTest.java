package mondragon.edu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

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

public class ChessBoardTest {
    ChessBoard board;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        board = new ChessBoard(true);
    }

    @Test(timeout = 3000)
    public void testInitializePieces() throws Exception {
        Method method = ChessBoard.class.getDeclaredMethod("initializePieces");
        method.setAccessible(true);
        method.invoke(board);
        assertNotNull(board);
    }

    @Test(timeout = 3000)
    public void testIsInCheck() {
        board.setPiece(7, 4, new King(new Position(7, 4), Color.WHITE));
        board.setPiece(5, 4, new Rook(new Position(5, 4), Color.BLACK));
    
        assertTrue(board.isInCheck(Color.WHITE));
        assertFalse(board.isCheckmate(Color.WHITE));
    }
    
    @Test(timeout = 3000)
    public void testIsCheckmate() {
        // Initialize pieces
        Piece pieceking = new King(new Position(6, 0), Color.WHITE);
        Piece piecepawn1 = new Pawn(new Position(7, 1), Color.WHITE);
        Piece piecepawn2 = new Pawn(new Position(6, 1), Color.WHITE);
        Piece piecepawn3 = new Pawn(new Position(5, 1), Color.WHITE);
        Piece piecepawn4 = new Pawn(new Position(7, 0), Color.WHITE);
        Piece piecerook = new Rook(new Position(5, 0), Color.BLACK);
    
        // Initialize the board with no pieces
        ChessBoard chessBoard = new ChessBoard(true);
    
        // Set up the pieces on the board for this test
        chessBoard.setPiece(6, 0, pieceking);
        chessBoard.setPiece(7, 1, piecepawn1);
        chessBoard.setPiece(6, 1, piecepawn2);
        chessBoard.setPiece(5, 1, piecepawn3);
        chessBoard.setPiece(7, 0, piecepawn4);
        chessBoard.setPiece(0, 0, piecerook);
    
        // Assert that White is in checkmate
        assertTrue(chessBoard.isCheckmate(Color.WHITE));
    }
    
    @Test(timeout = 3000)
    public void testKingExists() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("King not found, which should never happen.");

        Piece piecepawn1 = new Pawn(new Position(7, 1), Color.WHITE);
        Piece piecepawn2 = new Pawn(new Position(6, 1), Color.WHITE);
        Piece piecepawn3 = new Pawn(new Position(5, 1), Color.WHITE);
        Piece piecepawn4 = new Pawn(new Position(7, 0), Color.WHITE);
        Piece piecerook = new Rook(new Position(5, 0), Color.BLACK);
    
        ChessBoard chessBoard = new ChessBoard(true);
    
        chessBoard.setPiece(7, 1, piecepawn1);
        chessBoard.setPiece(6, 1, piecepawn2);
        chessBoard.setPiece(5, 1, piecepawn3);
        chessBoard.setPiece(7, 0, piecepawn4);
        chessBoard.setPiece(0, 0, piecerook);
    
        chessBoard.findKingPosition(Color.WHITE);
    }

    @Test(timeout = 3000)
    public void testIsPositionOnBoard() {
        ChessBoard chessBoard = new ChessBoard(true);

        assertTrue(chessBoard.isPositionOnBoard(new Position(0, 0)));
        assertTrue(chessBoard.isPositionOnBoard(new Position(7, 7)));
        assertTrue(chessBoard.isPositionOnBoard(new Position(3, 3)));
        assertTrue(chessBoard.isPositionOnBoard(new Position(0, 7)));
        assertTrue(chessBoard.isPositionOnBoard(new Position(7, 0)));

        assertFalse(chessBoard.isPositionOnBoard(new Position(-1, 0)));
        assertFalse(chessBoard.isPositionOnBoard(new Position(0, -1)));
        assertFalse(chessBoard.isPositionOnBoard(new Position(8, 0)));
        assertFalse(chessBoard.isPositionOnBoard(new Position(0, 8)));
        assertFalse(chessBoard.isPositionOnBoard(new Position(8, 8)));
    }
}
