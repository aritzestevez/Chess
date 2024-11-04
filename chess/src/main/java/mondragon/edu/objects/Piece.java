package mondragon.edu.objects;

import java.util.List;

public abstract class Piece {

    protected Position position;
    protected Color color;

    
    protected Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean validMovement(Position pos){
        Boolean isvalid = true;
        return isvalid;
    }
    
    public abstract boolean isValidMove(Position newPosition, Piece[][] board);
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
