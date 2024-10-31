package mondragon.edu.objects;

import java.util.List;

public abstract class Piece {

    Position position;
    Color color;

    
    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
