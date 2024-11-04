package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class King extends Piece {
    final static String BLACKKING = "\u265A";
    final static String WHITEKING = "\u2654";

    public King(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(position.getX() - newPosition.getX());
        int colDiff = Math.abs(position.getY() - newPosition.getY());

        // Kings can move one square in any direction.
        boolean isOneSquareMove = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);

        if (!isOneSquareMove) {
            return false; // Move is not within one square.
        }

        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        // The move is valid if the destination is empty or contains an opponent's
        // piece.
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK) {
            return BLACKKING;
        } else {
            return WHITEKING;
        }
    }

}