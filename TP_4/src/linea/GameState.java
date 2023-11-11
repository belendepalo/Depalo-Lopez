package linea;

public abstract class GameState {
	public static String GameStillGoing = "The game is still ongoing.";
	public static String GameEndedTie = "The game ended in a tie!";
	public static String GameOver = "Game Over!";


	public abstract String statusOfGame(GameStateManager stateManager);

	public abstract boolean canHandle(GameStateManager stateManager);

	public abstract void playBlueAt(LineGame game, int column);

	public abstract void playRedAt(LineGame game, int column);

	public abstract boolean finished();

}
