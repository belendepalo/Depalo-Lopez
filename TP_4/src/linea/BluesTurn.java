package linea;

public class BluesTurn extends GameState {

	@Override
	public String statusOfGame(GameStateManager stateManager) {
		return "The game is still ongoing.";
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return !stateManager.isRedTurn() && !stateManager.hasWinner() && !stateManager.isTie();
	}

	@Override
	public void playRedAt(LineGame game, int column) {
		throw new RuntimeException("It's Blue's Turn!");
	}

	@Override
	public void playBlueAt(LineGame game, int column) {
		game.placeChip('B', column);
	}

	@Override
	public boolean finished() {
		return false;
	}

}
