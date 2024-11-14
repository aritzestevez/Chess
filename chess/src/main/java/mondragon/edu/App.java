package mondragon.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mondragon.edu.objects.ChessBoard;
import mondragon.edu.objects.Color;
import mondragon.edu.objects.Piece;
import mondragon.edu.objects.Position;

public class App 
{

    private Scanner input;
	private ChessBoard chessBoard;
    private boolean isGameOver;
	private Color playerColor;

	public App() {
		input = new Scanner(System.in);
		chessBoard = new ChessBoard(false);
		isGameOver = false;
		playerColor = Color.WHITE;
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
		do {
			if (isGameOver()) {
				displayGameOverMessage();
				return;
			}
			
			option = menu();
			handleMenuOption(option);
			
		} while (option != 0);
	}
	
	private boolean isGameOver() {
		return isGameOver;
	}
	
	private void displayGameOverMessage() {
		System.out.println("Game is over. Restart to play again.");
	}
	
	private void handleMenuOption(int option) {
		switch (option) {
			case 1:
				showChessboard(chessBoard);
				break;
			case 2:
				processMove();

				break;
			case 0:
				// Exit the game loop
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	private void processMove() {
		Piece piece = choosePiece();
		if (piece == null) {
			System.out.println("No valid piece selected. Please try again.");
			return;
		}
		
		movePiece(chessBoard, piece);
		checkForCheckmate();
		if(playerColor == Color.BLACK){
			playerColor = Color.WHITE;
		}else{
			playerColor = Color.BLACK;
		}
	}
	
	public void checkForCheckmate() {
		if (chessBoard.isCheckmate(Color.BLACK)) {
			System.out.println("Black is in checkmate! White wins!");
			isGameOver = true;
		} else if (chessBoard.isCheckmate(Color.WHITE)) {
			System.out.println("White is in checkmate! Black wins!");
			isGameOver = true;
		}
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
				
				if (piece != null && piece.getColor() == playerColor) {
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