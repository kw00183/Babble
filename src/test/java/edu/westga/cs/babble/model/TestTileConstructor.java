package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * JUnit test class for Tile model class
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileConstructor {
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void shouldNotAllowNonLetters() {
		String stringNumber = "1";
		char convertToChar = stringNumber.charAt(0);
		try {
			new Tile(convertToChar);
		} catch (IllegalArgumentException iae) {
			assertEquals("letter must be between A and Z", iae.getMessage());
		}
	}
	
	@Test
	public void shouldCreateOnePointTiles() {
		String stringLetters = "EAIONRTLSU";
		char convertToCharE = stringLetters.charAt(0);
		char convertToCharA = stringLetters.charAt(1);
		char convertToCharI = stringLetters.charAt(2);
		char convertToCharO = stringLetters.charAt(3);
		char convertToCharN = stringLetters.charAt(4);
		char convertToCharR = stringLetters.charAt(5);
		char convertToCharT = stringLetters.charAt(6);
		char convertToCharL = stringLetters.charAt(7);
		char convertToCharS = stringLetters.charAt(8);
		char convertToCharU = stringLetters.charAt(9);
		assertEquals("E", Character.toString(new Tile(convertToCharE).getLetter()));
		assertEquals("A", Character.toString(new Tile(convertToCharA).getLetter()));
		assertEquals("I", Character.toString(new Tile(convertToCharI).getLetter()));
		assertEquals("O", Character.toString(new Tile(convertToCharO).getLetter()));
		assertEquals("N", Character.toString(new Tile(convertToCharN).getLetter()));
		assertEquals("R", Character.toString(new Tile(convertToCharR).getLetter()));
		assertEquals("T", Character.toString(new Tile(convertToCharT).getLetter()));
		assertEquals("L", Character.toString(new Tile(convertToCharL).getLetter()));
		assertEquals("S", Character.toString(new Tile(convertToCharS).getLetter()));
		assertEquals("U", Character.toString(new Tile(convertToCharU).getLetter()));
		assertEquals(1, new Tile(convertToCharE).getPointValue());
		assertEquals(1, new Tile(convertToCharA).getPointValue());
		assertEquals(1, new Tile(convertToCharI).getPointValue());
		assertEquals(1, new Tile(convertToCharO).getPointValue());
		assertEquals(1, new Tile(convertToCharN).getPointValue());
		assertEquals(1, new Tile(convertToCharR).getPointValue());
		assertEquals(1, new Tile(convertToCharT).getPointValue());
		assertEquals(1, new Tile(convertToCharL).getPointValue());
		assertEquals(1, new Tile(convertToCharS).getPointValue());
		assertEquals(1, new Tile(convertToCharU).getPointValue());
	}
	
	@Test
	public void shouldCreateTwoPointTiles() {
		String stringLetters = "DG";
		char convertToCharD = stringLetters.charAt(0);
		char convertToCharG = stringLetters.charAt(1);
		assertEquals("D", Character.toString(new Tile(convertToCharD).getLetter()));
		assertEquals("G", Character.toString(new Tile(convertToCharG).getLetter()));
		assertEquals(2, new Tile(convertToCharD).getPointValue());
		assertEquals(2, new Tile(convertToCharG).getPointValue());
	}
	
	@Test
	public void shouldCreateThreePointTiles() {
		String stringLetters = "BCMP";
		char convertToCharB = stringLetters.charAt(0);
		char convertToCharC = stringLetters.charAt(1);
		char convertToCharM = stringLetters.charAt(2);
		char convertToCharP = stringLetters.charAt(3);
		assertEquals("B", Character.toString(new Tile(convertToCharB).getLetter()));
		assertEquals("C", Character.toString(new Tile(convertToCharC).getLetter()));
		assertEquals("M", Character.toString(new Tile(convertToCharM).getLetter()));
		assertEquals("P", Character.toString(new Tile(convertToCharP).getLetter()));
		assertEquals(3, new Tile(convertToCharB).getPointValue());
		assertEquals(3, new Tile(convertToCharC).getPointValue());
		assertEquals(3, new Tile(convertToCharM).getPointValue());
		assertEquals(3, new Tile(convertToCharP).getPointValue());
	}
	
	@Test
	public void shouldCreateFourPointTiles() {
		String stringLetters = "FHVWY";
		char convertToCharF = stringLetters.charAt(0);
		char convertToCharH = stringLetters.charAt(1);
		char convertToCharV = stringLetters.charAt(2);
		char convertToCharW = stringLetters.charAt(3);
		char convertToCharY = stringLetters.charAt(4);
		assertEquals("F", Character.toString(new Tile(convertToCharF).getLetter()));
		assertEquals("H", Character.toString(new Tile(convertToCharH).getLetter()));
		assertEquals("V", Character.toString(new Tile(convertToCharV).getLetter()));
		assertEquals("W", Character.toString(new Tile(convertToCharW).getLetter()));
		assertEquals("Y", Character.toString(new Tile(convertToCharY).getLetter()));
		assertEquals(4, new Tile(convertToCharF).getPointValue());
		assertEquals(4, new Tile(convertToCharH).getPointValue());
		assertEquals(4, new Tile(convertToCharV).getPointValue());
		assertEquals(4, new Tile(convertToCharW).getPointValue());
		assertEquals(4, new Tile(convertToCharY).getPointValue());
	}
	
	@Test
	public void shouldCreateFivePointTiles() {
		String stringLetters = "K";
		char convertToCharK = stringLetters.charAt(0);
		assertEquals("K", Character.toString(new Tile(convertToCharK).getLetter()));
		assertEquals(5, new Tile(convertToCharK).getPointValue());
	}
	
	@Test
	public void shouldCreateEightPointTiles() {
		String stringLetters = "JX";
		char convertToCharJ = stringLetters.charAt(0);
		char convertToCharX = stringLetters.charAt(1);
		assertEquals("J", Character.toString(new Tile(convertToCharJ).getLetter()));
		assertEquals("X", Character.toString(new Tile(convertToCharX).getLetter()));
		assertEquals(8, new Tile(convertToCharJ).getPointValue());
		assertEquals(8, new Tile(convertToCharX).getPointValue());
	}
	
	@Test
	public void shouldCreateTenPointTiles() {
		String stringLetters = "QZ";
		char convertToCharQ = stringLetters.charAt(0);
		char convertToCharZ = stringLetters.charAt(1);
		assertEquals("Q", Character.toString(new Tile(convertToCharQ).getLetter()));
		assertEquals("Z", Character.toString(new Tile(convertToCharZ).getLetter()));
		assertEquals(10, new Tile(convertToCharQ).getPointValue());
		assertEquals(10, new Tile(convertToCharZ).getPointValue());
	}
}