package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Queen extends Piece{
    int id;
    Position position;
    Color color;
    
    public Queen(int id, int x, int y, Color color) {
        super(id, x, y, color);
    }
    
}
