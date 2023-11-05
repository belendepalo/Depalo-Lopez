package linea;

import java.util.List;

public abstract class GameStatus {

	protected static final String ERROR_GAME_OVER = "Game Over!";
	protected static final char RED_CHIP = 'R';
	protected static final char BLUE_CHIP = 'B';

	public abstract String show(List<List<Character>> board);

	public abstract boolean finished(List<List<Character>> board);

	public abstract void playRedAt(int col, List<List<Character>> board);

	public abstract void playBlueAt(int col, List<List<Character>> board);
	
    public abstract GameStatus checkAndUpdateGameState(List<List<Character>> board);

}
