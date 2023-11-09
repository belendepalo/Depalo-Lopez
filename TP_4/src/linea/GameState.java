package linea;

public abstract class GameState {

	public abstract void playRedAt(LineGame game, int column);

	public abstract void playBlueAt(LineGame game, int column);

	public abstract boolean finished();

	public abstract String statusOfGame();

	public abstract boolean canHandle(GameContext context);

}
