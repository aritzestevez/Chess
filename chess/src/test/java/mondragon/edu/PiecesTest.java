package mondragon.edu;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.nio.file.WatchEvent.Kind;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Bishop;
import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Knight;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Queen;
import mondragon.edu.objects.pieces.Rook;

public class PiecesTest {
    
    Position position;

    @Before
    public void setup(){
        position = new Position(0, 0);
    }

    @Test
    public void testBishopGetSet(){
        Bishop bishop = new Bishop(position, Color.BLACK);
        assertEquals(bishop.getPosition(), position);
        assertEquals(bishop.getColor(),Color.BLACK);
        bishop.setColor(Color.WHITE);

    }
    @Test
    public void testKingGetSet(){
        King king = new King(position, Color.BLACK);
        assertEquals(king.getPosition(), position);
        assertEquals(king.getColor(),Color.BLACK);
    }
    @Test
    public void testKnightGetSet(){
        Knight knight = new Knight(position, Color.BLACK);
        assertEquals(knight.getPosition(), position);
        assertEquals(knight.getColor(),Color.BLACK);
    }
    @Test
    public void testPawnGetSet(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        assertEquals(pawn.getPosition(), position);
        assertEquals(pawn.getColor(),Color.BLACK);
    }
    /* @Test
    public void testPawnValidMovement(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        pawn.validMovement(position);
    } */
    @Test
    public void testQueenGetSet(){
        Queen queen = new Queen(position, Color.BLACK);
        assertEquals(queen.getPosition(), position);
        assertEquals(queen.getColor(),Color.BLACK);
    }
    @Test
    public void testRookGetSet(){

        Rook rook = new Rook(position, Color.BLACK);
        assertEquals(rook.getPosition(), position);
        assertEquals(rook.getColor(),Color.BLACK);
    }
    @Test
    public void testPosition(){
        Rook rook = new Rook(position, null);
        rook.setPosition(new Position(1, 1));
    }
    @Test 
    public  void testToString(){
        Rook rook = new Rook(position, null);
        rook.toString();
    }
}
