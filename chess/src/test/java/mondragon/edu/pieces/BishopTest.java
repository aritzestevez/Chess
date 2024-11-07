package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.App;
import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Bishop;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Queen;
import net.bytebuddy.matcher.MethodOverrideMatcher;

public class BishopTest {

    Position position;
    Piece[][] board;
    Method movePiece;

    @Before
    public void setup() throws Exception{
        position = new Position(0, 0);
        ChessBoard chessBoard = new ChessBoard();
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        board = (Piece[][]) boardField.get(chessBoard);
        movePiece = App.class.getDeclaredMethod("movePiece",Piece.class);
        movePiece.setAccessible(true);

    }
    
    @Test
    public void testBishopGetSet(){
        Bishop bishop = new Bishop(position, Color.BLACK);
        assertEquals(bishop.getPosition(), position);
        assertEquals(Color.BLACK,bishop.getColor());
        bishop.setColor(Color.WHITE);

    }
    @Test
    public void testBishopValidMovementFalse(){
        Bishop bishop = new Bishop(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        bishop.isValidMove(position,chessBoard.getChessboard());
        assertEquals(false, bishop.isValidMove(position, chessBoard.getChessboard()));
    }
    @Test
    public void testMoveToSamePosition() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE); // Create a Bishop piece at (2, 0)
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;

        // Try to move the piece to the same position (2, 0)
        boolean result = piece.isValidMove(new Position(2, 0), board);

        // Assert that the move is invalid (should return false)
        assertFalse(result);
    }

    @Test
    public void testValidDiagonalMove() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;

        // Move diagonally
        assertTrue(piece.isValidMove(new Position(0, 2), board));
    }

    @Test
    public void testBlockedPathDiagonal() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(1, 1), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;
        board[1][1] = blockingPiece; // Blocking piece in the diagonal path

        assertFalse(piece.isValidMove(new Position(0, 2), board));
    }

    @Test
    public void testCaptureOpponentPiece() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(0, 2), Color.BLACK);
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;
        board[0][2] = opponentPiece; // Opponent's piece at destination

        assertTrue(piece.isValidMove(new Position(0, 2), board));
    }

    @Test
    public void testCaptureSameColorPiece() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(0, 2), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;
        board[0][2] = opponentPiece; // Opponent's piece at destination

        assertFalse(piece.isValidMove(new Position(0, 2), board));
    }

    @Test
    public void testInvalidNonDiagonalMove() {
        Piece piece = new Bishop(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Try an invalid straight move, which is not allowed
        assertFalse(piece.isValidMove(new Position(4, 5), board));
    }
}
