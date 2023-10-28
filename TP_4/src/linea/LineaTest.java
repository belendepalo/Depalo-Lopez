package linea;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class LineaTest {

	@Test
	public void testValidPlay() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(3);
		assertTrue(game.show().contains("R"));
	}

	@Test
	public void testInvalidPlay() {
		Linea game = new Linea(7, 6, 'C');
		for (int i = 0; i < 6; i++) {
			game.playRedAt(3);
		}
		assertThrows(RuntimeException.class, () -> game.playBlueAt(3));
	}

	@Test
	public void testWinHorizontal() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(2);
		game.playRedAt(3);
		game.playRedAt(4);
		game.playRedAt(5);
		assertTrue(game.finished());
	}

	@Test
	public void testWinVertical() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(3);
		game.playRedAt(3);
		game.playRedAt(3);
		game.playRedAt(3);
		assertTrue(game.finished());
	}

	@Test
	public void testWinDiagonal() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(5);
		game.playBlueAt(5);
		game.playRedAt(5);
		game.playBlueAt(6);
		game.playBlueAt(6);
		game.playBlueAt(6);
		game.playRedAt(6);
		assertTrue(game.finished());
	}

	@Test
	public void testTie() {
		Linea game = new Linea(3, 3, 'C');
		for (int i = 1; i < 4; i++) {
			game.playRedAt(i);
			game.playBlueAt(i);
			game.playRedAt(i);
		}
		assertTrue(game.finished());
	}

	@Test
	public void testAlternateTurns() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(3);
		game.playBlueAt(4);
		String board = game.show();
		assertTrue(board.contains("R") && board.contains("B"));
	}
}
