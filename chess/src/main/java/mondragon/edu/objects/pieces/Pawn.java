package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Pawn extends Piece {

    static final String BLACKPAWN = "\u265F";
    static final String WHITEPAWN = "\u2659";

    public Pawn(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int forwardDirection = color == Color.WHITE ? -1 : 1;
        int rowDiff = (newPosition.getX() - position.getX()) * forwardDirection;
        int colDiff = newPosition.getY() - position.getY();

        // Forward move
        if (colDiff == 0 && rowDiff == 1 && board[newPosition.getX()][newPosition.getY()] == null) {
            return true; // Move forward one square
        }

        // Initial two-square move
        boolean isStartingPosition = (color == Color.WHITE 
        && position.getX() == 6) ||
                (color == Color.BLACK && 
                position.getX() == 1);
        if (colDiff == 0 && 
        rowDiff == 2 
        && isStartingPosition
                && board[newPosition.getX()][newPosition.getY()] == null) {
            // Check the square in between for blocking pieces
            int middleRow = position.getX() + forwardDirection;
            if (board[middleRow][position.getY()] == null) {
                return true; // Move forward two squares
            }
        }

        // Diagonal capture
        if (Math.abs(colDiff) == 1 && rowDiff == 1 && board[newPosition.getX()][newPosition.getY()] != null &&
                board[newPosition.getX()][newPosition.getY()].color != this.color) {
            return true; // Capture an opponent's piece
        }
        return false;
    }
    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKPAWN;
        }else{
            return WHITEPAWN;
        }
    }
}
