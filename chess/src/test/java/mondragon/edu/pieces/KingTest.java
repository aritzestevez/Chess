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
    public void setup() throws Exception {
        position = new Position(0, 0);
        piece = new King(new Position(7, 4), Color.WHITE);
        board = new Piece[8][8];
        board[4][4] = piece;
    }
    
    @Test
    public void testKingGetSet(){
        King king = new King(position, Color.BLACK);
        assertEquals(king.getPosition(), position);
        assertEquals(Color.BLACK,king.getColor());
    }

    @Test
    public void testKingValidMovementFalse(){
        King king = new King(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, king.isValidMove(position, chessBoard.getChessboard()));
    }

    @Test
    public void testMoveToSamePosition() {
        boolean result = piece.isValidMove(new Position(7, 4), board);
        assertFalse(result);
    }

    @Test
    public void testValidStraightLineMove() {
        assertTrue(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testValidDiagonalMove() {
        assertTrue(piece.isValidMove(new Position(6, 5), board));
    }

    @Test
    public void testBlockedPathStraight() {
        Piece blockingPiece = new Pawn(new Position(6, 4), Color.WHITE);
        board[6][4] = blockingPiece;
        assertFalse(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testBlockedPathDiagonal() {
        Piece blockingPiece = new Pawn(new Position(6, 5), Color.WHITE);
        board[6][5] = blockingPiece;
        assertFalse(piece.isValidMove(new Position(6, 5), board));
    }

    @Test
    public void testCaptureOpponentPiece() {
        Piece opponentPiece = new Pawn(new Position(6, 4), Color.BLACK);
        board[6][4] = opponentPiece;
        assertTrue(piece.isValidMove(new Position(6, 4), board));
    }

    @Test
    public void testCaptureSameColorPiece() {
        Piece sameColorPiece = new Pawn(new Position(6, 4), Color.WHITE);
        board[6][4] = sameColorPiece;
        assertFalse(piece.isValidMove(new Position(6, 4), board));
    }
}
