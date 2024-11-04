package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Rook extends Piece {

    static final String BLACKROOK = "\u265C";
    static final String WHITEROOK = "\u2656";

    public Rook(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Rooks can move vertically or horizontally any number of squares.
        // They cannot jump over pieces.
        if (position.getX() == newPosition.getX()) {
            int columnStart = Math.min(position.getY(), newPosition.getY()) + 1;
            int columnEnd = Math.max(position.getY(), newPosition.getY());
            for (int column = columnStart; column < columnEnd; column++) {
                if (board[position.getX()][column] != null) {
                    return false; // There's a piece in the way
                }
            }
        } else if (position.getY() == newPosition.getY()) {
            int rowStart = Math.min(position.getX(), newPosition.getX()) + 1;
            int rowEnd = Math.max(position.getX(), newPosition.getX());
            for (int row = rowStart; row < rowEnd; row++) {
                if (board[row][position.getY()] != null) {
                    return false; // There's a piece in the way
                }
            }
        } else {
            return false; // Not a valid rook move (not straight line)
        }

        // Check the destination square for capturing
        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        if (destinationPiece == null) {
            return true; // The destination is empty, move is valid.
        } else if (destinationPiece.getColor() != this.getColor()) {
            return true; // The destination has an opponent's piece, capture is valid.
        }

        return false; // The destination has a piece of the same color, move is invalid.
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK) {
            return BLACKROOK;
        } else {
            return WHITEROOK;
        }
    }
}
