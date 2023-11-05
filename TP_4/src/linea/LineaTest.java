package linea;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LineaTest {
	@Test
	public void test00_RedAlwaysStarts() {
		Linea game = new Linea(7, 6, 'C');
		assertTrue(game.gameStatus instanceof RedsTurn);
		assertFalse(game.gameStatus instanceof BluesTurn);
	}

	@Test
	public void test01_AfterRedPlaysIsBlueTurn() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(1);
		assertFalse(game.gameStatus instanceof RedsTurn);
		assertTrue(game.gameStatus instanceof BluesTurn);

	}

	@Test
	public void test02_ErrorIsThrownAfterRedTriesToPlayTwice() {
		Linea game = new Linea(7, 6, 'C');
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
		Linea game = new Linea(7, 6, 'C');
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
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(1);
		assertTrue(game.show().contains("R"));

	}

	@Test
	public void test05_BluesPlayIsValid() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		assertTrue(game.show().contains("B"));

	}

	@Test
	public void test06_RedsTriesToPlayInAColumnThatIsNotInTheGame() {
		Linea game = new Linea(7, 6, 'C');
		try {
			game.playRedAt(0);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("Column out of bounds!", e.getMessage());
		}

	}

	@Test
	public void test07_BluesTriesToPlayInAColumnThatIsNotInTheGame() {
		Linea game = new Linea(7, 6, 'C');
		try {
			game.playRedAt(0);
			fail("Se esperaba una excepción, pero no se lanzó.");
		} catch (RuntimeException e) {
			assertEquals("Column out of bounds!", e.getMessage());
		}

	}

	@Test
	public void test08_RedsAndBluesPlaysAreValidAndTheGameIsStillGoing() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		assertTrue(game.show().contains("R"));
		assertTrue(game.show().contains("B"));
		assertEquals("The game is still ongoing.", game.winner());

	}

	@Test
	public void test09_ErrorIsThrownAfterRedsTryToPlayWhenColumnIsFull() {
		Linea game = new Linea(7, 6, 'C');
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
	public void test10_RedWinsHorizontally() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals("Red is the winner!", game.winner());

	}

	@Test
	public void test11_BlueWinsVertically() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(1);
		game.playRedAt(4);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(1);
		assertTrue(game.finished());
		assertEquals("Blue is the winner!", game.winner());

	}

	@Test
	public void test12_RedWinsInDiagonal() {
		Linea game = new Linea(7, 6, 'C');
		game.playRedAt(1);
		game.playBlueAt(2);
		game.playRedAt(2);
		game.playBlueAt(3);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(3);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(5);
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals("Red is the winner!", game.winner());

	}

	@Test
	public void test13_GameEndedInATie() {
		Linea game = new Linea(3, 3, 'C');
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
		assertEquals("The game ended in a tie!", game.winner());
	}
	
	@Test
	public void test14_GameEndedButRedTriesToPlay() {
		Linea game = new Linea(7, 6, 'C');
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
}
