package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Rook extends Piece {

    final static String BLACKROOK = "\u265C";
    final static String WHITEROOK = "\u2656";

    public Rook(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
       return false;
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKROOK;
        }else{
            return WHITEROOK;
        }
    }
}
