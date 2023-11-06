package linea;

import java.util.List;

public abstract class Turns {
	protected static String ErrorItsRedsTurn = "It's Red's Turn!";
	protected static String ErrorItsBluesTurn = "It's Blue's Turn!";
	
	public abstract void playBlue(int col, List<List<Character>> board);
    public abstract void playRed(int col, List<List<Character>> board);
    


}
