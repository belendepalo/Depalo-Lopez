package linea;

import java.util.List;

public class OngoingGame extends GameStatus {

	private Turns currentTurn;
	private WinningVariants winVariant;

	public OngoingGame(Turns initialTurn, WinningVariants initialWinVariant) {
		this.currentTurn = initialTurn;
		this.winVariant = initialWinVariant;
	}

	@Override
	public void playRedAt(int col, List<List<Character>> board) {
		currentTurn.playRedAt(col, board);
		currentTurn = currentTurn.nextTurn();
	}

	@Override
	public void playBlueAt(int col, List<List<Character>> board) {
		currentTurn.playBlueAt(col, board);
		currentTurn = currentTurn.nextTurn();
	}

	@Override
	public String show(List<List<Character>> board) {
		StringBuilder boardStr = new StringBuilder();
		for (List<Character> row : board) {
			for (char cell : row) {
				boardStr.append("|").append(cell);
			}
			boardStr.append("|\n");
		}
		return boardStr.toString();
	}

	@Override
	public boolean finished(List<List<Character>> board) {
		return winVariant.checkWin(RED_CHIP, board) || winVariant.checkWin(BLUE_CHIP, board)
				|| winVariant.checkTie(board);

	}

}
