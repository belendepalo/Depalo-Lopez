package linea;

import java.util.List;

public abstract class Turns {

	protected static final String ERROR_ITS_REDS_TURN = "It's Red's Turn!";
	protected static final String ERROR_ITS_BLUES_TURN = "It's Blue's Turn!";
	protected static final char RED_CHIP = 'R';
	protected static final char BLUE_CHIP = 'B';

	public abstract void playRedAt(int col, List<List<Character>> board);

	public abstract void playBlueAt(int col, List<List<Character>> board);

	public abstract Turns nextTurn();

	protected void placeChip(int col, List<List<Character>> board, char chip) {
		if (board.get(0).get(col - 1) != ' ') {
			throw new RuntimeException("Column is full!");
		}

		for (int rowIndex = board.size() - 1; rowIndex >= 0; rowIndex--) {
			if (board.get(rowIndex).get(col - 1) == ' ') {
				board.get(rowIndex).set(col - 1, chip);
				return;
			}
		}
	}
}
