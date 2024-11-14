package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Bishop;
import mondragon.edu.objects.pieces.Pawn;

public class BishopTest {

    Position position;
    Piece[][] board;

    @Before
    public void setup() throws Exception{
        position = new Position(0, 0);
        ChessBoard chessBoard = new ChessBoard(true);
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        board = (Piece[][]) boardField.get(chessBoard);
    }
    
    @Test
    public void testBishopGetSet(){
        Bishop bishop = new Bishop(position, Color.BLACK);
        assertEquals(bishop.getPosition(), position);
        assertEquals(Color.BLACK,bishop.getColor());
        bishop.setColor(Color.WHITE);

    }
    
    @Test
    public void testMoveToSamePosition() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE); // Create a Bishop piece at (2, 0)
        Piece[][] boardSame = new Piece[8][8];
        boardSame[2][0] = piece;

        // Try to move the piece to the same position (2, 0)
        boolean result = piece.isValidMove(new Position(2, 0), boardSame);

        // Assert that the move is invalid (should return false)
        assertFalse(result);
    }

    @Test
    public void testValidDiagonalMoveLeft() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece[][] boardDiagonal = new Piece[8][8];
        boardDiagonal[2][0] = piece;

        // Move diagonally
        assertTrue(piece.isValidMove(new Position(0, 2), boardDiagonal));
    }
    @Test
    public void testValidDiagonalMoveRight() {
        Piece piece = new Bishop(new Position(0, 2), Color.WHITE);
        Piece[][] boardDiagonal = new Piece[8][8];
        boardDiagonal[0][2] = piece;

        // Move diagonally
        assertTrue(piece.isValidMove(new Position(2, 0), boardDiagonal));
    }
    @Test
    public void testBlockedPathDiagonal() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(1, 1), Color.WHITE);
        Piece[][] boardBlocked = new Piece[8][8];
        boardBlocked[2][0] = piece;
        boardBlocked[1][1] = blockingPiece; // Blocking piece in the diagonal path

        assertFalse(piece.isValidMove(new Position(0, 2), boardBlocked));
    }

    @Test
    public void testCaptureOpponentPiece() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(0, 2), Color.BLACK);
        Piece[][] boardOppo = new Piece[8][8];
        boardOppo[2][0] = piece;
        boardOppo[0][2] = opponentPiece; // Opponent's piece at destination

        assertTrue(piece.isValidMove(new Position(0, 2), boardOppo));
    }

    @Test
    public void testCaptureSameColorPiece() {
        Piece piece = new Bishop(new Position(2, 0), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(0, 2), Color.WHITE);
        Piece[][] boardSameColor = new Piece[8][8];
        boardSameColor[2][0] = piece;
        boardSameColor[0][2] = opponentPiece; // Opponent's piece at destination

        assertFalse(piece.isValidMove(new Position(0, 2), boardSameColor));
    }

    @Test
    public void testInvalidNonDiagonalMove() {
        Piece piece = new Bishop(new Position(4, 4), Color.WHITE);
        Piece[][] boardInvalid = new Piece[8][8];
        boardInvalid[4][4] = piece;

        // Try an invalid straight move, which is not allowed
        assertFalse(piece.isValidMove(new Position(4, 5), boardInvalid));
    }
}
