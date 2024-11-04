package mondragon.edu;

import java.util.Scanner;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class App 
{

    private Scanner input;
	private ChessBoard chessBoard;

	public App() {
		input = new Scanner(System.in);
		chessBoard = new ChessBoard();
	}


    public int menu() {
		int option;
		System.out.println("1.- Show chessboard");
		System.out.println("2.- Move a piece");
		System.out.println("0.- Exit");
		System.out.print("Choose an option: ");
		option = input.nextInt();
		input.nextLine();
		return option;
	}

	public void run() {
		int option;
		Piece piece;

		do {
			option = menu();
			switch (option) {
				case 1:
					showChessboard(chessBoard);
					break;
				case 2:
					piece = choosePiece();
					movePiece(piece);
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid option");
			}
		} while (option != 0);
	}

    private void movePiece(Piece piece) {
		System.out.print("New row position: ");
		int row = input.nextInt();

		System.out.print("New col position: ");
		int col = input.nextInt();

		Position pos = new Position(row, col);

		/*if (piece.isValidMove(pos)) {
			piece.setPosition(pos);
			System.out.println("New piece posiiton: " + piece.getPosition());
		}
		else {
			System.out.println("Invalid position");
		}*/
	}

	private Piece choosePiece() {

		Piece[][] cb = chessBoard.getChessboard();

		System.out.print("Piece row position: ");
		int row = input.nextInt();

		System.out.print("Piece col position: ");
		int col = input.nextInt();

		return cb[row][col];
	}

	private void showChessboard(ChessBoard chessBoard) {
		throw new UnsupportedOperationException("Unimplemented method 'showChessboard'");
	}

	public static void main( String[] args )
    {
        App program = new App();
		program.run();
    }
}
