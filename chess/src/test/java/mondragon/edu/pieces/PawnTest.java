package mondragon.edu.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Pawn;

public class PawnTest {
    Position position;

    @Before
    public void setup() {
        position = new Position(0, 0);
    }

    @Test
    public void testPawnGetSet() {

        Pawn pawn = new Pawn(position, Color.BLACK);
        assertEquals(pawn.getPosition(), position);
        assertEquals(Color.BLACK, pawn.getColor());
    }

    @Test
    public void testPawnValidMovementFalse() {
        Pawn pawn = new Pawn(position, Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(false, pawn.isValidMove(position, chessBoard.getChessboard()));
    }

    @Test
    public void testPosition() {
        Pawn pawn = new Pawn(position, null);
        pawn.setPosition(new Position(1, 1));
        assertEquals(1, pawn.getPosition().getX());
        assertEquals(1, pawn.getPosition().getY());
    }

    @Test
    public void testPawnPieceInTheWay() throws Exception {
        Position pawnMove = new Position(1, 1);
        Position piece = new Position(2, 1);
        Position move = new Position(3, 1);
        Piece[][] chessBoard = new Piece[8][8];
        Pawn pawn = new Pawn(pawnMove, Color.BLACK);
        King pieceRand = new King(piece, Color.BLACK);
        chessBoard[1][1] = pawn;
        chessBoard[2][1] = pieceRand;
        assertEquals(false, pawn.isValidMove(move, chessBoard));
    }

    @Test
    public void testPawnEatPiece() throws Exception {
        Position pawnMove = new Position(1, 1);
        Position piece = new Position(2, 2);
        Position move = new Position(2, 2);
        Piece[][] chessBoard = new Piece[8][8];
        Pawn pawn = new Pawn(pawnMove, Color.BLACK);
        King pieceRand = new King(piece, Color.WHITE);
        chessBoard[1][1] = pawn;
        chessBoard[2][2] = pieceRand;
        assertEquals(true, pawn.isValidMove(move, chessBoard));
    }
    @Test
    public void testPawnNoEatPiece() throws Exception {
        Position pawnMove = new Position(1, 1);
        Position piece = new Position(2, 2);
        Position move = new Position(2, 2);
        Piece[][] chessBoard = new Piece[8][8];
        Pawn pawn = new Pawn(pawnMove, Color.BLACK);
        King pieceRand = new King(piece, Color.BLACK);
        chessBoard[1][1] = pawn;
        chessBoard[2][2] = pieceRand;
        assertEquals(false, pawn.isValidMove(move, chessBoard));
    }

    @Test
    public void testPawnValidMoving() throws Exception {
        Position pawnMove1 = new Position(1, 0);
        Position init = new Position(0, 0);
        Piece[][] chessBoard = new Piece[8][8];
        Pawn pawn = new Pawn(init, null);
        chessBoard[0][0] = pawn;
        assertEquals(true, pawn.isValidMove(pawnMove1, chessBoard));
    }

    @Test
    public void testPawnInvalidDiagonal() {
        Position init = new Position(0, 0);
        Position diagonal = new Position(1, 1);
        Piece[][] chessBoard = new Piece[8][8];
        Pawn pawn = new Pawn(init, Color.WHITE);
        chessBoard[0][0] = pawn;
        assertEquals(false, pawn.isValidMove(diagonal, chessBoard));
    }
}
