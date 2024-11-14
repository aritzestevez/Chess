package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Rook;

public class RookTest {
    Position position;

    @Before
    public void setup() {
        position = new Position(0, 0);
    }

    @Test(timeout = 3000)
    public void testRookGetSet() {

        Rook rook = new Rook(position, Color.BLACK);
        assertEquals(rook.getPosition(), position);
        assertEquals(Color.BLACK, rook.getColor());
    }

    @Test(timeout = 3000)
    public void testPosition() {
        Rook rook = new Rook(position, null);
        rook.setPosition(new Position(1, 1));
        assertEquals(1, rook.getPosition().getX());
        assertEquals(1, rook.getPosition().getY());
    }

    @Test(timeout = 3000)
    public void testRookPieceInTheWay() {
        Position rookMove = new Position(0, 0);
        Position piece = new Position(0, 4);
        Position move = new Position(0, 6);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(rookMove, Color.WHITE);
        King pieceRand = new King(piece, Color.WHITE);
        chessBoard[0][0] = rook;
        chessBoard[0][4] = pieceRand;
        assertFalse(rook.isValidMove(move, chessBoard));
    }

    @Test(timeout = 3000)
    public void testRookPieceInTheWay2() {
        Position rookMove = new Position(0, 0);
        Position piece = new Position(4, 0);
        Position move = new Position(6, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(rookMove, Color.WHITE);
        King pieceRand = new King(piece, Color.WHITE);
        chessBoard[0][0] = rook;
        chessBoard[4][0] = pieceRand;
        assertFalse(rook.isValidMove(move, chessBoard));
    }

    @Test(timeout = 3000)
    public void testRookValidMoving() {
        Position rookMove1 = new Position(1, 0);
        Position init = new Position(0, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, null);
        chessBoard[0][0] = rook;
        assertTrue(rook.isValidMove(rookMove1, chessBoard));
    }
    @Test
    public void testRookValidMoveDown() {
        Position rookMove1 = new Position(2, 1);
        Position init = new Position(2,2);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, null);
        chessBoard[2][2] = rook;
        assertTrue(rook.isValidMove(rookMove1, chessBoard));
    }

    @Test(timeout = 3000)
    public void testRookValidEating() {
        Position eatRook2 = new Position(4, 0);
        Position init = new Position(0, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, Color.WHITE);
        Rook rook2 = new Rook(eatRook2, Color.BLACK);
        chessBoard[0][0] = rook;
        chessBoard[4][0] = rook2;
        assertTrue(rook.isValidMove(eatRook2, chessBoard));
    }
    @Test
    public void testRookValidEatingSameColor() {
        Position eatRook2 = new Position(4, 0);
        Position init = new Position(0, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, Color.WHITE);
        Rook rook2 = new Rook(eatRook2, Color.WHITE);
        chessBoard[0][0] = rook;
        chessBoard[4][0] = rook2;
        assertFalse(rook.isValidMove(eatRook2, chessBoard));
    }


    @Test(timeout = 3000)
    public void testRookInvalidDiagonal() {
        Position init = new Position(0, 0);
        Position diagonal = new Position(2, 2);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, Color.WHITE);
        chessBoard[0][0] = rook;
        assertFalse(rook.isValidMove(diagonal, chessBoard));
    }

}
