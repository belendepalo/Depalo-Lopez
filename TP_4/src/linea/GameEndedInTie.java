package linea;

public class GameEndedInTie extends GameState {

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

	@Override
	public String statusOfGame() {
		return "The game ended in a tie!";
	}

	@Override
	public boolean canHandle(GameContext context) {
		return context.isTie() && !context.hasWinner();
	}

}
