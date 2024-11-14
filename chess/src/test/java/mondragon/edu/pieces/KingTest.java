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
import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Pawn;

public class KingTest {

    Position position;
    Piece[][] board;
    Piece piece;

    @Before
    public void setup(){
        position = new Position(0, 0);
        piece = new King(new Position(6, 3), Color.WHITE);
        board = new Piece[8][8];
        board[6][3] = piece;
    }
    
    @Test(timeout = 3000)
    public void testKingGetSet(){
        King king = new King(position, Color.BLACK);
        assertEquals(king.getPosition(), position);
        assertEquals(Color.BLACK,king.getColor());
    }

    @Test(timeout = 3000)
    public void testKingValidMovementFalse(){
        King king = new King(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard(true);
        assertFalse(king.isValidMove(position, chessBoard.getChessboard()));
    }

    @Test(timeout = 3000)
    public void testMoveToSamePosition() {
        assertFalse(piece.isValidMove(new Position(6, 3), board));
    }

    @Test(timeout = 3000)
    public void testValidStraightLineMove() {
        assertTrue(piece.isValidMove(new Position(5, 3), board));
    }

    @Test(timeout = 3000)
    public void testValidDiagonalMoveTopLeft() {
        assertTrue(piece.isValidMove(new Position(5, 4), board));
    }

    @Test(timeout = 3000)
    public void testValidDiagonalMoveTopRight() {
        assertTrue(piece.isValidMove(new Position(7, 4), board));
    }
    @Test(timeout = 3000)
    public void testValidDiagonalMoveBottonLeft() {
        assertTrue(piece.isValidMove(new Position(5, 4), board));
    }

    @Test(timeout = 3000)
    public void testValidDiagonalMoveBottonRight() {
        assertTrue(piece.isValidMove(new Position(7, 2), board));
    }

    @Test(timeout = 3000)
    public void testNonDiagonalNonStraightMove() {
        assertFalse(piece.isValidMove(new Position(5, 5), board));
    }

    @Test(timeout = 3000)
    public void testBlockedPathStraight() {
        Piece blockingPiece = new Pawn(new Position(5, 3), Color.WHITE);
        board[5][3] = blockingPiece;
        assertFalse(piece.isValidMove(new Position(5, 3), board));
    }

    @Test(timeout = 3000)
    public void testBlockedPathDiagonal() {
        Piece blockingPiece = new Pawn(new Position(5, 4), Color.WHITE);
        board[5][4] = blockingPiece;
        assertFalse(piece.isValidMove(new Position(5, 4), board));
    }

    @Test(timeout = 3000)
    public void testCaptureOpponentPiece() {
        Piece opponentPiece = new Pawn(new Position(5, 3), Color.BLACK);
        board[5][3] = opponentPiece;
        assertTrue(piece.isValidMove(new Position(5, 3), board));
    }

    @Test(timeout = 3000)
    public void testCaptureSameColorPiece() {
        Piece sameColorPiece = new Pawn(new Position(5, 3), Color.WHITE);
        board[5][3] = sameColorPiece;
        assertFalse(piece.isValidMove(new Position(5, 3), board));
    }
}
