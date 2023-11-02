package linea;

public class GameOver extends GameStatus{

	private static String ErrorGameOver = "Game Over!";

	@Override
	public void playBlueAt(int col) {
		throw new RuntimeException(ErrorGameOver);
		
	}

	@Override
	public void playRedAt(int col) {
		throw new RuntimeException(ErrorGameOver);
		
	}

}
