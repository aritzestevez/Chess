package mondragon.edu.objects.pieces;

import java.util.List;

import mondragon.edu.objects.ChessBoard;
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
        // Check if the new position is the same as the current position
        if (newPosition.equals(this.position)) {
            return false;
        }
    
        int rowDiff = Math.abs(newPosition.getX() - this.position.getX());
        int colDiff = Math.abs(newPosition.getY() - this.position.getY());
    
        // Check if the king moves only one square in any direction
        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }
    
        // The move is valid if the destination is empty or contains an opponent's piece
        Piece destinationPiece = board[newPosition.getX()][newPosition.getY()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }
    
    @Override
    public String toString() {
        if (this.color == Color.BLACK){
            return BLACKKING;
        }else{
            return WHITEKING;
        }
    }

    
}