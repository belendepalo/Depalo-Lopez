package linea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineaTest {

	private Linea game;

	@BeforeEach
	public void setUp() {
		game = new Linea(7, 6, 'C');
	}

	@Test
	public void test00_RedAlwaysStarts() {
		assertTrue(game.isRedsTurn());
		assertFalse(game.isBluesTurn());
	}

	@Test
	public void test01_AfterRedPlaysIsBlueTurn() {
		game.playRedAt(1);
		assertFalse(game.isRedsTurn());
		assertTrue(game.isBluesTurn());

	}

	@Test
	public void test02_ErrorIsThrownAfterRedTriesToPlayTwice() {
		game.playRedAt(1);
		Exception exception = assertThrows(RuntimeException.class, () -> game.playRedAt(3));
		assertEquals("It's Blue's Turn!", exception.getMessage());
	}

	@Test
	public void test03_ErrorIsThrownAfterBlueTriesToPlayTwice() {
		game.playRedAt(1);
		game.playBlueAt(2);
		Exception exception = assertThrows(RuntimeException.class, () -> game.playBlueAt(3));
		assertEquals("It's Red's Turn!", exception.getMessage());
	}

	@Test
	public void test04_RedsPlayIsValid() {
		game.playRedAt(1);
		assertTrue(game.show().contains("R"));

	}

	@Test
	public void test05_BluesPlayIsValid() {
		game.playRedAt(1);
		game.playBlueAt(2);
		assertTrue(game.show().contains("B"));

	}

	@Test
	public void test06_RedsTriesToPlayInAColumnThatIsNotInTheGame() {
		Exception exception = assertThrows(RuntimeException.class, () -> game.playRedAt(0));
		assertEquals("Column out of bounds!", exception.getMessage());
	}

	@Test
	public void test07_BluesTriesToPlayInAColumnThatIsNotInTheGame() {
		Exception exception = assertThrows(RuntimeException.class, () -> game.playBlueAt(0));
		assertEquals("Column out of bounds!", exception.getMessage());
	}

	@Test
	public void test08_RedsAndBluesPlaysAreValidAndTheGameIsStillGoing() {
		game.playRedAt(1);
		game.playBlueAt(2);
		assertTrue(game.show().contains("R"));
		assertTrue(game.show().contains("B"));
		assertEquals("The game is still ongoing.", game.winner());

	}

	@Test
	public void test09_ErrorIsThrownAfterRedsTryToPlayWhenColumnIsFull() {

		for (int i = 0; i < 3; i++) {
			game.playRedAt(3);
			game.playBlueAt(3);
		}
		Exception exception = assertThrows(RuntimeException.class, () -> game.playRedAt(3));
		assertEquals("Column is full!", exception.getMessage());
	}

	@Test
	public void test10_RedWinsHorizontally() {
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
	public void test14_PlayAfterGameIsFinishedThrowsException() {
		game.playRedAt(1);
		game.playBlueAt(1);
		game.playRedAt(2);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(3);
		game.playRedAt(4);

		Exception exception = assertThrows(RuntimeException.class, () -> game.playRedAt(5));
		assertEquals("Game is already finished!", exception.getMessage());
	}

}
