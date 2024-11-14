package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Pawn;

public class PawnTest {
    Position position;
    Position position2;
    Piece[][] board;

    @Before
    public void setup() throws Exception{
        position = new Position(0, 0);
        position2 = new Position(1, 0);
        ChessBoard chessBoard = new ChessBoard(true);
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        board = (Piece[][]) boardField.get(chessBoard);

    }

    @Test
    public void testPawnGetSet(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        assertEquals(pawn.getPosition(), position);
        assertEquals(Color.BLACK,pawn.getColor());
    }
    
    @Test
    public void testPawnValidMovementFalseSamePos(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(false, pawn.isValidMove(position, chessBoard));
    }
    @Test
    public void testPawnValidMovementFalsePieceInPos(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        Pawn pawn2 = new Pawn(position2, Color.WHITE);
        Piece[][] chessBoard = new Piece[8][8];
        chessBoard[1][0] = pawn2;
        assertEquals(false, pawn.isValidMove(position2, chessBoard));
    }
    @Test
    public void testPawnValidEatingTruePieceInPos(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(1, 1), Color.WHITE);
        Piece[][] chessBoard = new Piece[8][8];
        chessBoard[1][1] = pawn2;
        assertEquals(true, pawn.isValidMove(new Position(1, 1), chessBoard));
    }
    @Test
    public void testPawnValidEatingFalseSameColor(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(1, 1), Color.BLACK);
        Piece[][] chessBoard = new Piece[8][8];
        chessBoard[1][1] = pawn2;
        assertEquals(false, pawn.isValidMove(new Position(1, 1), chessBoard));
    }
    @Test
    public void testPawnValidEatingFalseNoPiece(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(false, pawn.isValidMove(new Position(1, 1), chessBoard));
    }
    @Test
    public void testPawnValidMovementTrueNoPiece(){
        Pawn pawn = new Pawn(position, Color.BLACK);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(true, pawn.isValidMove(position2, chessBoard));
    }
    @Test
    public void testPawnValidInitTrueBlack(){
        Pawn pawn = new Pawn(position2, Color.BLACK);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(true, pawn.isValidMove(new Position(3, 0), chessBoard));
    }
    @Test
    public void testPawnValidInitFalseBlack(){
        Pawn pawn = new Pawn(new Position(3, 0), Color.BLACK);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(false, pawn.isValidMove(new Position(5, 0), chessBoard));
    }
    @Test
    public void testPawnValidInitTrueWhite(){
        Pawn pawn = new Pawn(new Position(6, 0), Color.WHITE);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(true, pawn.isValidMove(new Position(4, 0), chessBoard));
    }
    @Test
    public void testPawnValidInitFalseWhite(){
        Pawn pawn = new Pawn(new Position(5, 0), Color.WHITE);
        Piece[][] chessBoard = new Piece[8][8];
        assertEquals(false, pawn.isValidMove(new Position(3, 0), chessBoard));
    }
    @Test
    public void testPawnValidInitFalse(){
        Pawn pawn = new Pawn(new Position(6, 0), Color.WHITE);
        Pawn pawn2 = new Pawn(new Position(4, 0), Color.WHITE);
        Piece[][] chessBoard = new Piece[8][8];
        chessBoard[4][0] = pawn2;
        assertEquals(false, pawn.isValidMove(new Position(4, 0), chessBoard));
    }
    @Test
    public void testPawnValidInitFalseMiddle(){
        Pawn pawn = new Pawn(new Position(6, 0), Color.WHITE);
        Pawn pawn2 = new Pawn(new Position(5, 0), Color.WHITE);
        Piece[][] chessBoard = new Piece[8][8];
        chessBoard[5][0] = pawn2;
        assertEquals(false, pawn.isValidMove(new Position(4, 0), chessBoard));
    }
}
