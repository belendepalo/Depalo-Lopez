package linea;

import java.util.stream.IntStream;

public class Linea {
	private char[][] board;
	private int width;
	private int height;
	private char winVariant;

	public Linea(int width, int height, char winVariant) {
		this.width = width;
		this.height = height;
		this.winVariant = winVariant;
		this.board = new char[height][width];
		
//		El stream llena el tablero de espacios vacíos.
		IntStream.range(0, height).forEach(rowIndex -> IntStream.range(0, width).forEach(colIndex -> board[rowIndex][colIndex] = ' '));

	}

	public void playRedAt(int col) {
		placeChip(col - 1, 'R');
	}

	public void playBlueAt(int col) {
		placeChip(col - 1, 'B');
	}

	public String show() {
		StringBuilder boardStr = new StringBuilder();
//		El stream construye la representación del tablero en forma de texto.
		IntStream.range(0, height).forEach(rowIndex -> {
			IntStream.range(0, width).forEach(colIndex -> boardStr.append("|").append(board[rowIndex][colIndex]));
			boardStr.append("|\n");
		});
		return boardStr.toString();
	}

	public boolean finished() {
//		Si alguno gano o termino en empate, devuelve true.
		return checkWin('R') || checkWin('B') || checkTie();
	}

	public String winner() {
//		Cheque quien gana y devuelvo el mensaje adecuado.
		if (checkWin('R')) {
			return "Red is the winner!";
		} else if (checkWin('B')) {
			return "Blue is the winner!";
		} else if (checkTie()) {
			return "The game ended in a tie!";
		} else {
			return "";
		}
	}

	private void placeChip(int col, char chip) {		
//		Coloca el chip en la columna especificada. Si está llena lanza una excepción.
		int validRowIndex = IntStream.iterate(height - 1, rowIndex -> rowIndex - 1)
				.filter(rowIndex -> board[rowIndex][col] == ' ')
				.findFirst().orElse(-1);

		if (validRowIndex != -1) {
			board[validRowIndex][col] = chip;
		} else {
			throw new RuntimeException("Column is full!");
		}
	}

	private boolean checkWin(char chip) {
//		Verifica si el chip especificado ganó.
//		La verificación depende del valor de la variante de victoria.
		
		if (this.winVariant == 'A') {
			return checkHorizontalWin(chip) || checkVerticalWin(chip);
		} else if (this.winVariant == 'B') {
			return checkDiagonalWin(chip);
		}
		return checkHorizontalWin(chip) || checkVerticalWin(chip) || checkDiagonalWin(chip);

	}

	private boolean checkTie() {
//		Si la parte superior de todas las columnas está ocupada, es empate.
		return IntStream.range(0, width).allMatch(colIndex -> board[0][colIndex] != ' ');
	}

	private boolean checkHorizontalWin(char chip) {
		return IntStream.range(0, height).anyMatch(targetRow -> hasConsecutiveChips(chip, 4,
				IntStream.range(0, width).map(targetColumn -> board[targetRow][targetColumn])));
	}

	private boolean checkVerticalWin(char chip) {
		return IntStream.range(0, width).anyMatch(targetColumn -> hasConsecutiveChips(chip, 4,
				IntStream.range(0, height).map(targetRow -> board[targetRow][targetColumn])));
	}
	
	private boolean checkDiagonalWin(char chip) {
	    return IntStream.range(0, height - 3)
	            .anyMatch(rowIndex -> IntStream.range(0, width - 3)
	                    .anyMatch(colIndex -> checkDiagonalSequence(rowIndex, colIndex, 1, 1, chip)))
	           ||
	           IntStream.range(3, height)
	            .anyMatch(rowIndex -> IntStream.range(0, width - 3)
	                    .anyMatch(colIndex -> checkDiagonalSequence(rowIndex, colIndex, -1, 1, chip)));
	}
	
	private boolean hasConsecutiveChips(char chip, int requiredConsecutiveChips, IntStream chipSequence) {
		/**
		 * Verifica si una secuencia proporcionada contiene una cantidad requerida de
		 * chips consecutivos.
		 */
		int[] sequenceArray = chipSequence.toArray();

		return IntStream.range(0, sequenceArray.length - requiredConsecutiveChips + 1)
				.anyMatch(startIndex -> IntStream.range(startIndex, startIndex + requiredConsecutiveChips)
						.allMatch(sequenceIndex -> sequenceArray[sequenceIndex] == chip));
	}

	private boolean checkDiagonalSequence(int rowStart, int colStart, int rowIncrement, int colIncrement, char chip) {

//		Verifica una secuencia diagonal en una dirección dada.

	    return IntStream.range(0, 4)
	            .allMatch(step -> {
	                int targetRowIndex = rowStart + step * rowIncrement;
	                int targetColumnIndex = colStart + step * colIncrement;
	                return targetRowIndex >= 0 && targetRowIndex < height && targetColumnIndex >= 0 && targetColumnIndex < width && board[targetRowIndex][targetColumnIndex] == chip;
	            });
	}

}
