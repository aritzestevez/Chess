package mondragon.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;
import mondragon.edu.objects.pieces.King;

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
		System.out.println("\n1.- Show chessboard");
		System.out.println("2.- Move a piece");
		System.out.println("0.- Exit");
		System.out.print("Choose an option: ");
		option = input.nextInt();
		input.nextLine();
		System.out.print("\n");
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
					if (piece != null) {
						movePiece(chessBoard, piece);
					} else {
						System.out.println("No piece was selected. Please choose a valid piece.");
						// You may also want to prompt the user to try again or handle this case differently
					}
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid option");
			}
		} while (option != 0);
	}

    private void movePiece(ChessBoard chessBoard, Piece piece){

		System.out.print("New row position: ");
		int row = input.nextInt();

		System.out.print("New col position: ");
		int col = input.nextInt();

		Position pos = new Position(row, col);

		piece.movePiece(chessBoard,pos);//hacer un try out of bounds

	}

	private Piece choosePiece() {
		int i = 1;
		Piece[][] cb = chessBoard.getChessboard();
		List<int[]> piecePositions = new ArrayList<>();

		for (int row = 0; row < cb.length; row++) {
			for (int col = 0; col < cb[row].length; col++) {
				Piece piece = cb[row][col];
				
				if (piece != null) {
					System.out.println(i + ". " + piece.getColor() + " " + piece.getClass().getSimpleName() + " (" + row + ", " + col + ")");
					piecePositions.add(new int[]{row, col});
					i++;
				}
			}
		}

		System.out.print("Piece to be moved (enter number): ");
		int id = input.nextInt();
		
		if (id < 1 || id > piecePositions.size()) {
			System.out.println("Invalid selection. Please try again.");
			return null;
		}
		
		int[] selectedPosition = piecePositions.get(id - 1);
		
		return cb[selectedPosition[0]][selectedPosition[1]];
	}

	private void showChessboard(ChessBoard chessBoard) {
		System.out.println(chessBoard);
	}

	public static void main( String[] args )
    {
        App program = new App();
		program.run();
    }
}