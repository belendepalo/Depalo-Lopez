package linea;

import java.util.List;

class BluesTurn extends Turns {

	@Override
	public void playBlueAt(int col, List<List<Character>> board) {
		placeChip(col, board, BLUE_CHIP);
	}

	@Override
	public void playRedAt(int col, List<List<Character>> board) {
		throw new RuntimeException(ERROR_ITS_BLUES_TURN);
	}

	@Override
	public Turns nextTurn() {
		return new RedsTurn();
	}
}
