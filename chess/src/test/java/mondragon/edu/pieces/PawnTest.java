package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Pawn;

public class PawnTest {
    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
    }

    @Test
    public void testPawnGetSet(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        assertEquals(pawn.getPosition(), position);
        assertEquals(Color.BLACK,pawn.getColor());
    }
    
    @Test
    public void testPawnValidMovementFalse(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, pawn.isValidMove(position, chessBoard.getChessboard()));
    }
}
