package linea;

import java.util.Arrays;
import java.util.List;

public class GameStateManager {

	private List<GameState> states;

	public GameStateManager() {
		states = Arrays.asList(new RedsTurn(), new BluesTurn(), new GameEndedWithWinner(), new GameEndedInTie());
	}

	public GameState getNextState(GameContext context) {
		return states.stream()
				.filter(state -> state.canHandle(context))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No valid game state found."));
	}
}
