package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.King;

public class KingTest {

    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
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
    
}
