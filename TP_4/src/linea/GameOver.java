package linea;

import java.util.List;

public class GameOver extends GameStatus {

	@Override
	public void playRedAt(int col, List<List<Character>> board) {
		throw new RuntimeException(ERROR_GAME_OVER);
	}

	@Override
	public void playBlueAt(int col, List<List<Character>> board) {
		throw new RuntimeException(ERROR_GAME_OVER);
	}

	@Override
	public String show(List<List<Character>> board) {
		throw new RuntimeException(ERROR_GAME_OVER);
	}

	@Override
	public boolean finished(List<List<Character>> board) {
		return true;
	}

	@Override
	public GameStatus checkAndUpdateGameState(List<List<Character>> board) {
		return this;
	}

}
