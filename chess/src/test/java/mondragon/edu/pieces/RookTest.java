package mondragon.edu.pieces;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.*;

public class RookTest {
    Position position;

    @Before
    public void setup() {
        position = new Position(0, 0);
    }

    @Test
    public void testRookGetSet() {

        Rook rook = new Rook(position, Color.BLACK);
        assertEquals(rook.getPosition(), position);
        assertEquals(Color.BLACK, rook.getColor());
    }

    @Test
    public void testRookValidMovementFalse() {
        Rook rook = new Rook(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, rook.isValidMove(position, chessBoard.getChessboard()));
    }

    @Test
    public void testPosition() {
        Rook rook = new Rook(position, null);
        rook.setPosition(new Position(1, 1));
        assertEquals(1, rook.getPosition().getX());
        assertEquals(1, rook.getPosition().getY());
    }

    @Test
    public void testRookPieceInTheWay() throws Exception {
        Position rookMove = new Position(0, 0);
        Position piece = new Position(0, 4);
        Position move = new Position(0, 6);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(rookMove, Color.WHITE);
        King pieceRand = new King(piece, Color.WHITE);
        chessBoard[0][0] = rook;
        chessBoard[0][4] = pieceRand;
        assertEquals(false, rook.isValidMove(move, chessBoard));
    }

    @Test
    public void testRookPieceInTheWay2() throws Exception {
        Position rookMove = new Position(0, 0);
        Position piece = new Position(4, 0);
        Position move = new Position(6, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(rookMove, Color.WHITE);
        King pieceRand = new King(piece, Color.WHITE);
        chessBoard[0][0] = rook;
        chessBoard[4][0] = pieceRand;
        assertEquals(false, rook.isValidMove(move, chessBoard));
    }

    @Test
    public void testRookValidMoving() throws Exception {
        Position rookMove1 = new Position(1, 0);
        Position init = new Position(0, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, null);
        chessBoard[0][0] = rook;
        assertEquals(true, rook.isValidMove(rookMove1, chessBoard));
    }
    

    @Test
    public void testRookValidEating() throws Exception {
        Position eatRook2 = new Position(4, 0);
        Position init = new Position(0, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, Color.WHITE);
        Rook rook2 = new Rook(eatRook2, Color.BLACK);
        chessBoard[0][0] = rook;
        chessBoard[4][0] = rook2;
        assertEquals(true, rook.isValidMove(eatRook2, chessBoard));
    }

    @Test
    public void testRookInvalidDiagonal() {
        Position init = new Position(0, 0);
        Position diagonal = new Position(2, 2);
        Piece[][] chessBoard = new Piece[8][8];
        Rook rook = new Rook(init, Color.WHITE);
        chessBoard[0][0] = rook;
        assertEquals(false, rook.isValidMove(diagonal, chessBoard));
    }

}
