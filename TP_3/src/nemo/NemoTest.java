package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class NemoTest {
	private Nemo nemo;

	@BeforeEach
	public void setUp() {
		this.nemo = new Nemo();
	}

	@Test
	public void test00_NewNemoHasDefaultValues() {
		assertEquals(0, nemo.coordinate.x);
		assertEquals(0, nemo.coordinate.y);
		assertEquals(0, nemo.coordinate.z);
		assertTrue(nemo.getDirection() instanceof North);
		assertTrue(nemo.getCurrentDepth() instanceof Surface);
	}

	@Test
	public void test01_NemoDescendsOneUnit() {
		executeCommandsAndTestCoordinates("d", 0, 0, -1);
	}

	@Test
	public void test02_NemoAscendsOneUnit() {
		executeCommandsAndTestCoordinates("du", 0, 0, 0);
	}

	@Test
	public void test03_NemoMovesForwardOneUnit() {
		executeCommandsAndTestCoordinates("f", 0, 1, 0);
	}

	@Test
	public void test04_NemoTurns90DegreesRight() {
		executeCommandAndTestDirection("r", new East());
	}

	@Test
	public void test05_NemoTurns90DegreesLeft() {
		executeCommandAndTestDirection("l", new West());
	}

	@Test
	public void test06_NemoReleasesCapsuleInSurface() {
		executeCommandAndTestDepths("m", new Surface());

	}

	@Test
	public void test07_CoordinatesAreConstantlyUpdated() {
		executeCommandsAndTestCoordinates("ff", 0, 2, 0);
	}

	@Test
	public void test08_DirectionIsConstantlyUpdated() {
		executeCommandAndTestDirection("ll", new South());
	}

	@Test
	public void test09_ExecuteMultipleSameCommands() {
		executeCommandsAndTestCoordinates("ffffffff", 0, 8, 0);
	}

	@Test
	public void test10_ExecuteMultipleDifferentCommands() {
		executeCommandsAndTestCoordinates("fdddduffuu", 0, 3, -1);
	}

	@Test
	public void test11_NemoReleasesCapsuleInDepthEndMarker() {
		executeCommandAndTestDepths("dm", new ShallowWater());
		assertTrue(nemo.getCurrentDepth().canReleaseCapsule());
	}

	@Test
	public void test12_CannotReleaseCapsuleInDeepWater() {
		executeCommandAndTestDepths("dddd", new DeepWater(null));
		assertThrowsLike(DeepWater.nemoHasBeenDestroyed, () -> nemo.executeCommands("m"));	    
	}

	@Test
	public void test13_NemoGoesToDeepWaterAndThenGoesToSurfaceToReleaseCapsule() {
		executeCommandAndTestDepths("dddduuuum", new Surface());
		assertTrue(nemo.getCurrentDepth().canReleaseCapsule());
	}

	@Test
	public void test14_NemoGoesToDeepWaterAndThenGoesToDepthEndMarkerToReleaseCapsule() {
		executeCommandAndTestDepths("ddum", new ShallowWater());
		assertTrue(nemo.getCurrentDepth().canReleaseCapsule());
	}

	@Test
	public void test15_NemoReleasesCapsuleInSurfaceAndShallowWaterButNotInDeepWater() {
		executeCommandAndTestDepths("m", new Surface());
		assertTrue(nemo.getCurrentDepth().canReleaseCapsule());
		executeCommandAndTestDepths("dm", new ShallowWater());
		assertTrue(nemo.getCurrentDepth().canReleaseCapsule());
		assertThrowsLike(DeepWater.nemoHasBeenDestroyed, () -> nemo.executeCommands("dm"));	    
	}

	private void executeCommandsAndTestCoordinates(String commands, int x, int y, int z) {
		nemo.executeCommands(commands);
		assertEquals(x, nemo.coordinate.x);
		assertEquals(y, nemo.coordinate.y);
		assertEquals(z, nemo.coordinate.z);
	}

	private void executeCommandAndTestDirection(String commands, CardinalDirection expectedDirection) {
		nemo.executeCommands(commands);
		assertTrue(expectedDirection.getClass().isInstance(nemo.getDirection()));
	}

	private void executeCommandAndTestDepths(String commands, Depth depth) {
		nemo.executeCommands(commands);
		assertTrue(depth.getClass().isInstance(nemo.getCurrentDepth()));
	}

	private void assertThrowsLike(String message, Executable executable) {
		assertEquals(message, assertThrows(Exception.class, executable).getMessage());
	}

}
