package linea;

public class BluesTurn extends GameStatus{

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
	public String StatusOfGame() {
		return "The game is still ongoing.";
	}

	@Override
	public GameStatus changeOfTurnsWhilePlaying() {
		return new RedsTurn();
	}
	
}
