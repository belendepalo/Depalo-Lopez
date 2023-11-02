package linea;

public abstract class GameStatus {
	
	protected static String ErrorItsRedsTurn = "It's Red's Turn!";
	protected static String ErrorItsBluesTurn = "It's Blue's Turn!";
	
	public abstract void playBlueAt(int col);
	public abstract void playRedAt(int col);
}
