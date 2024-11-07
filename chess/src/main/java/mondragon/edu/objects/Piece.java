package mondragon.edu.objects;

import java.util.Scanner;

public abstract class Piece {

    public Position position;
    public Color color;

    
    protected Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void movePiece(ChessBoard chessBoard, Position pos) {
        

		if (this.isValidMove(pos,chessBoard.getChessboard())) {
			// Update chessboard
			Position currentPos = this.getPosition();
			Piece[][] cb = chessBoard.getChessboard();
			cb[currentPos.getX()][currentPos.getY()] = null;
			cb[pos.getX()][pos.getY()] = this;

			// Update piece
			this.setPosition(pos);
			System.out.println("New piece position: (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")");

		}
		else {
			System.out.println("Invalid position");
		}
	}

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public abstract boolean isValidMove(Position newPosition, Piece[][] board);


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
