package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Bishop extends Piece {

    static final String WHITEBISHOP = "\u265D";
    static final String BLACKBISHOP = "\u2657";
    
    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(position.getX() - newPosition.getX());
        int colDiff = Math.abs(position.getY() - newPosition.getY());

        if (rowDiff != colDiff) {
            return false; // Move is not diagonal
        }

        int rowStep = newPosition.getX() > position.getX() ? 1 : -1;
        int colStep = newPosition.getY() > position.getY() ? 1 : -1;
        int steps = rowDiff - 1; // Number of squares to check for obstruction

        // Check for obstructions along the path
        for (int i = 1; i <= steps; i++) {
            if (board[position.getX() + i * rowStep][position.getY() + i * colStep] != null) {
                return false; // There's a piece in the way
            }
        }

        // Check the destination square for capturing or moving to an empty square
        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        /* if (destinationPiece == null || destinationPiece.getColor() != this.getColor()) {
            return true; 
        } */

        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKBISHOP;
        }else{
            return WHITEBISHOP;
        }
    }
}
