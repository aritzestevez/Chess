package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Pawn extends Piece {

    public Pawn(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean validMovement(Position pos) {
        // TODO Auto-generated method stub
        return super.validMovement(pos);
    }

    
}
