package linea;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class LineGameTest {
	private static String BWonTheGame = "B has won the game!";
	private static String RWonTheGame = "R has won the game!";
	
	@Test
	public void test00_RedAlwaysStarts() {
		LineGame game = new LineGame(7, 6, 'C');
		
		checkCurrentTurn(game.isRedsTurn(), game.isBluesTurn());
	}

	@Test
	public void test01_AfterRedPlaysIsBlueTurn() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		
		checkCurrentTurn(game.isBluesTurn(), game.isRedsTurn());
	}

	@Test
	public void test02_ErrorIsThrownAfterRedTriesToPlayTwice() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		
		assertThrowsLike(BluesTurn.ErrorBluesTurn, () -> game.playRedAt(3));
	}

	@Test
	public void test03_ErrorIsThrownAfterBlueTriesToPlayTwice() {
		LineGame game = new LineGame(7, 6, 'C');
		RedsAndBluesPlayVerticallyAndRecursively(game, 1);
		
		assertThrowsLike(RedsTurn.ErrorRedsTurn, () -> game.playBlueAt(3));
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
		RedsAndBluesPlayVerticallyAndRecursively(game, 1);
		
		assertTrue(game.show().contains("B"));
	}

	@Test
	public void test06_RedsTriesToPlayInAColumnThatIsNotInTheGame() {
		LineGame game = new LineGame(7, 6, 'C');
		
		assertThrowsLike(LineGame.ErrorColumnOutOfBounds, () -> game.playRedAt(0));
	}

	@Test
	public void test07_BluesTriesToPlayInAColumnThatIsNotInTheGame() {
		LineGame game = new LineGame(7, 6, 'C');
		game.playRedAt(1);
		
		assertThrowsLike(LineGame.ErrorColumnOutOfBounds, () -> game.playBlueAt(0));
	}

	@Test
	public void test08_RedsAndBluesPlaysAreValidAndTheGameIsStillGoing() {
		LineGame game = new LineGame(7, 6, 'C');
		RedsAndBluesPlayVerticallyAndRecursively(game, 1);
		
		assertTrue(game.show().contains("R"));
		assertTrue(game.show().contains("B"));
		assertEquals(GameState.GameStillGoing, game.winner());
	}

	@Test
	public void test09_ErrorIsThrownAfterRedsTryToPlayWhenColumnIsFull() {
	    LineGame game = new LineGame(7, 6, 'B');
	    RedsAndBluesPlayVerticallyAndRecursively(game, 6);
	    
		assertThrowsLike(LineGame.ErrorFullColumn, () -> game.playRedAt(1));
	}

	@Test
	public void test10_RedWinsHorizontallyWithWinningVariantA() {
	    LineGame game = new LineGame(7, 6, 'A');
	    RedsAndBluesPlayHorizonatallyAndRecursively(game, 3);
	    game.playRedAt(4);
	    
	    checkWhoWonTheGame(game.winner(), game);
	}
	
	@Test
	public void test11_BlueWinsHorizontallyWithWinningVariantA() {
		 LineGame game = new LineGame(7, 6, 'A');
		 RedsAndBluesPlayHorizonatallyAndRecursively(game, 3);
		 game.playRedAt(5);
		 game.playBlueAt(4);
		 game.playRedAt(5);
		 game.playBlueAt(4);
		 
		 checkWhoWonTheGame(game.winner(), game);
	}
	
	@Test
	public void test12_RedsWinsVerticallyWithWinningVariantA() {
		LineGame game = new LineGame(7, 6, 'A');
		RedsAndBluesPlayVerticallyAndRecursively(game, 3);
		game.playRedAt(1);
		
		checkWhoWonTheGame(RWonTheGame, game);
	}

	@Test
	public void test13_BlueWinsVerticallyWithWinningVariantA() {
		LineGame game = new LineGame(7, 6, 'A');
		RedsAndBluesPlayVerticallyAndRecursively(game, 3);
		game.playRedAt(5);
		game.playBlueAt(2);
		
		checkWhoWonTheGame(BWonTheGame, game);
	}

	@Test
	public void test14_RedWinsInDiagonalWithWinningVariantB() {
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
		
		checkWhoWonTheGame(RWonTheGame, game);
	}
	
	@Test
	public void test15_BlueWinsInDiagonalWithWinningVariantB() {
		LineGame game = new LineGame(7, 6, 'B');
		game.playRedAt(2);
		game.playBlueAt(1);
		game.playRedAt(3);
		game.playBlueAt(2);
		game.playRedAt(3);
		game.playBlueAt(3);
		game.playRedAt(4);
		game.playBlueAt(4);
		game.playRedAt(4);
		game.playBlueAt(4);
		
		checkWhoWonTheGame(BWonTheGame, game);
	}

	@Test
	public void test13_RedWinsHorizontallyWithWinningVariantC() {
		LineGame game = new LineGame(7, 6, 'C');
		RedsAndBluesPlayHorizonatallyAndRecursively(game, 3);
		game.playRedAt(4);
		
		checkWhoWonTheGame(RWonTheGame, game);
	}

	@Test
	public void test14_BlueWinsVerticallyWithWinningVariantC() {
		LineGame game = new LineGame(7, 6, 'C');
		RedsAndBluesPlayVerticallyAndRecursively(game, 3);
		game.playRedAt(3);
		game.playBlueAt(2);
		
		checkWhoWonTheGame(BWonTheGame, game);
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

		checkWhoWonTheGame(RWonTheGame, game);
	}
	
	@Test
	public void test16_GameStartsWithWrongWinningVariable() {
		assertThrowsLike(LineGame.ErrorGameVariantNotFound + 'H', () -> { new LineGame(7, 6, 'H'); });
	}

	@Test
	public void test17_GameEndedInATie() {
		LineGame game = new LineGame(2, 2, 'C');
		RedsAndBluesPlayVerticallyAndRecursively(game, 1);
		RedsAndBluesPlayVerticallyAndRecursively(game, 1);
		
		assertEquals(GameState.GameEndedTie, game.winner());
	}

	@Test
	public void test18_GameEndedButRedTriesToPlay() {
		LineGame game = new LineGame(7, 6, 'C');
		RedsAndBluesPlayHorizonatallyAndRecursively(game, 3);
	    game.playRedAt(4);
	    
		assertTrue(game.finished());
		assertThrowsLike(GameState.GameOver, () -> game.playRedAt(3));
	}

	@Test
	public void test19_GameEndedButBlueTriesToPlay() {
		LineGame game = new LineGame(7, 6, 'C');
		RedsAndBluesPlayVerticallyAndRecursively(game, 3);
	    game.playRedAt(5);
		game.playBlueAt(2);
		assertTrue(game.finished());
		assertThrowsLike(GameState.GameOver, () -> game.playBlueAt(2));

	}
	
	private void checkWhoWonTheGame(String winner, LineGame game) {
		assertTrue(game.finished());
		assertEquals(winner, game.winner());
	}
	
	private void RedsAndBluesPlayVerticallyAndRecursively(LineGame game, int roundsToPlay) {
		IntStream.range(0, roundsToPlay).forEach(round -> {
            game.playRedAt(1);
            game.playBlueAt(2);
        });
	}
	
	private void RedsAndBluesPlayHorizonatallyAndRecursively(LineGame game, int roundsToPlay) {
		IntStream.range(0, roundsToPlay).forEach(round -> {
            game.playRedAt(round + 1);
            game.playBlueAt(round + 1);
        });
	}
	
	private void checkCurrentTurn(boolean correctTurn, boolean incorrectTurn) {
		assertTrue(correctTurn);
		assertFalse(incorrectTurn);
	}
	private void assertThrowsLike(String expectedMessage, Executable executable) {
	    assertEquals(expectedMessage, assertThrows(RuntimeException.class, executable).getMessage());
	}
	
}
