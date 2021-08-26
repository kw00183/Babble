package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

/**
 * JUnit test class for TileGroup model class append method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileGroupAppend {

	private Dummy dummy;

	@BeforeEach
	public void setUp() throws Exception {
		this.dummy = new Dummy();
	}

	@Test
	public void shouldNotAllowNull() {
		try {
			this.dummy.append(null);
		} catch (IllegalArgumentException iae) {
			assertEquals("tile cannot be null", iae.getMessage());
		}
	}
	
	@Test
	public void emptyGroupShouldBeEmpty() {
		ObservableList<Tile> tiles = this.dummy.tiles();
		assertEquals(0, tiles.size());
	}
	
	@Test
	public void shouldHaveOneTileInTileGroup() {
		String stringLetter = "A";
		this.dummy.append(new Tile(stringLetter.charAt(0)));
		assertEquals(1, this.dummy.tiles().size());
	}
	
	@Test
	public void shouldHaveManyTilesInTileGroup() {
		String stringLetters = "AB";
		this.dummy.append(new Tile(stringLetters.charAt(0)));
		this.dummy.append(new Tile(stringLetters.charAt(1)));
		assertEquals(2, this.dummy.tiles().size());
	}
	
	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		String stringLetters = "ABBCD";
		this.dummy.append(new Tile(stringLetters.charAt(0)));
		this.dummy.append(new Tile(stringLetters.charAt(1)));
		this.dummy.append(new Tile(stringLetters.charAt(2)));
		this.dummy.append(new Tile(stringLetters.charAt(3)));
		this.dummy.append(new Tile(stringLetters.charAt(4)));
		assertEquals(5, this.dummy.tiles().size());
	}
	
	@Test
	public void canNotAddSameTileTwice() {
		String stringLetters = "ABBCD";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		Tile tile3 = new Tile(stringLetters.charAt(2));
		Tile tile4 = new Tile(stringLetters.charAt(3));
		Tile tile5 = new Tile(stringLetters.charAt(4));
		this.dummy.append(tile1);
		this.dummy.append(tile2);
		this.dummy.append(tile3);
		this.dummy.append(tile4);
		this.dummy.append(tile5);
		assertEquals(5, this.dummy.tiles().size());
		
		try {
			this.dummy.append(tile1);
		} catch (IllegalArgumentException iae) {
			assertEquals("can not add same tile twice", iae.getMessage());
		}
		assertEquals(5, this.dummy.tiles().size());
	}
}