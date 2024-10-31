package mondragon.edu;

import java.util.Scanner;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Piece;

/**
 * Hello world!
 *
 */
public class App 
{

    private Scanner input;
	private ChessBoard chessBoard;

	public App() {
		input = new Scanner(System.in);
	}

    public int menu() {
		int option;
		System.out.println("1.- Show chessboard");
		System.out.println("2.- Move pawn");
		System.out.println("3.- Move rook");
		System.out.println("4.- Move bishop");
		System.out.println("5.- Move knight");
		System.out.println("6.- Move king");
		System.out.println("7.- Move queen");
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
				case 2, 3, 4, 5, 6, 7:
					piece = choosePiece(option);
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'movePiece'");
	}

	private Piece choosePiece(int piece) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'choosePiece'");
	}

	private void showChessboard(ChessBoard chessBoard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'showChessboard'");
	}

	public static void main( String[] args )
    {
        App program = new App();
		program.run();
    }
}
