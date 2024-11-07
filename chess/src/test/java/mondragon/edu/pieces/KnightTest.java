package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Knight;

public class KnightTest {

    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
    }

    @Test
    public void testKnightGetSet(){
        Knight knight = new Knight(position, Color.BLACK);
        assertEquals(knight.getPosition(), position);
        assertEquals(Color.BLACK,knight.getColor());
    }
    @Test
    public void testKnightValidMovementFalse(){
        Knight knight = new Knight(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, knight.isValidMove(position, chessBoard.getChessboard()));
    }
    
}
