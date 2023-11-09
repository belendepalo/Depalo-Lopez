package linea;

import java.util.Arrays;
import java.util.List;

public class GameStateManager {

	private List<GameStatus> states;

	public GameStateManager() {
		states = Arrays.asList(new RedsTurn(), new BluesTurn(), new GameEndedWithWinner(), new GameEndedInTie());
	}

	public GameStatus getNextState(GameContext context) {
		return states.stream()
				.filter(state -> state.canHandle(context))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No valid game state found."));
	}
}
