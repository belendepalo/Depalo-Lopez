package linea;

public abstract class WinVariants {
	protected LineGame game;
	protected char player;

	public abstract boolean checkWin(char chip, LineGame game);

	public abstract boolean canHandle(char variantIdentifier);

}
