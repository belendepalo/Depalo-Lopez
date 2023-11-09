package linea;

public class BluesTurn extends GameStatus {

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

	@Override
	public String statusOfGame() {
		return "The game is still ongoing.";
	}

	@Override
	public boolean canHandle(GameContext context) {
		return !context.isRedTurn() && !context.hasWinner() && !context.isTie();
	}

}
