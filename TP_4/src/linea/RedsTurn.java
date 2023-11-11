package linea;

public class RedsTurn extends GameState {

	public static String ErrorRedsTurn = "It's Red's Turn!";

	@Override
	public String statusOfGame(GameStateManager stateManager) {
		return GameStillGoing;
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return stateManager.isRedTurn() && !stateManager.hasWinner() && !stateManager.isTie();
	}

	@Override
	public void playRedAt(LineGame game, int column) {
		game.placeChip('R', column);

	}

	@Override
	public void playBlueAt(LineGame game, int column) {
		throw new RuntimeException(ErrorRedsTurn);
	}

	@Override
	public boolean finished() {
		return false;
	}

}
