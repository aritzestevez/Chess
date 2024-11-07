package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.App;
import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.Bishop;
import mondragon.edu.objects.pieces.Pawn;
import net.bytebuddy.matcher.MethodOverrideMatcher;

public class BishopTest {

    Position position;
    Piece[][] board;
    Method movePiece;

    @Before
    public void setup() throws Exception{
        position = new Position(0, 0);
        ChessBoard chessBoard = new ChessBoard();
        Field boardField = ChessBoard.class.getDeclaredField("board");
        boardField.setAccessible(true);
        board = (Piece[][]) boardField.get(chessBoard);
        movePiece = App.class.getDeclaredMethod("movePiece",Piece.class);
        movePiece.setAccessible(true);

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
    @Test
    public void testBishopPieceInTheWay() throws Exception{
        Position newPos = new Position(2, 0);

        Bishop bishop = (Bishop) board[0][2];
        assertEquals(false, bishop.isValidMove(newPos, board));
    }
    @Test
    public void testBishopValidMove() throws Exception, Throwable{
        Position newPos = new Position(2, 0);
        ChessBoard chessBoard = new ChessBoard();

        Pawn pawn = (Pawn) board[1][1];
        
        Bishop bishop = (Bishop) board[0][2];
        

        assertEquals(true, bishop.isValidMove(newPos, board));
    }
}
