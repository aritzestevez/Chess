package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Queen extends Piece {

    static final String WHITEQUEEN = "\u265B";
    static final String BLACKQUEEN = "\u2655";

    public Queen(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Check if the new getPosition() is the same as the current getPosition()
        if (newPosition.equals(this.getPosition())) {
            return false;
        }

        int rowDiff = Math.abs(newPosition.getX() - this.getPosition().getX());
        int colDiff = Math.abs(newPosition.getY() - this.getPosition().getY());

        // Check for straight line movement
        boolean straightLine = this.getPosition().getX() == newPosition.getX()
                || this.getPosition().getY() == newPosition.getY();

        // Check for diagonal movement
        boolean diagonal = rowDiff == colDiff;

        if (!straightLine && !diagonal) {
            return false; // The move is neither straight nor diagonal
        }

        // Calculate direction of movement
        int rowDirection = Integer.compare(newPosition.getX(), this.getPosition().getX());
        int colDirection = Integer.compare(newPosition.getY(), this.getPosition().getY());

        // Check for any pieces in the path
        int currentRow = this.getPosition().getX() + rowDirection;
        int currentCol = this.getPosition().getY() + colDirection;
        while (currentRow != newPosition.getX() || currentCol != newPosition.getY()) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path is blocked
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // The move is valid if the destination is empty or contains an opponent's piece
        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.BLACK) {
            return BLACKQUEEN;
        } else {
            return WHITEQUEEN;
        }
    }

}
