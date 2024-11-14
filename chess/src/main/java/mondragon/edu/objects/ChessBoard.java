package mondragon.edu.objects;

import mondragon.edu.objects.pieces.Bishop;
import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Knight;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Queen;
import mondragon.edu.objects.pieces.Rook;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard(boolean emptyBoard) {
        this.board = new Piece[8][8];
        if (!emptyBoard) {
            initializePieces();
        }
    }

    public Piece getPiece(int row, int column) {
        return board[row][column];
    }

    public void setPiece(int row, int column, Piece piece) {
        board[row][column] = piece;
        if (piece != null) {
            piece.setPosition(new Position(row, column));
        }
    }

    private void initializePieces() {

        this.board = new Piece[8][8];
        board[0][0] = new Rook(new Position(0, 0), Color.BLACK);
        board[0][7] = new Rook(new Position(0, 7), Color.BLACK);
        board[7][0] = new Rook(new Position(7, 0), Color.WHITE);
        board[7][7] = new Rook(new Position(7, 7), Color.WHITE);

        board[0][1] = new Knight(new Position(0, 1), Color.BLACK);
        board[0][6] = new Knight(new Position(0, 6), Color.BLACK);
        board[7][1] = new Knight(new Position(7, 1), Color.WHITE);
        board[7][6] = new Knight(new Position(7, 6), Color.WHITE);

        board[0][2] = new Bishop(new Position(0, 2), Color.BLACK);
        board[0][5] = new Bishop(new Position(0, 5), Color.BLACK);
        board[7][2] = new Bishop(new Position(7, 2), Color.WHITE);
        board[7][5] = new Bishop(new Position(7, 5), Color.WHITE);

        board[0][3] = new Queen(new Position(0, 3), Color.BLACK);
        board[7][3] = new Queen(new Position(7, 3), Color.WHITE);

        board[0][4] = new King(new Position(0, 4), Color.BLACK);
        board[7][4] = new King(new Position(7, 4), Color.WHITE);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Position(1, i), Color.BLACK);
            board[6][i] = new Pawn(new Position(6, i), Color.WHITE);
        }
    }


    public boolean isInCheck(Color kingcolor) {
		Position kingPosition = findKingPosition(kingcolor);
		for (int row = 0; row < getChessboard().length; row++) {
			for (int col = 0; col < getChessboard()[row].length; col++) {
				Piece piece = getPiece(row, col);
				if (piece != null && piece.getColor() != kingcolor && piece.isValidMove(kingPosition, getChessboard())) {
                    return true; // An opposing piece can capture the king
				}
			}
		}
		return false;
    }

    public Position findKingPosition(Color color) {
        for (int row = 0; row < getChessboard().length; row++) {
            for (int col = 0; col < getChessboard()[row].length; col++) {
                Piece piece = getPiece(row, col);
                if (piece instanceof King && piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("King not found, which should never happen.");
    }

	public boolean isCheckmate(Color kingcolor) {
        if (!isInCheck(kingcolor)) {
          return false; // Not in check, so cannot be checkmate
        }

        System.out.println(kingcolor+" King is checked");
        Position kingPosition = findKingPosition(kingcolor);
        King king = (King) getPiece(kingPosition.getX(), kingPosition.getY());

        // Attempt to find a move that gets the king out of check
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue; // Skip the current position of the king
                }
                Position newPosition = new Position(kingPosition.getX() + rowOffset,
                        kingPosition.getY() + colOffset);
                // Check if moving the king to the new position is a legal move and does not
                // result in a check
                if (isPositionOnBoard(newPosition) && king.isValidMove(newPosition, getChessboard()) && !wouldBeInCheckAfterMove(kingcolor, kingPosition, newPosition)) {
                    return false; // Found a move that gets the king out of check, so it's not checkmate
                }
            }
        }
        return true; // No legal moves available to escape check, so it's checkmate

    }

    public boolean isPositionOnBoard(Position position) {
        return position.getX() >= 0 && position.getX() < getChessboard().length &&
                position.getY() >= 0 && position.getY() < getChessboard()[0].length;

    }


    private boolean wouldBeInCheckAfterMove(Color kingColor, Position from, Position to) {
        // Temporarily move the king to the target position 'to'
        Piece movingPiece = getPiece(from.getX(), from.getY());
        Piece targetPiece = getPiece(to.getX(), to.getY()); // The piece currently at 'to', if any
        
        // Simulate the move
        setPiece(to.getX(), to.getY(), movingPiece); // Place the king at 'to'
        setPiece(from.getX(), from.getY(), null);    // Vacate the original position 'from'

        // Check if the king is in check after this move
        boolean inCheck = isInCheck(kingColor);

        // Undo the move
        setPiece(from.getX(), from.getY(), movingPiece); // Restore the king back to 'from'
        setPiece(to.getX(), to.getY(), targetPiece);     // Restore any captured piece at 'to'

        return inCheck;

    }

    public Piece[][] getChessboard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    sb.append(board[row][col].toString()).append(" ");
                } else {
                    sb.append("  "); // Dos espacios para casillas vacías
                }
            }
            if (row < 7) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
