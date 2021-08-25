package edu.westga.cs.babble.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for TileBag model class
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileBagDrawTile {

	private TileBag bag;

	@BeforeEach
	void setUp() throws Exception {
		this.bag = new TileBag();
	}

	@Test
	public void canDrawAllTiles() throws EmptyTileBagException {
		boolean isBagEmpty97 = false;
		for (int index = 0; index < 97; index++) {
			this.bag.drawTile();
			isBagEmpty97 = this.bag.isEmpty();
		}
		assertFalse(isBagEmpty97);
		
		this.bag = new TileBag();
		boolean isBagEmpty98 = false;
		for (int index = 0; index < 98; index++) {
			this.bag.drawTile();
			isBagEmpty98 = this.bag.isEmpty();
		}
		assertTrue(isBagEmpty98);
	}
	
	@Test
	public void canNotDrawTooManyTiles() throws EmptyTileBagException {
		String message = null;
		for (int index = 0; index < 99; index++) {
			try {
				this.bag.drawTile();
			} catch (EmptyTileBagException etbe) {
				 message = "too many tiles drawn";
			}
		}
		assertEquals("too many tiles drawn", message);
	}
	
	@Test
	public void hasProperTileDistribution() throws EmptyTileBagException {
		ArrayList<String> array = new ArrayList<String>();
		for (int index = 0; index < 98; index++) {
			array.add(Character.toString(this.bag.drawTile().getLetter()));
		}
		assertEquals(9, Collections.frequency(array, "A"));
		assertEquals(2, Collections.frequency(array, "B"));
		assertEquals(2, Collections.frequency(array, "C"));
		assertEquals(4, Collections.frequency(array, "D"));
		assertEquals(12, Collections.frequency(array, "E"));
		assertEquals(2, Collections.frequency(array, "F"));
		assertEquals(3, Collections.frequency(array, "G"));
		assertEquals(2, Collections.frequency(array, "H"));
		assertEquals(9, Collections.frequency(array, "I"));
		assertEquals(1, Collections.frequency(array, "J"));
		assertEquals(1, Collections.frequency(array, "K"));
		assertEquals(4, Collections.frequency(array, "L"));
		assertEquals(2, Collections.frequency(array, "M"));
		assertEquals(6, Collections.frequency(array, "N"));
		assertEquals(8, Collections.frequency(array, "O"));
		assertEquals(2, Collections.frequency(array, "P"));
		assertEquals(1, Collections.frequency(array, "Q"));
		assertEquals(6, Collections.frequency(array, "R"));
		assertEquals(4, Collections.frequency(array, "S"));
		assertEquals(6, Collections.frequency(array, "T"));
		assertEquals(4, Collections.frequency(array, "U"));
		assertEquals(2, Collections.frequency(array, "V"));
		assertEquals(2, Collections.frequency(array, "W"));
		assertEquals(1, Collections.frequency(array, "X"));
		assertEquals(2, Collections.frequency(array, "Y"));
		assertEquals(1, Collections.frequency(array, "Z"));
	}
}