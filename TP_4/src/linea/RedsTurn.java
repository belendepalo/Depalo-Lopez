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
	public String statusOfGame(GameStateManager stateManager) {
		return "The game is still ongoing.";
	}

	@Override
	public boolean canHandle(GameStateManager stateManager) {
		return stateManager.isRedTurn() && !stateManager.hasWinner() && !stateManager.isTie();
	}
	
}
