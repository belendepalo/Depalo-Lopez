package linea;

import java.util.List;

class RedsTurn extends Turns {

	@Override
	public void playRedAt(int col, List<List<Character>> board) {
		placeChip(col, board, RED_CHIP);
	}

	@Override
	public void playBlueAt(int col, List<List<Character>> board) {
		throw new RuntimeException(ERROR_ITS_REDS_TURN);
	}

	@Override
	public Turns nextTurn() {
		return new BluesTurn();
	}
}
