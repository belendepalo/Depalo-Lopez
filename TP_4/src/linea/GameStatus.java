package linea;

import java.util.Arrays;
import java.util.List;

public abstract class GameStatus {
	
	private static char chipB = 'B';
	private static char chipR = 'R';
	protected static String ErrorItsRedsTurn = "It's Red's Turn!";
	protected static String ErrorItsBluesTurn = "It's Blue's Turn!";
	private List<WinningVariants> winningVariantsList = Arrays.asList(new WinningVariantA(), new WinningVariantB(), new WinningVariantC());
	public WinningVariants winVariants;
	
	public abstract void playBlueAt(int col, List<List<Character>> board);
    public abstract void playRedAt(int col, List<List<Character>> board);
	public abstract boolean isBluesTurn();
	public abstract boolean isRedsTurn();
	public abstract GameStatus checkAndUpdateGameState(List<List<Character>> board);
	
    public boolean checkWin(char chip, List<List<Character>> board) {
        return winVariants.checkWin(chip, board);
    }
	
	public boolean checkTie(List<List<Character>> board) {
		return board.get(0).stream().allMatch(cell -> cell != ' ');
    }
	
	public boolean finished(List<List<Character>> board) {
		return checkWin(chipR, board) || checkWin(chipB, board) || checkTie(board);
	}
	
	protected void initializeWinningVariant(char winVariant) {
    	winningVariantsList.stream().filter(variant -> variant
    	.canHandle(winVariant)).findFirst()
        .ifPresent(variant -> winVariants = variant);
    }
	
}
