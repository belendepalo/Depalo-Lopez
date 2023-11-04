package linea;

public class GameOver extends GameStatus {

	private static String ErrorGameOver = "Game is already finished!";

	@Override
	public void playBlueAt(int col) {
		throw new RuntimeException(ErrorGameOver);

	}

	@Override
	public void playRedAt(int col) {
		throw new RuntimeException(ErrorGameOver);

	}

}
