package mondragon.edu.objects.pieces;

import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class Rook extends Piece {

    static final String WHITEROOK = "\u265C";
    static final String BLACKROOK = "\u2656";

    public Rook(Position pos, Color color) {
        super(pos, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Rooks can move vertically or horizontally any number of squares.
        // They cannot jump over pieces.
        if (getPosition().getX() == newPosition.getX()) {
            int columnStart = Math.min(getPosition().getY(), newPosition.getY()) + 1;
            int columnEnd = Math.max(getPosition().getY(), newPosition.getY());
            for (int column = columnStart; column < columnEnd; column++) {
                if (board[getPosition().getX()][column] != null) {
                    return false; // There's a piece in the way
                }
            }
        } else if (getPosition().getY() == newPosition.getY()) {
            int rowStart = Math.min(getPosition().getX(), newPosition.getX()) + 1;
            int rowEnd = Math.max(getPosition().getX(), newPosition.getX());
            for (int row = rowStart; row < rowEnd; row++) {
                if (board[row][getPosition().getY()] != null) {
                    return false; // There's a piece in the way
                }
            }
        } else {
            return false; // Not a valid rook move (not straight line)
        }

        // Check the destination square for capturing
        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.BLACK) {
            return BLACKROOK;
        } else {
            return WHITEROOK;
        }
    }
}
