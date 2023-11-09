package linea;

public class GameEndedWithWinner extends GameState {

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
		return "You won the game!";
	}

	@Override
	public boolean canHandle(GameContext context) {
		return context.hasWinner();
	}

}
