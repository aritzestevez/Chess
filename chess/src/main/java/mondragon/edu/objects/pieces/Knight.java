package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Knight extends Piece{

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Check if the new position is the same as the current position
        if (newPosition.equals(this.position)) return false;

        int rowDiff = Math.abs(newPosition.getX() - this.position.getX());
        int colDiff = Math.abs(newPosition.getY() - this.position.getY());

        // Check if the move follows an "L" shape (2 squares in one direction, 1 in the other)
        boolean isLShape = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if (!isLShape) return false; // Invalid knight move if it's not an L shape

        // The move is valid if the destination is empty or contains an opponent's piece
        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    
}
