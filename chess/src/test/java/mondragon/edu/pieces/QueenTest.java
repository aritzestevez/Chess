package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Queen;
import mondragon.edu.objects.pieces.Rook;

public class QueenTest {
    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
    }

    @Test(timeout = 3000)
    public void testQueenGetSet(){
        Queen queen = new Queen(position, Color.BLACK);
        assertEquals(queen.getPosition(), position);
        assertEquals(Color.BLACK,queen.getColor());
    }

    @Test(timeout = 3000)
    public void testMoveToSamePosition() {
        Position initialPosition = new Position(3, 3); // Assume this represents row 3, column 3
        Queen queen = new Queen(initialPosition, Color.WHITE);
        Piece[][] board = new Piece[8][8]; // Create an empty 8x8 chess board

        // Act
        boolean result = queen.isValidMove(initialPosition, board);

        // Assert
        assertFalse(result);
    }

    @Test(timeout = 3000)
    public void testValidStraightLineMove() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Move vertically downwards
        assertTrue(piece.isValidMove(new Position(6, 4), board));
    }

    @Test(timeout = 3000)
    public void testValidDiagonalMove() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Move diagonally
        assertTrue(piece.isValidMove(new Position(6, 6), board));
    }

    @Test(timeout = 3000)
    public void testBlockedPathStraight() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(5, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[5][4] = blockingPiece; // Blocking piece in the path

        assertFalse(piece.isValidMove(new Position(6, 4), board));
    }

    @Test(timeout = 3000)
    public void testBlockedPathDiagonal() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(5, 5), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[5][5] = blockingPiece; // Blocking piece in the diagonal path

        assertFalse(piece.isValidMove(new Position(6, 6), board));
    }

    @Test(timeout = 3000)
    public void testBlockedPathHorizontal() {
        // Arrange
        Position initialPosition = new Position(3, 3);
        Queen queen = new Queen(initialPosition, Color.WHITE);
        Piece[][] board = new Piece[8][8];

        // Place a blocking piece in the horizontal path
        board[3][5] = new Rook(new Position(3, 5), Color.BLACK); // Assume Rook class exists

        // Act
        boolean result = queen.isValidMove(new Position(3, 7), board); // Horizontal move to (3, 7)

        // Assert
        assertFalse(result);
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

    @Test(timeout = 3000)
    public void testCaptureSameColorPiece() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece sameColorPiece = new Pawn(new Position(6, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;
        board[6][4] = sameColorPiece; // Same color piece at destination

        assertFalse(piece.isValidMove(new Position(6, 4), board));
    }

    @Test(timeout = 3000)
    public void testInvalidNonStraightOrDiagonalMove() {
        Piece piece = new Queen(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Try an invalid "L" shaped move, which is not allowed
        assertFalse(piece.isValidMove(new Position(5, 6), board));
    }
}
