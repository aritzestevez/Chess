package mondragon.edu.pieces;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Bishop;
import mondragon.edu.objects.pieces.Rook;

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
        Position newPos = new Position(0, 1);
        ChessBoard chessBoard = new ChessBoard();
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Piece[][] board = (Piece[][]) boardField.get(chessBoard);
        Rook rook = (Rook) board[0][0];
        assertEquals(false, rook.isValidMove(newPos, board));
    }

    @Test
    public void testRookValidMoving() throws Exception {
        Position pawnMove1 = new Position(1, 3);
        Position rookMove1 = new Position(0, 2);
        ChessBoard chessBoard = new ChessBoard();
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Piece[][] board = (Piece[][]) boardField.get(chessBoard);
        Rook rook = (Rook) board[0][0];
        assertEquals(false, rook.isValidMove(newPos, board));
    }

}
