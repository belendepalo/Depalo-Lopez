package linea;

public class GameEndedWithWinner extends GameState {

	@Override
	public String statusOfGame(GameStateManager stateManager) {
		return stateManager.getLastPlayedChip() + " has won the game!";
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return stateManager.hasWinner();
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
