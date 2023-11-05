package linea;

import java.util.List;

public class DiagonalOnlyVariant extends WinningVariants {

	public DiagonalOnlyVariant() {
		super('B');
	}

	@Override
	public boolean canHandle(char variantIdentifier) {
		return 'B' == variantIdentifier;
	}

	@Override
	public boolean checkWin(char chip, List<List<Character>> board) {
		return checkDiagonal(chip, board);
	}

	public boolean checkDiagonal(char chip, List<List<Character>> board) {
		// Verificación de diagonales principales
		for (int rowIndex = 0; rowIndex < board.size() - 3; rowIndex++) {
			for (int colIndex = 0; colIndex < board.get(0).size() - 3; colIndex++) {
				if (board.get(rowIndex).get(colIndex) == chip && board.get(rowIndex + 1).get(colIndex + 1) == chip
						&& board.get(rowIndex + 2).get(colIndex + 2) == chip
						&& board.get(rowIndex + 3).get(colIndex + 3) == chip) {
					return true;
				}
			}
		}

		// Verificación de diagonales secundarias
		for (int rowIndex = 3; rowIndex < board.size(); rowIndex++) {
			for (int colIndex = 0; colIndex < board.get(0).size() - 3; colIndex++) {
				if (board.get(rowIndex).get(colIndex) == chip && board.get(rowIndex - 1).get(colIndex + 1) == chip
						&& board.get(rowIndex - 2).get(colIndex + 2) == chip
						&& board.get(rowIndex - 3).get(colIndex + 3) == chip) {
					return true;
				}
			}
		}

		return false;
	}

}
