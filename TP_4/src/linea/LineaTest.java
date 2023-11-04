package linea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineaTest {

	private static String ErrorItsRedsTurn = "It's Red's Turn!";
	private static String ErrorItsBluesTurn = "It's Blue's Turn!";
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
		playTurn(1, 'R');
		assertFalse(game.isRedsTurn());
		assertTrue(game.isBluesTurn());
	}

	@Test
	public void test02_ErrorIsThrownAfterRedTriesToPlayTwice() {
		playTurn(1, 'R');
		playTurnExpectError(3, 'R', ErrorItsBluesTurn);
	}

	@Test
	public void test03_ErrorIsThrownAfterBlueTriesToPlayTwice() {
		playTurn(1, 'R');
		playTurn(2, 'B');
		playTurnExpectError(3, 'B', ErrorItsRedsTurn);
	}

	@Test
	public void test04_RedsPlayIsValid() {
		playTurn(1, 'R');
		assertTrue(game.show().contains("R"));
	}

	@Test
	public void test05_BluesPlayIsValid() {
		playTurn(1, 'R');
		playTurn(2, 'B');
		assertTrue(game.show().contains("B"));
	}

	@Test
	public void test06_RedsTriesToPlayInAColumnThatIsNotInTheGame() {
		assertColumnOutOfBounds(0);
	}

	@Test
	public void test07_BluesTriesToPlayInAColumnThatIsNotInTheGame() {
		assertColumnOutOfBounds(8); 
	}

	@Test
	public void test08_RedsAndBluesPlaysAreValidAndTheGameIsStillGoing() {
		playTurn(1, 'R');
		playTurn(2, 'B');
		assertGameOngoing();
	}

	@Test
	public void test09_ErrorIsThrownAfterRedsTryToPlayWhenColumnIsFull() {
		fillColumn(3, 6);
		playTurnExpectError(3, 'R', "Column is full!");
	}

	@Test
	public void test10_RedWinsHorizontally() {
		playTurn(1, 'R');
		playTurn(1, 'B');
		playTurn(2, 'R');
		playTurn(1, 'B');
		playTurn(3, 'R');
		playTurn(1, 'B');
		playTurn(4, 'R');
		assertWinner("Red is the winner!");
	}

	@Test
	public void test11_BlueWinsVertically() {
		playTurn(2, 'R');
		playTurn(1, 'B');
		playTurn(3, 'R');
		playTurn(1, 'B');
		playTurn(4, 'R');
		playTurn(1, 'B');
		playTurn(2, 'R');
		playTurn(1, 'B');
		assertWinner("Blue is the winner!");
	}

	@Test
	public void test12_RedWinsInDiagonal() {
		playTurn(1, 'R');
		playTurn(2, 'B');
		playTurn(2, 'R');
		playTurn(3, 'B');
		playTurn(3, 'R');
		playTurn(4, 'B');
		playTurn(3, 'R');
		playTurn(4, 'B');
		playTurn(4, 'R');
		playTurn(5, 'B');
		playTurn(4, 'R');
		assertWinner("Red is the winner!");
	}

	@Test
	public void test13_GameEndedInATie() {
		game = new Linea(3, 3, 'C');
		playTurn(1, 'R');
		playTurn(1, 'B');
		playTurn(1, 'R');
		playTurn(2, 'B');
		playTurn(2, 'R');
		playTurn(2, 'B');
		playTurn(3, 'R');
		playTurn(3, 'B');
		playTurn(3, 'R');
		assertWinner("The game ended in a tie!");
	}

	@Test
	public void test14_PlayAfterGameIsFinishedThrowsException() {
		playTurn(1, 'R');
		playTurn(1, 'B');
		playTurn(2, 'R');
		playTurn(2, 'B');
		playTurn(3, 'R');
		playTurn(3, 'B');
		playTurn(4, 'R'); 
		playTurnExpectError(5, 'R', "Game is already finished!");
	}

	private void playTurn(int column, char player) {
		if (player == 'R') {
			game.playRedAt(column);
		} else {
			game.playBlueAt(column);
		}
	}

	private void playTurnExpectError(int column, char player, String expectedMessage) {
		Exception exception = assertThrows(RuntimeException.class, () -> playTurn(column, player));
		assertEquals(expectedMessage, exception.getMessage());
	}

	private void fillColumn(int column, int height) {
		IntStream.range(0, height).forEach(i -> playTurn(column, i % 2 == 0 ? 'R' : 'B'));

	}

	private void assertWinner(String expectedWinner) {
		assertTrue(game.finished());
		assertEquals(expectedWinner, game.winner());
	}

	private void assertColumnOutOfBounds(int column) {
		Exception exception = assertThrows(RuntimeException.class, () -> game.playRedAt(column));
		assertEquals("Column out of bounds!", exception.getMessage());
	}

	private void assertGameOngoing() {
		assertFalse(game.finished());
		assertEquals("The game is still ongoing.", game.winner());
	}
}
