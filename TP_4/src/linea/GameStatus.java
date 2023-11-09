package linea;

public abstract class GameStatus {

	public abstract void playRedAt(LineGame game, int column);

	public abstract void playBlueAt(LineGame game, int column);

	public abstract boolean finished();

	public abstract String statusOfGame();

	public abstract boolean canHandle(GameContext context);

}
