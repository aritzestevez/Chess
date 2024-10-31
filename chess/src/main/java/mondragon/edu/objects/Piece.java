package mondragon.edu.objects;

import java.util.List;

public abstract class Piece {

    Position position;
    int id;
    Color color;

    public Piece(int id, Position position, Color color) {
        this.id = id;
        this.position = position;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract List<Square> getLegalMoves(ChessBoard b);

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
