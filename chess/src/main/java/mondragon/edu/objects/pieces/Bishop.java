package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Bishop extends Piece {

    final static String BLACKBISHOP = "\u265D";
    final static String WHITEBISHOP = "\u2657";
    
    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
       return false;
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKBISHOP;
        }else{
            return WHITEBISHOP;
        }
    }
}
