package mondragon.edu.objects;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        initializePieces();
    }

    private void initializePieces() {
        System.out.println("Frogatzen");
    }

    public Piece[][] getChessboard() {
        return board;
    }
}
