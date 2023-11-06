package linea;

import java.util.List;

public class GameOver extends GameStatus{

	private static String ErrorGameOver = "Game Over!";

	@Override
    public void playBlueAt(int col, List<List<Character>> board) {
        throw new RuntimeException(ErrorGameOver);
    }

    @Override
    public void playRedAt(int col, List<List<Character>> board) {
        throw new RuntimeException(ErrorGameOver);
    }
    
    @Override
    public boolean isRedsTurn() {
    	throw new RuntimeException(ErrorGameOver);
    }
    
    @Override
    public boolean isBluesTurn() {
    	throw new RuntimeException(ErrorGameOver);
    }

	@Override
	public GameStatus checkAndUpdateGameState(List<List<Character>> board) {
		return this;
	}

}

