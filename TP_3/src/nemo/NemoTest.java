package nemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NemoTest {
	private Nemo nemo;
	
	@BeforeEach
	public void setUp(){
		this.nemo = new Nemo();
	}
	
	@Test
	public void test00_NewNemoHasDefaultValues() {
		assertEquals(0, nemo.coordinate.x);
		assertEquals(0, nemo.coordinate.y);
		assertEquals(0, nemo.coordinate.z);
		assertTrue(nemo.direction instanceof North);
		assertTrue(nemo.currentDepth instanceof Surface);
	}

	@Test
	public void test01_NemoDescendsOneUnit() {
		nemo.executeCommands("d");
		assertEquals(-1, nemo.coordinate.z);
	}

	@Test
	public void test02_NemoAscendsOneUnit() {
		nemo.executeCommands("u");
		assertEquals(1, nemo.coordinate.z);
	}

	@Test
	public void test03_NemoMovesForwardOneUnit() {
		nemo.executeCommands("f");
		assertEquals(1, nemo.coordinate.y);
	}

	@Test
	public void test04_NemoTurns90DegreesRight() {
		nemo.executeCommands("r");
		assertTrue(nemo.direction instanceof East);
	}

	@Test
	public void test05_NemoTurns90DegreesLeft() {
		nemo.executeCommands("l");
		assertTrue(nemo.direction instanceof West);
	}

	@Test
	public void test06_CoordinatesAreConstantlyUpdated() {
		nemo.executeCommands("ff");
		assertEquals(2, nemo.coordinate.y);
	}

	@Test
	public void test07_DirectionIsConstantlyUpdated() {
		nemo.executeCommands("ll");
		assertTrue(nemo.direction instanceof South);
	}

	@Test
	public void test08_ExecuteMultipleSameCommands() {
		nemo.executeCommands("ffffffff");
		assertEquals(8, nemo.coordinate.y);
	}

	@Test
	public void test09_ExecuteMultipleDifferentCommands() {
		nemo.executeCommands("fdddduffuu");
		assertEquals(3, nemo.coordinate.y);
		assertEquals(-1, nemo.coordinate.z);
	}

	@Test
	public void test10_CannotReleaseCapsuleInDeepWater() {
		nemo.executeCommands("dddd");
		assertTrue(nemo.currentDepth instanceof DeepWater);
		Exception exception = assertThrows(RuntimeException.class, () -> nemo.executeCommands("m"));
		assertEquals("Nemo has been destroyed", exception.getMessage());
	}
}
