package linea;

public class GameEndedInTie extends GameState {

	@Override
	public String statusOfGame(GameStateManager stateManager) {
		return "The game ended in a tie!";
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return stateManager.isTie() && !stateManager.hasWinner();
	}

	@Override
	public void playRedAt(LineGame game, int column) {
		throw new RuntimeException("Game Over!");
	}

	@Override
	public void playBlueAt(LineGame game, int column) {
		throw new RuntimeException("Game Over!");
	}

	@Override
	public boolean finished() {
		return true;
	}

}
