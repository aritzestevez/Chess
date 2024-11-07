package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Position;
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
    public void testQueenValidMovementFalse(){
        Queen queen = new Queen(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, queen.isValidMove(position, chessBoard.getChessboard()));
    }
}
