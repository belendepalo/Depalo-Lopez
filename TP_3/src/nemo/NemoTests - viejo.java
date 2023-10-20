package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class NemoTests {
	private Nemo nemo;

	@Before
	public void setup() {
		nemo = new Nemo();
	}

	@Test
	public void test01_InitialPositionAndOrientation() {
		assertEquals(0, nemo.getXPosition());
		assertEquals(0, nemo.getYPosition());
		assertEquals(0, nemo.getDepth());
		assertEquals("NORTH", nemo.getCurrentDirection());
	}

	@Test
	public void test02_Descend() {
		nemo.executeCommand('d');
		assertEquals(-1, nemo.getDepth());
	}

	@Test
	public void test03_Ascend() {
		nemo.executeCommand('d');
		nemo.executeCommand('u');
		assertEquals(0, nemo.getDepth());
	}

	@Test
	public void test04_MoveForwardFacingNorth() {
		nemo.executeCommand('f');
		assertEquals(0, nemo.getXPosition());
		assertEquals(1, nemo.getYPosition());
	}

	@Test
	public void test05_RotateLeftFromNorth() {
		nemo.executeCommand('l');
		assertEquals("WEST", nemo.getCurrentDirection());
	}

	@Test
	public void test06_RotateRightFromNorth() {
		nemo.executeCommand('r');
		assertEquals("EAST", nemo.getCurrentDirection());
	}

	@Test
	public void test07_MoveForwardFacingEast() {
		nemo.executeCommand('r');
		nemo.executeCommand('f');
		assertEquals(1, nemo.getXPosition());
		assertEquals(0, nemo.getYPosition());
	}

	@Test
	public void test08_MoveForwardFacingSouth() {
		nemo.executeCommand('r');
		nemo.executeCommand('r');
		nemo.executeCommand('f');
		assertEquals(0, nemo.getXPosition());
		assertEquals(-1, nemo.getYPosition());
	}

	@Test
	public void test09_MoveForwardFacingWest() {
		nemo.executeCommand('l');
		nemo.executeCommand('f');
		assertEquals(-1, nemo.getXPosition());
		assertEquals(0, nemo.getYPosition());
	}

	@Test
	public void test16_ReleaseCapsuleOnSurface() {
		nemo.executeCommand('m');
		assertFalse(nemo.isDestroyed());
	}

	@Test
	public void test17_ReleaseCapsuleAtFirstLevel() {
		nemo.executeCommand('d');
		nemo.executeCommand('m');
		assertFalse(nemo.isDestroyed());
	}

	@Test
	public void test18_ReleaseCapsuleAtGreaterDepth() {
		nemo.executeCommand('d');
		nemo.executeCommand('d');
		nemo.executeCommand('m');
		assertTrue(nemo.isDestroyed());
	}

	@Test
	public void test19_Destroyed() {
		nemo.executeCommands("ddddm");
		assertTrue(nemo.isDestroyed());
	}

	@Test
	public void test20_DestroyedCannotMove() {
		nemo.executeCommands("ddddm");
		Exception exception = assertThrows(RuntimeException.class, () -> nemo.executeCommand('f'));
		assertEquals("Nemo has been destroyed.", exception.getMessage());
		assertEquals(-4, nemo.getDepth());
		assertEquals(0, nemo.getXPosition());
		assertEquals(0, nemo.getYPosition());
	}

	@Test
	public void test21_RotateAfterDestroyed() {
		nemo.executeCommands("ddddm");
		Exception exception = assertThrows(RuntimeException.class, () -> nemo.executeCommand('r'));
		assertEquals("Nemo has been destroyed.", exception.getMessage());
		assertEquals("NORTH", nemo.getCurrentDirection());
	}

	@Test
	public void test22_DescendAfterDestroyed() {
		nemo.executeCommands("ddddm");
		Exception exception = assertThrows(RuntimeException.class, () -> nemo.executeCommand('d'));
		assertEquals("Nemo has been destroyed.", exception.getMessage());
		assertEquals(-4, nemo.getDepth());
	}

	@Test
	public void test23_AscendAfterDestroyed() {
		nemo.executeCommands("ddddm");
		Exception exception = assertThrows(RuntimeException.class, () -> nemo.executeCommand('u'));
		assertEquals("Nemo has been destroyed.", exception.getMessage());
		assertEquals(-4, nemo.getDepth());
	}

	@Test
	public void test24_ReleaseCapsuleAfterDestroyed() {
		nemo.executeCommands("ddddm");
		Exception exception = assertThrows(RuntimeException.class, () -> nemo.executeCommand('m'));
		assertEquals("Nemo has been destroyed.", exception.getMessage());
		assertTrue(nemo.isCapsuleLaunched());
	}

	@Test
	public void test25_MultiDirectionalMovement() {
		nemo.executeCommands("rfflff");
		assertEquals(2, nemo.getXPosition());
		assertEquals(2, nemo.getYPosition());
	}

	@Test
	public void test26_ComplexMovements() {
		nemo.executeCommands("ffrfflff");
		assertEquals(2, nemo.getXPosition());
		assertEquals(4, nemo.getYPosition());
		assertEquals("NORTH", nemo.getCurrentDirection());
	}

	@Test
	public void test27_AscendBeyondSurface() {
		nemo.executeCommand('u');
		assertEquals(0, nemo.getDepth());
	}

	@Test
	public void test28_CapsuleReleaseStatus() {
		nemo.executeCommand('m');
		assertTrue(nemo.isCapsuleLaunched());
	}

	@Test
	public void test29_MultipleCapsuleReleases() {
		nemo.executeCommand('m');
		nemo.executeCommand('m');
		assertTrue(nemo.isCapsuleLaunched());
	}

	@Test
	public void test30_RepeatedRotation() {
		nemo.executeCommands("llll");
		assertEquals("NORTH", nemo.getCurrentDirection());
	}

	@Test
	public void test31_MovementAtMaxDepth() {
		nemo.executeCommands("ddddddddddflfrfr");
		assertEquals(-10, nemo.getDepth());
		assertEquals(-1, nemo.getXPosition());
		assertEquals(2, nemo.getYPosition());
	}

	@Test
	public void test32_RepeatedDescend() {
		nemo.executeCommands("dddddd");
		assertEquals(-6, nemo.getDepth());
	}

	@Test
	public void test33_RepeatedAscend() {
		nemo.executeCommands("uuuuuu");
		assertEquals(0, nemo.getDepth());
	}

	@Test
	public void test34_MultipleCapsuleReleaseAtValidDepths() {
		nemo.executeCommands("mdm");
		assertFalse(nemo.isDestroyed());
	}

	@Test
	public void test35_MoveInAllDirections() {
		nemo.executeCommands("frflf");
		assertEquals(1, nemo.getXPosition());
		assertEquals(2, nemo.getYPosition());
	}

	@Test
	public void test36_ComplexSequence() {
		try {
			nemo.executeCommands("ffrffudddmlff");
		} catch (RuntimeException e) {
			assertEquals("Nemo has been destroyed.", e.getMessage());
		}
		assertEquals(2, nemo.getXPosition());
		assertEquals(2, nemo.getYPosition());
		assertEquals(-3, nemo.getDepth());
		assertTrue(nemo.isCapsuleLaunched());
	}
}
