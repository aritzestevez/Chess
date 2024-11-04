package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Queen extends Piece {

    final static String BLACKQUEEN = "\u265B";
    final static String WHITEQUEEN = "\u2655";

    public Queen(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
       return false;
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKQUEEN;
        }else{
            return WHITEQUEEN;
        }
    }

    

}
