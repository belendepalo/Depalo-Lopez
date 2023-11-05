package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Linea {

	private static final String ERROR_COLUMN_OUT_OF_BOUNDS = "Column out of bounds!";
	private List<List<Character>> board;
	private WinningVariants winVariant;
	private GameStatus gameStatus;
	private int width;
	private int height;

	public Linea(int width, int height, char winVariantChar) {
		this.width = width;
		this.height = height;
		this.winVariant = determineWinningVariant(winVariantChar);
		this.gameStatus = new OngoingGame(new RedsTurn(), winVariant);
		initializeBoard();
	}

	public void playRedAt(int col) {
		if (isColumnOutOfBounds(col)) {
			throw new RuntimeException(ERROR_COLUMN_OUT_OF_BOUNDS);
		}
		gameStatus.playRedAt(col, this.board);
		gameStatus = gameStatus.checkAndUpdateGameState(this.board);
	}

	public void playBlueAt(int col) {
		if (isColumnOutOfBounds(col)) {
			throw new RuntimeException(ERROR_COLUMN_OUT_OF_BOUNDS);
		}
		gameStatus.playBlueAt(col, this.board);
		gameStatus = gameStatus.checkAndUpdateGameState(this.board);
	}

	public String show() {
		return gameStatus.show(this.board);
	}

	public boolean finished() {
		return gameStatus.finished(this.board);
	}

	private boolean isColumnOutOfBounds(int col) {
		return col < 0 || col >= width;
	}

	private void initializeBoard() {
		this.board = new ArrayList<>();
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			List<Character> row = new ArrayList<>();
			for (int colIndex = 0; colIndex < width; colIndex++) {
				row.add(' ');
			}
			this.board.add(row);
		}
	}

	private WinningVariants determineWinningVariant(char winVariantChar) {
		List<WinningVariants> variants = Arrays.asList(new VerticalAndHorizontalVariant(), new DiagonalOnlyVariant(),
				new ClassicalVariant());

		return variants.stream()
				.filter(variant -> variant.canHandle(winVariantChar))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Variable de juego invalida."));
	}
}
