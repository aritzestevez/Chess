package mondragon.edu;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
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
        assertEquals(Color.BLACK,bishop.getColor());
        bishop.setColor(Color.WHITE);

    }
    @Test
    public void testBishopValidMovementFalse(){
        Bishop bishop = new Bishop(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        bishop.isValidMove(position,chessBoard.getChessboard());
        assertEquals(false, bishop.isValidMove(position, chessBoard.getChessboard()));
    }
    /* @Test
    public void testBishopValidMovementTrue() throws Exception{
        Position newPos = new Position(2, 4);
        ChessBoard chessBoard = new ChessBoard();
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Piece[][] board =  (Piece[][]) boardField.get(chessBoard);
        Bishop bishop = (Bishop) board[0][2];
        assertEquals(true, bishop.isValidMove(newPos, board));
    } */
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
    @Test
    public void testRookGetSet(){

        Rook rook = new Rook(position, Color.BLACK);
        assertEquals(rook.getPosition(), position);
        assertEquals(Color.BLACK,rook.getColor());
    }
    @Test
    public void testRookValidMovementFalse(){
        Rook rook = new Rook(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, rook.isValidMove(position, chessBoard.getChessboard()));
    }
    @Test
    public void testPosition(){
        Rook rook = new Rook(position, null);
        rook.setPosition(new Position(1, 1));
        assertEquals(1, rook.getPosition().getX());
        assertEquals(1, rook.getPosition().getY());
    }
}
