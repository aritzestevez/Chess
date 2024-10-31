package mondragon.edu.objects;

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


    public boolean validMovement(Position pos){

        Boolean isvalid = true;
        return isvalid;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }


    public Position getPosition() {
        return position;
    }


    public void setPosition(Position position) {
        this.position = position;
    }



}
