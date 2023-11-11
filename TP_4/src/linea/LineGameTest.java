package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LineGame {

	public static String ErrorFullColumn = "Column is full!";
	public static String ErrorColumnOutOfBounds = "Column is out of parameter!";
	public static String ErrorGameVariantNotFound = "No game variant can handle the provided winVariant character: ";
	
	private List<WinVariants> winningVariantsList = Arrays.asList(new WinVariantA(), new WinVariantB(), new WinVariantC());
	private List<List<Character>> board = new ArrayList<>();
	private GameState gameStatus = new RedsTurn();
	private GameStateManager gameManager;
	private WinVariants winVariants;
	private int height;
	private int width;

	public LineGame(int width, int height, char winVariant) {
		this.width = width;
		this.height = height;
		initializeBaseOfBoard();
		initializeWinningVariant(winVariant);
		this.gameManager = new GameStateManager('R');
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
		IntStream.range(0, width).forEach(i -> board.add(new ArrayList<>()));
	}

	public void placeChip(char chip, int column) {
		if (column < 0 || column >= width) {
			throw new RuntimeException(ErrorColumnOutOfBounds);

		} else {
			List<Character> targetColumn = board.get(column);
			if (targetColumn.size() < height) {
				targetColumn.add(chip);
			} else {
				throw new RuntimeException(ErrorFullColumn);
			}
		}

	}

	public String show() {
		  return IntStream.range(0, height).mapToObj(row -> IntStream.range(0, width).mapToObj(col -> {
			  List<Character> column = board.get(col);
			  return (column.size() > row) ? column.get(row) + " " : "- ";})
			     .collect(Collectors.joining()) + "\n").collect(Collectors.collectingAndThen(Collectors.toList(), lines -> {
			    	 Collections.reverse(lines);
			         return String.join("", lines); }));
	}

	public String winner() {
		return gameStatus.statusOfGame(gameManager);
	}

	public boolean finished() {
		return gameStatus.finished();
	}

	public boolean checkWin(char chip) {
		return winVariants.checkWin(chip, this);
	}

	public boolean checkTie() {
		return board.stream().allMatch(column -> column.size() >= height);
	}

	public boolean checkVerticalWin(char player) {

		int consecutiveCount = 0;

		for (int col = 0; col < width; col++) {
			List<Character> column = board.get(col);
			consecutiveCount = 0;

			for (char chip : column) {
				if (chip == player) {
					consecutiveCount++;
					if (consecutiveCount >= 4) {
						return true;
					}
				} else {
					consecutiveCount = 0;
				}
			}
		}

		return false;
	}

	public boolean checkHorizontalWin(char player) {
		for (int row = 0; row < height; row++) {
			int consecutiveCount = 0;

			for (int col = 0; col < width; col++) {
				List<Character> column = board.get(col);
				if (column.size() > row && column.get(row) == player) {
					consecutiveCount++;
					if (consecutiveCount >= 4) {
						return true;
					}
				} else {
					consecutiveCount = 0;
				}
			}
		}

		return false;
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

	private void initializeWinningVariant(char winVariant) {
		winVariants = winningVariantsList.stream().filter(variant -> variant.canHandle(winVariant)).findFirst()
										 .orElseThrow(() -> new RuntimeException(ErrorGameVariantNotFound + winVariant));
	}

	private void updateGameStatus(char playedChip) {
		gameManager.updateContextAfterPlay(playedChip);
		gameManager.setWinner(checkWin(playedChip));
		gameManager.setTie(checkTie());
		gameStatus = gameManager.getNextState(gameManager);
	}

}
