package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Queen;

public class QueenTest {
    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
    }

    @Test
    public void testQueenGetSet(){
        Queen queen = new Queen(position, Color.BLACK);
        assertEquals(queen.getPosition(), position);
        assertEquals(Color.BLACK,queen.getColor());
    }

    @Test
    public void testMoveToSamePosition() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE); // Create a Queen piece at (4, 4)
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Try to move the piece to the same position (4, 4)
        boolean result = piece.isValidMove(new Position(4, 4), board);

        // Assert that the move is invalid (should return false)
        assertFalse(result);
    }

    @Test
    public void testValidStraightLineMove() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Move vertically downwards
        assertTrue(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testValidDiagonalMove() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Move diagonally
        assertTrue(piece.isValidMove(new Position(6, 6), board));
    }

    @Test
    public void testBlockedPathStraight() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(5, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[5][4] = blockingPiece; // Blocking piece in the path

        assertFalse(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testBlockedPathDiagonal() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(5, 5), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[5][5] = blockingPiece; // Blocking piece in the diagonal path

        assertFalse(piece.isValidMove(new Position(6, 6), board));
    }

    @Test
    public void testCaptureOpponentPiece() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(6, 4), Color.BLACK);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[6][4] = opponentPiece; // Opponent's piece at destination

        assertTrue(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testCaptureSameColorPiece() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece sameColorPiece = new Pawn(new Position(6, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[6][4] = sameColorPiece; // Same color piece at destination

        assertFalse(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testInvalidNonStraightOrDiagonalMove() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Try an invalid "L" shaped move, which is not allowed
        assertFalse(piece.isValidMove(new Position(5, 6), board));
    }
}
