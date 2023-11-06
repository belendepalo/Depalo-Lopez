package linea;

import java.util.List;

public class OnGoingGame extends GameStatus{
	
	public Turns currentTurn;
	
	public OnGoingGame(Turns initialTurn, char winVariant){	
		this.currentTurn = initialTurn;		
		initializeWinningVariant(winVariant);		
	}

	@Override
	public void playBlueAt(int col, List<List<Character>> board) {
		currentTurn.playBlue( col, board);
		this.currentTurn = new RedsTurn();
		
	}

	@Override
	public void playRedAt(int col, List<List<Character>> board) {
		currentTurn.playRed(col, board);
		this.currentTurn = new BluesTurn();
		
	}
	
	@Override
	public boolean isRedsTurn() {
        return currentTurn instanceof RedsTurn;
    }

	@Override
    public boolean isBluesTurn() {
        return currentTurn instanceof BluesTurn;
    }
	
	@Override
    public GameStatus checkAndUpdateGameState(List<List<Character>> board) {
		if (finished(board)) {
	        GameOver gameOver = new GameOver();
	        gameOver.winVariants = this.winVariants;
	        return gameOver;
        }
        return this; 
    }
	
}
