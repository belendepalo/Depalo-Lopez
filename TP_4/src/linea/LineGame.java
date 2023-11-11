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

	private List<WinVariants> winningVariantsList = Arrays.asList(new WinVariantA(), new WinVariantB(),
			new WinVariantC());
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
			return (column.size() > row) ? column.get(row) + " " : "- ";
		}).collect(Collectors.joining()) + "\n").collect(Collectors.collectingAndThen(Collectors.toList(), lines -> {
			Collections.reverse(lines);
			return String.join("", lines);
		}));
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
		return IntStream.range(0, width).anyMatch(col -> {
			List<Character> column = board.get(col);
			return IntStream.range(0, column.size()).filter(i -> column.get(i) == player)
					.mapToObj(i -> IntStream.rangeClosed(i, Math.min(i + 3, column.size() - 1)).mapToObj(column::get)
							.collect(Collectors.toList()))
					.anyMatch(subList -> isWinningSequence(subList, player));
		});
	}

	private boolean isWinningSequence(List<Character> sequence, char player) {
		return sequence.size() == 4 && sequence.stream().allMatch(c -> c == player);
	}

	public boolean checkHorizontalWin(char player) {
		return IntStream.range(0, height).anyMatch(row -> IntStream.range(0, width - 3).anyMatch(col -> IntStream
				.range(col, col + 4).allMatch(c -> board.get(c).size() > row && board.get(c).get(row) == player)));
	}

	public boolean checkDescendingDiagonalWin(char player) {
		return IntStream.range(0, height - 3)
				.anyMatch(row -> IntStream.range(0, width - 3).anyMatch(col -> IntStream.range(0, 4).allMatch(
						i -> board.get(col + i).size() > row + i && board.get(col + i).get(row + i) == player)));
	}

	public boolean checkAscendingDiagonalWin(char player) {
		return IntStream.range(3, height)
				.anyMatch(row -> IntStream.range(0, width - 3).anyMatch(col -> IntStream.range(0, 4).allMatch(
						i -> board.get(col + i).size() > row - i && board.get(col + i).get(row - i) == player)));
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
