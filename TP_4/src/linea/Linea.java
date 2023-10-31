package linea;

public class Linea {
	private char[][] board;
	private int width;
	private int height;
	private char winVariant;
	private char turn;

	public Linea(int width, int height, char winVariant) {
		this.width = width;
		this.height = height;
		this.winVariant = winVariant;
		this.board = new char[height][width];
		this.turn = 'R';
		
	
		
//		este for crea el tablero de espacios vacios
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int colIndex = 0; colIndex < width; colIndex++) {
				board[rowIndex][colIndex] = ' ';
			}
		}
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
		if (turn == 'R') {
			placeChip(col - 1, 'R');
            turn = 'B';
        } else {
            throw new RuntimeException("It's Blue's Turn!");
        }

	}

	public void playBlueAt(int col) {
		if (turn == 'B') {
			placeChip(col - 1, 'B');
            turn = 'R';
        } else {
            throw new RuntimeException("It's Red's Turn!");
        }
		
	}

	private void placeChip(int col, char chip) {
//		coloca el chip en la columna especificada. si esta llena, lanza una excepcion
		for (int rowIndex = height - 1; rowIndex >= 0; rowIndex--) {
			if (board[rowIndex][col] == ' ') {
				board[rowIndex][col] = chip;
				return;
			}
		}
		throw new RuntimeException("Column is full!");
	}

	public String show() {
		StringBuilder boardStr = new StringBuilder();
//		loop construye la representacion del tablero en forma de texto.
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				boardStr.append("|").append(board[i][j]);
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
//		verifica si el chip especificado gano.
//		la verificacion depende del valor de la variente de victoria.
		if (this.winVariant == 'C') {
			return checkHorizontal(chip) || checkVertical(chip) || checkDiagonal(chip);
		}
		else if(this.winVariant == 'A') {
			return checkHorizontal(chip) || checkVertical(chip);
		}else {
			return checkDiagonal(chip); 
		}
		
	}

	private boolean checkHorizontal(char chip) {
		
		for (int targetRow = 0; targetRow < height; targetRow++) {
			int count = 0;
			for (int targetColumn = 0; targetColumn < width; targetColumn++) {
				if (board[targetRow][targetColumn] == chip) {
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
		for (int targetColumn = 0; targetColumn < width; targetColumn++) {
			int count = 0;
			for (int targetRow = 0; targetRow < height; targetRow++) {
				if (board[targetRow][targetColumn] == chip) {
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
				if (board[rowIndex][colIndex] == chip && board[rowIndex + 1][colIndex + 1] == chip && board[rowIndex + 2][colIndex + 2] == chip
						&& board[rowIndex + 3][colIndex + 3] == chip) {
					return true;
				}
			}
		}

		// Verificación de diagonales secundarias
		for (int rowIndex = 3; rowIndex < height; rowIndex++) {
			for (int colIndex = 0; colIndex < width - 3; colIndex++) {
				if (board[rowIndex][colIndex] == chip && board[rowIndex - 1][colIndex + 1] == chip && board[rowIndex - 2][colIndex + 2] == chip
						&& board[rowIndex - 3][colIndex + 3] == chip) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkTie() {
//		si la parte superior de todas las columnas esta ocupada, es empate.
		for (int colIndex = 0; colIndex < width; colIndex++) {
			if (board[0][colIndex] == ' ') {
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
