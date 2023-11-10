package linea;

import java.util.Arrays;
import java.util.List;

public class GameStateManager {

	private List<GameState> states = Arrays.asList(new RedsTurn(), new BluesTurn(), new GameEndedWithWinner(), new GameEndedInTie());
	private boolean isRedTurn = true;
	private boolean hasWinner = false;
	private boolean isTie = false;
	private char lastPlayedChip;

	public GameStateManager(char lastPlayedChip) {
		this.lastPlayedChip = lastPlayedChip;
	}

	public GameState getNextState(GameStateManager stateManager) {
		return states.stream().filter(state -> state.canHandle(stateManager)).findFirst()
					 .orElseThrow(() -> new RuntimeException("No valid game state found."));
	}

	public void updateContextAfterPlay(char chip) {
		this.lastPlayedChip = chip;
		this.isRedTurn = !this.isRedTurn;
	}

	public boolean isRedTurn() {
		return isRedTurn;
	}

	public boolean hasWinner() {
		return hasWinner;
	}

	public void setWinner(boolean winner) {
		hasWinner = winner;
	}

	public boolean isTie() {
		return isTie;
	}

	public void setTie(boolean tie) {
		isTie = tie;
	}

	public char getLastPlayedChip() {
		return lastPlayedChip;
	}

}
