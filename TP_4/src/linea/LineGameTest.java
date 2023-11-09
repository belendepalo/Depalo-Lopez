package linea;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LineGameTest {
	@Test
	public void test00_RedAlwaysStarts() {
		LineGame game = new LineGame(7, 6, 'C');
		assertTrue(game.isRedsTurn());
		assertFalse(game.isBluesTurn());
	}

	@Test
	public void test01_AfterRedPlaysIsBlueTurn() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		assertFalse(game.isRedsTurn());
		assertTrue(game.isBluesTurn());

	}

	@Test
	public void test02_ErrorIsThrownAfterRedTriesToPlayTwice() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		try {
			game.playRedAt(3);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("It's Blue's Turn!", e.getMessage());
		}

	}

	@Test
	public void test03_ErrorIsThrownAfterBlueTriesToPlayTwice() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		try {
			game.playBlueAt(3);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("It's Red's Turn!", e.getMessage());
		}

	}

	@Test
	public void test04_RedsPlayIsValid() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		assertTrue(game.show().contains("R"));

	}

	@Test
	public void test05_BluesPlayIsValid() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		assertTrue(game.show().contains("B"));

	}

	@Test
	public void test06_RedsTriesToPlayInAColumnThatIsNotInTheGame() {
		LineGame game = new LineGame(7, 6, 'C');
		try {
			game.playRedAt(0);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals(LineGame.ErrorColumnOutOfBounds, e.getMessage());
		}

	}

	@Test
	public void test07_BluesTriesToPlayInAColumnThatIsNotInTheGame() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		try {
			game.playBlueAt(0);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals(LineGame.ErrorColumnOutOfBounds, e.getMessage());
		}

	}

	@Test
	public void test08_RedsAndBluesPlaysAreValidAndTheGameIsStillGoing() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		assertTrue(game.show().contains("R"));
		assertTrue(game.show().contains("B"));
		assertEquals("The game is still ongoing.", game.winner());

	}

	@Test
	public void test09_ErrorIsThrownAfterRedsTryToPlayWhenColumnIsFull() {
		LineGame game = new LineGame(7, 6, 'C');
		for (int i = 0; i < 3; i++) {
			game.playRedAt(3);
			game.playBlueAt(3);
		}

		try {
			game.playRedAt(3);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("Column is full!", e.getMessage());
		}
	}

	@Test
	public void test10_RedWinsHorizontallyWithWinningVariantA() {
		LineGame game = new LineGame(7, 6, 'A');
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals("You won the game!", game.winner());

	}

	@Test
	public void test11_BlueWinsVerticallyWithWinningVariantA() {
		LineGame game = new LineGame(7, 6, 'A');
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		assertTrue(game.finished());
		assertEquals("You won the game!", game.winner());

	}

	@Test
	public void test12_RedWinsInDiagonalWithWinningVariantB() {
		LineGame game = new LineGame(7, 6, 'B');
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(6);
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals("You won the game!", game.winner());

	}

	@Test
	public void test13_RedWinsHorizontallyWithWinningVariantC() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals("You won the game!", game.winner());

	}

	@Test
	public void test14_BlueWinsVerticallyWithWinningVariantC() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		assertTrue(game.finished());
		assertEquals("You won the game!", game.winner());

	}

	@Test
	public void test15_RedWinsInDiagonalWithWinningVariantC() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(6);
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals("You won the game!", game.winner());

	}

	@Test
	public void test16_GameEndedInATie() {
		LineGame game = new LineGame(3, 3, 'C');
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(3);
		game.playRedAt(3);
		assertTrue(game.finished());
		// assertEquals("The game ended in a tie!", game.winner());
	}

	@Test
	public void test17_GameEndedButRedTriesToPlay() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		assertTrue(game.finished());
		try {
			game.playRedAt(3);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("Game Over!", e.getMessage());
		}
	}

	@Test
	public void test18_GameEndedButBlueTriesToPlay() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(1);
		assertTrue(game.finished());
		try {
			game.playBlueAt(2);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("Game Over!", e.getMessage());
		}
	}
}
