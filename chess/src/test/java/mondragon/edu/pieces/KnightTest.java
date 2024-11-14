package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Knight;
import mondragon.edu.objects.pieces.Pawn;

public class KnightTest {

    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
    }

    @Test(timeout = 3000)
    public void testKnightGetSet(){
        Knight knight = new Knight(position, Color.BLACK);
        assertEquals(knight.getPosition(), position);
        assertEquals(Color.BLACK,knight.getColor());
    }

    @Test(timeout = 3000)
    public void testKnightValidMovementFalse(){
        Knight knight = new Knight(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard(true);
        assertEquals(false, knight.isValidMove(position, chessBoard.getChessboard()));
    }

    @Test(timeout = 3000)
    public void testMoveToSamePosition() {
        Piece piece = new Knight(new Position(2, 0), Color.WHITE); // Create a Knight piece at (2, 0)
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;

        // Try to move the piece to the same position (2, 0)
        boolean result = piece.isValidMove(new Position(2, 0), board);

        // Assert that the move is invalid (should return false)
        assertFalse(result);
    }

    @Test(timeout = 3000)
    public void testValidLMove() {
        Piece piece = new Knight(new Position(2, 0), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[2][0] = piece;

        // Move in L
        assertTrue(piece.isValidMove(new Position(1, 2), board));
    }

    @Test(timeout = 3000)
    public void testMoveWithPieceInPath() {
        Piece piece = new Knight(new Position(1, 0), Color.WHITE);
        Piece blockingPiece = new Pawn(new Position(1, 1), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[1][0] = piece;
        board[1][1] = blockingPiece; // Blocking piece in the path

        assertTrue(piece.isValidMove(new Position(0, 2), board));
    }

    @Test(timeout = 3000)
    public void testCaptureOpponentPiece() {
        Piece piece = new Knight(new Position(1, 0), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(0, 2), Color.BLACK);
        Piece[][] board = new Piece[8][8];
        board[1][0] = piece;
        board[0][2] = opponentPiece; // Opponent's piece at destination

        assertTrue(piece.isValidMove(new Position(0, 2), board));
    }

    @Test(timeout = 3000)
    public void testCaptureSameColorPiece() {
        Piece piece = new Knight(new Position(1, 0), Color.WHITE);
        Piece opponentPiece = new Pawn(new Position(0, 2), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[1][0] = piece;
        board[0][2] = opponentPiece; // Opponent's piece at destination

        assertFalse(piece.isValidMove(new Position(0, 2), board));
    }

    @Test(timeout = 3000)
    public void testInvalidNonLMove() {
        Piece piece = new Knight(new Position(4, 4), Color.WHITE);
        Piece[][] board = new Piece[8][8];
        board[4][4] = piece;

        // Try an invalid "L" shaped move, which is not allowed
        assertFalse(piece.isValidMove(new Position(5, 5), board));
    }
    
}
