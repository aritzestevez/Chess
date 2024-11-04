package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class King extends Piece {
    static final String BLACKKING = "\u265A";
    static final String WHITEKING = "\u2654";

    public King(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
       return false;
    }
    
    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKKING;
        }else{
            return WHITEKING;
        }
    }

    
}