package linea;

public class BluesTurn extends GameState {
	public static String ErrorBluesTurn = "It's Blue's Turn!";

	@Override
	public String statusOfGame(GameStateManager stateManager) {
		return GameStillGoing;
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return !stateManager.isRedTurn() && !stateManager.hasWinner() && !stateManager.isTie();
	}

	@Override
	public void playRedAt(LineGame game, int column) {
		throw new RuntimeException(ErrorBluesTurn);
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
