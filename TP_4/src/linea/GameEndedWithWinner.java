package linea;

public class GameEndedWithWinner extends GameStatus{

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
	public String StatusOfGame() {
		return "You won the game!";
	}

	@Override
	public GameStatus changeOfTurnsWhilePlaying() {
		return this;
	}


}
