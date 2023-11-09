package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineGame {

	private static final String ErrorFullColumn = "Column is full!";
	public static final String ErrorColumnOutOfBounds = "Column is out of parameter!";
	private int width;
	private int height;
	private GameStateManager gameStateManager;
	private GameContext gameContext;
	private List<List<Character>> board = new ArrayList<>();
	public GameStatus gameStatus = new RedsTurn();
	private List<WinVariants> winningVariantsList = Arrays.asList(new WinVariantA(), new WinVariantB(), new WinVariantC());
	private WinVariants winVariants;

	public LineGame(int width, int height, char winVariant) {
		this.width = width;
		this.height = height;
		initializeBaseOfBoard();
		initializeWinningVariant(winVariant);
		this.gameStateManager = new GameStateManager();
		this.gameContext = new GameContext('R');
	}

	public boolean isRedsTurn() {
		return gameStatus instanceof RedsTurn;
	}

	public boolean isBluesTurn() {
		return gameStatus instanceof BluesTurn;
	}

	public void playRedAt(int column) {
		gameStatus.playRedAt(this, column - 1);
		updateGameStatus('R');
	}

	public void playBlueAt(int column) {
		gameStatus.playBlueAt(this, column - 1);
		updateGameStatus('B');

	}

	private void initializeBaseOfBoard() {
		for (int i = 0; i < width; i++) {
			board.add(new ArrayList<>()); // Agrega listas vacías para representar las columnas
		}
	}

	public void placeChip(char chip, int column) {
		if (column < 0 || column >= width) {
			throw new RuntimeException(ErrorColumnOutOfBounds);

		} else {
			List<Character> targetColumn = board.get(column);
			if (targetColumn.size() < height) {
				// Agregar el chip a la columna
				targetColumn.add(chip);
			} else {
				throw new RuntimeException(ErrorFullColumn);
			}
		}

	}

	public String show() {

		StringBuilder boardString = new StringBuilder();

		for (int row = height - 1; row >= 0; row--) {
			for (int col = 0; col < width; col++) {
				List<Character> column = board.get(col);
				if (column.size() > row) {
					boardString.append(column.get(row)).append(" ");
				} else {
					boardString.append("- "); // Carácter para indicar celda vacía
				}
			}
			boardString.append("\n"); // Nueva línea para la siguiente fila
		}

		return boardString.toString();
	}

	public String winner() {
		return gameStatus.statusOfGame();
	}

	public boolean finished() {
		return gameStatus.finished();
	}

	// GANAR EL JUEGO

	public boolean checkWin(char chip) {
		return winVariants.checkWin(chip, this);
	}

	public boolean checkTie() {

		for (List<Character> column : board) {
			if (column.size() < height) {
				return false; // Si alguna columna no está llena, el tablero no está lleno
			}
		}
		return true; // Si todas las columnas están llenas, el tablero está lleno y hay un empate
	}

	// ESTRATEGIAS PARA GANAR EL JUEGO
	public boolean checkVerticalWin(char player) {

		int consecutiveCount = 0;

		for (int col = 0; col < width; col++) {
			List<Character> column = board.get(col);
			consecutiveCount = 0;

			for (char chip : column) {
				if (chip == player) {
					consecutiveCount++;
					if (consecutiveCount >= 4) {
						return true; // Cuatro fichas consecutivas encontradas verticalmente
					}
				} else {
					consecutiveCount = 0; // Reiniciar el contador si no hay una ficha del jugador actual
				}
			}
		}

		return false; // No se encontraron cuatro fichas consecutivas verticalmente
	}

	public boolean checkHorizontalWin(char player) {
		for (int row = 0; row < height; row++) {
			int consecutiveCount = 0;

			for (int col = 0; col < width; col++) {
				List<Character> column = board.get(col);
				if (column.size() > row && column.get(row) == player) {
					consecutiveCount++;
					if (consecutiveCount >= 4) {
						return true; // Cuatro fichas consecutivas encontradas horizontalmente
					}
				} else {
					consecutiveCount = 0; // Reiniciar el contador si no hay una ficha del jugador actual
				}
			}
		}

		return false; // No se encontraron cuatro fichas consecutivas horizontalmente
	}

	public boolean checkDescendingDiagonalWin(char player) {
		for (int row = 0; row <= height - 4; row++) {
			for (int col = 0; col <= width - 4; col++) {
				boolean hasDiagonalWin = true;
				for (int i = 0; i < 4; i++) {
					if (board.get(col + i).size() <= row + i || board.get(col + i).get(row + i) != player) {
						hasDiagonalWin = false;
						break;
					}
				}
				if (hasDiagonalWin) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkAscendingDiagonalWin(char player) {
		for (int row = 3; row < height; row++) {
			for (int col = 0; col <= width - 4; col++) {
				boolean hasDiagonalWin = true;
				for (int i = 0; i < 4; i++) {
					if (board.get(col + i).size() <= row - i || board.get(col + i).get(row - i) != player) {
						hasDiagonalWin = false;
						break;
					}
				}
				if (hasDiagonalWin) {
					return true;
				}
			}
		}
		return false;
	}

	// FUNCION EXTRA

	private void initializeWinningVariant(char winVariant) {
		winVariants = winningVariantsList.stream().filter(variant -> variant.canHandle(winVariant)).findFirst()
										 .orElseThrow(() -> new RuntimeException(
												 	"No variant can handle the provided winVariant character: " + winVariant));
	}

	private void updateGameStatus(char playedChip) {
		gameContext.updateContextAfterPlay(playedChip);
		gameContext.setWinner(checkWin(playedChip));
		gameContext.setTie(checkTie());
		gameStatus = gameStateManager.getNextState(gameContext);
	}

}
