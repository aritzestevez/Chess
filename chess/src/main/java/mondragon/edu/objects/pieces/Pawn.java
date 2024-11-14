package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Pawn extends Piece {

    static final String WHITEPAWN = "\u265F";
    static final String BLACKPAWN = "\u2659";

    public Pawn(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int forwardDirection = this.getColor() == Color.WHITE ? -1 : 1;
        int rowDiff = (newPosition.getX() - getPosition().getX()) * forwardDirection;
        int colDiff = newPosition.getY() - getPosition().getY();
    
        // Forward move
        if (colDiff == 0 && rowDiff == 1 && board[newPosition.getX()][newPosition.getY()] == null) {
            return true; // Move forward one square
        }
    
        // Initial two-square move
        boolean isStartingPosition = (this.getColor() == Color.WHITE && getPosition().getX() == 6) ||
                                     (this.getColor() == Color.BLACK && getPosition().getX() == 1);
        if (colDiff == 0 && rowDiff == 2 && isStartingPosition &&
            board[newPosition.getX()][newPosition.getY()] == null) {
            // Check the square in between for blocking pieces
            int middleRow = getPosition().getX() + forwardDirection;
            if (board[middleRow][getPosition().getY()] == null) {
                return true; // Move forward two squares
            }
        }
    
        return Math.abs(colDiff) == 1 && rowDiff == 1 && board[newPosition.getX()][newPosition.getY()] != null &&
               board[newPosition.getX()][newPosition.getY()].getColor() != this.getColor();
    }
    

    @Override
    public String toString() {
        if (this.getColor() == Color.BLACK){
            return BLACKPAWN;
        }else{
            return WHITEPAWN;
        }
    }
}
