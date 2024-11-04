package mondragon.edu.objects;

import mondragon.edu.objects.pieces.King;
import mondragon.edu.objects.pieces.Pawn;
import mondragon.edu.objects.pieces.Queen;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8];
        initializePieces();
    }

    private void initializePieces() {

/*         board[0][0] = new Rook(Color.BLACK, new Position(0, 0));
        board[0][7] = new Rook(Color.BLACK, new Position(0, 7));
        board[7][0] = new Rook(Color.WHITE, new Position(7, 0));
        board[7][7] = new Rook(Color.WHITE, new Position(7, 7));

        board[0][1] = new Knight(Color.BLACK, new Position(0, 1));
        board[0][6] = new Knight(Color.BLACK, new Position(0, 6));
        board[7][1] = new Knight(Color.WHITE, new Position(7, 1));
        board[7][6] = new Knight(Color.WHITE, new Position(7, 6));

        board[0][2] = new Bishop(new Position(0, 2), Color.BLACK);
        board[0][5] = new Bishop(new Position(0, 5),Color.BLACK);
        board[7][2] = new Bishop(new Position(7, 2), Color.WHITE);
        board[7][5] = new Bishop(new Position(7, 5),Color.WHITE); */

        board[0][3] = new Queen(new Position(0, 3),Color.BLACK);
        board[7][3] = new Queen(new Position(7, 3), Color.WHITE);

        board[0][4] = new King(new Position(0, 4), Color.BLACK);
        board[7][4] = new King(new Position(7, 4), Color.WHITE);
        
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Position(1, i), Color.BLACK);
            board[6][i] = new Pawn(new Position(6, i),Color.WHITE);
        }
    }

    public Piece[][] getChessboard() {
        return board;
    }
}
