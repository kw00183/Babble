package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

/**
 * JUnit test class for Tile model class
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileGroupAppend {

	private TileRack rack;

	@BeforeEach
	public void setUp() throws Exception {
		this.rack = new TileRack();
	}

	@Test
	public void shouldNotAllowNull() {
		try {
			this.rack.append(null);
		} catch (IllegalArgumentException iae) {
			assertEquals("tile cannot be null", iae.getMessage());
		}
	}
	
	@Test
	public void emptyGroupShouldBeEmpty() {
		ObservableList<Tile> tiles = this.rack.tiles();
		assertEquals(0, tiles.size());
	}
	
	@Test
	public void shouldHaveOneTileInTileGroup() {
		String stringLetter = "A";
		this.rack.append(new Tile(stringLetter.charAt(0)));
		assertEquals(1, this.rack.tiles().size());
	}
	
	@Test
	public void shouldHaveManyTilesInTileGroup() {
		String stringLetters = "AB";
		this.rack.append(new Tile(stringLetters.charAt(0)));
		this.rack.append(new Tile(stringLetters.charAt(1)));
		assertEquals(2, this.rack.tiles().size());
	}
	
	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		String stringLetters = "ABBCD";
		this.rack.append(new Tile(stringLetters.charAt(0)));
		this.rack.append(new Tile(stringLetters.charAt(1)));
		this.rack.append(new Tile(stringLetters.charAt(2)));
		this.rack.append(new Tile(stringLetters.charAt(3)));
		this.rack.append(new Tile(stringLetters.charAt(4)));
		assertEquals(5, this.rack.tiles().size());
	}
	
	@Test
	public void canNotAddSameTileTwice() {
		String stringLetters = "ABBCD";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		Tile tile3 = new Tile(stringLetters.charAt(2));
		Tile tile4 = new Tile(stringLetters.charAt(3));
		Tile tile5 = new Tile(stringLetters.charAt(4));
		this.rack.append(tile1);
		this.rack.append(tile2);
		this.rack.append(tile3);
		this.rack.append(tile4);
		this.rack.append(tile5);
		assertEquals(5, this.rack.tiles().size());
		
		try {
			this.rack.append(tile1);
		} catch (IllegalArgumentException iae) {
			assertEquals("can not add same tile twice", iae.getMessage());
		}
		assertEquals(5, this.rack.tiles().size());
	}
}