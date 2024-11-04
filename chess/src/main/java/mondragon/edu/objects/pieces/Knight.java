package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Knight extends Piece{

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
       return false;
    }
    
}
