package linea;

public class GameEndedInTie extends GameState {

	@Override
	public String statusOfGame(GameStateManager stateManager) {
		return GameEndedTie;
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return stateManager.isTie() && !stateManager.hasWinner();
	}

	@Override
	public void playRedAt(LineGame game, int column) {
		throw new RuntimeException(GameOver);
	}

	@Override
	public void playBlueAt(LineGame game, int column) {
		throw new RuntimeException(GameOver);
	}

	@Override
	public boolean finished() {
		return true;
	}

}
