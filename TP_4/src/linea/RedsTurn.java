package linea;

public class RedsTurn extends GameState{

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
	public String statusOfGame() {
		return "The game is still ongoing.";
	}

	@Override
	public boolean canHandle(GameContext context) {
		return context.isRedTurn() && !context.hasWinner() && !context.isTie();
	}
	
}
