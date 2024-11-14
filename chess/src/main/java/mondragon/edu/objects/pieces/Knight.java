package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Knight extends Piece {

    static final String WHITEKNIGHT = "\u265E";
    static final String BLACKKNIGHT = "\u2658";

    public Knight(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if (newPosition.equals(this.getPosition())) {
            return false; // Cannot move to the same getPosition()
        }

        int rowDiff = Math.abs(this.getPosition().getX() - newPosition.getX());
        int colDiff = Math.abs(this.getPosition().getY() - newPosition.getY());

        // Check for the 'L' shaped move pattern
        boolean isValidLMove = (rowDiff == 2 
        && colDiff == 1) 
        || (rowDiff == 1
         && colDiff == 2);

        if (!isValidLMove) {
            return false; // Not a valid knight move
        }

        // Move is valid if the destination square is empty or contains an opponent's
        // piece
        Piece targetPiece = board[newPosition.getX()][newPosition.getY()];
        if (targetPiece == null) {
            return true; // The square is empty, move is valid
        } else {
            return targetPiece.getColor() != this.getColor(); // Can capture if it's an opponent's piece
        }
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.BLACK){
            return BLACKKNIGHT;
        }else{
            return WHITEKNIGHT;
        }
    }

}
