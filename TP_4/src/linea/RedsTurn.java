package linea;

public class RedsTurn extends GameStatus{

	@Override
	public void playRedAt(LineGame game, int column) {
		game.placeChip('R', column);		
	}

	@Override
	public void playBlueAt(LineGame game, int column) {
		throw new RuntimeException("It's Red's Turn!");		
	}

	@Override
	public boolean finished() {
		return false;
	}

	@Override
	public String StatusOfGame() {
		return "The game is still ongoing.";
	}

	@Override
	public GameStatus changeOfTurnsWhilePlaying() {
		return new BluesTurn();
	}
	
}
