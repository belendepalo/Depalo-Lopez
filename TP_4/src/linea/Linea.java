package linea;

import java.util.ArrayList;
import java.util.List;

public class Linea {
	private static final String ErrorItsRedsTurn = "It's Red's Turn!";
	private static final String ErrorItsBluesTurn = "It's Blue's Turn!";
	private static final String ErrorColumnOutOfBounds = "Column out of bounds!";
	private List<List<Character>> board;
	private int width;
	private int height;
	private char winVariant;
	private char turn;

	public Linea(int width, int height, char winVariant) {
		this.width = width;
		this.height = height;
		this.winVariant = winVariant;
		this.board = new ArrayList<>();
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			List<Character> row = new ArrayList<>();
			for (int colIndex = 0; colIndex < width; colIndex++) {
				row.add(' ');
			}
			this.board.add(row);
		}
		this.turn = 'R';
	}

	public void setTurn(char turn) {
		this.turn = turn;
	}

	public boolean isRedsTurn() {
		return getTurn() == 'R';
	}

	public boolean isBluesTurn() {
		return getTurn() == 'B';
	}

	public void playRedAt(int col) {
		if (col < 1 || col > width) {
			throw new RuntimeException(ErrorColumnOutOfBounds);
		}
		if (turn == 'R') {
			placeChip(col - 1, 'R');
			turn = 'B';
		} else {
			throw new RuntimeException(ErrorItsBluesTurn);
		}
	}

	public void playBlueAt(int col) {
		if (col < 1 || col > width) {
			throw new RuntimeException(ErrorColumnOutOfBounds);
		}
		if (turn == 'B') {
			placeChip(col - 1, 'B');
			turn = 'R';
		} else {
			throw new RuntimeException(ErrorItsRedsTurn);
		}
	}

	public void placeChip(int col, char chip) {
		for (int rowIndex = height - 1; rowIndex >= 0; rowIndex--) {
			if (board.get(rowIndex).get(col) == ' ') {
				board.get(rowIndex).set(col, chip);
				return;
			}
		}
		throw new RuntimeException("Column is full!");
	}

	public String show() {
		StringBuilder boardStr = new StringBuilder();
		for (List<Character> row : board) {
			for (char cell : row) {
				boardStr.append("|").append(cell);
			}
			boardStr.append("|\n");
		}
		return boardStr.toString();
	}

	public boolean finished() {
		return checkWin('R') || checkWin('B') || checkTie();
	}

	public String winner() {
		if (checkWin('R')) {
			return "Red is the winner!";
		} else if (checkWin('B')) {
			return "Blue is the winner!";
		} else if (checkTie()) {
			return "The game ended in a tie!";
		} else {
			return "The game is still ongoing.";
		}
	}

	private boolean checkWin(char chip) {
		if (this.winVariant == 'C') {
			return checkHorizontal(chip) || checkVertical(chip) || checkDiagonal(chip);
		} else if (this.winVariant == 'A') {
			return checkHorizontal(chip) || checkVertical(chip);
		} else {
			return checkDiagonal(chip);
		}
	}

	private boolean checkHorizontal(char chip) {
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			int count = 0;
			for (int colIndex = 0; colIndex < width; colIndex++) {
				if (board.get(rowIndex).get(colIndex) == chip) {
					count++;
					if (count == 4)
						return true;
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	private boolean checkVertical(char chip) {
		for (int colIndex = 0; colIndex < width; colIndex++) {
			int count = 0;
			for (int rowIndex = 0; rowIndex < height; rowIndex++) {
				if (board.get(rowIndex).get(colIndex) == chip) {
					count++;
					if (count == 4)
						return true;
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	private boolean checkDiagonal(char chip) {
		// Verificación de diagonales principales
		for (int rowIndex = 0; rowIndex < height - 3; rowIndex++) {
			for (int colIndex = 0; colIndex < width - 3; colIndex++) {
				if (board.get(rowIndex).get(colIndex) == chip && board.get(rowIndex + 1).get(colIndex + 1) == chip
						&& board.get(rowIndex + 2).get(colIndex + 2) == chip
						&& board.get(rowIndex + 3).get(colIndex + 3) == chip) {
					return true;
				}
			}
		}

		// Verificación de diagonales secundarias
		for (int rowIndex = 3; rowIndex < height; rowIndex++) {
			for (int colIndex = 0; colIndex < width - 3; colIndex++) {
				if (board.get(rowIndex).get(colIndex) == chip && board.get(rowIndex - 1).get(colIndex + 1) == chip
						&& board.get(rowIndex - 2).get(colIndex + 2) == chip
						&& board.get(rowIndex - 3).get(colIndex + 3) == chip) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkTie() {
		for (char cell : board.get(0)) {
			if (cell == ' ') {
				return false;
			}
		}
		return true;
	}

	// Extras
	public char getTurn() {
		return turn;
	}
}
