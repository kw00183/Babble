package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for TileGroup model class remove method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileGroupRemove {

	private TileRack rack;

	@BeforeEach
	public void setUp() throws Exception {
		this.rack = new TileRack();
	}

	@Test
	public void shouldNotAllowNull() throws TileNotInGroupException {
		String message = null;
		try {
			this.rack.remove(null);
		} catch (IllegalArgumentException iae) {
			message = "cannot remove null tile";
		}
		assertEquals("cannot remove null tile", message);
	}
	
	@Test
	public void canNotRemoveFromEmptyTileGroup() throws TileNotInGroupException {
		String message = null;
		String stringLetters = "ABBCD";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		assertEquals(0, this.rack.tiles().size());
		
		try {
			this.rack.remove(tile1);
		} catch (TileNotInGroupException tnige) {
			message = "cannot remove tile from empty group";
		}
		assertEquals("cannot remove tile from empty group", message);
	}
	
	@Test
	public void canNotRemoveTileNotInTileGroup() throws TileNotInGroupException {
		String message = null;
		String stringLetters = "ABBCD";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		Tile tile3 = new Tile(stringLetters.charAt(2));
		Tile tile4 = new Tile(stringLetters.charAt(3));
		Tile tile5 = new Tile(stringLetters.charAt(4));
		this.rack.append(tile1);
		this.rack.append(tile2);
		this.rack.append(tile4);
		this.rack.append(tile5);
		try {
			this.rack.remove(tile3);
		} catch (TileNotInGroupException tnige) {
			message = "cannot remove tile not in group";
		}
		assertEquals("cannot remove tile not in group", message);
		assertEquals(4, this.rack.tiles().size());
	}
	
	@Test
	public void canRemoveOnlyTileInTileGroup() throws TileNotInGroupException {
		String stringLetters = "ABBCD";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		this.rack.append(tile1);
		assertEquals(1, this.rack.tiles().size());
		this.rack.remove(tile1);
		assertEquals(0, this.rack.tiles().size());
	}
	
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() throws TileNotInGroupException {
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
		assertEquals("ABBCD", this.rack.getHand());
		
		for (int index = 0; index < this.rack.tiles().size(); index++) {
			if (index == 0) {
				this.rack.remove(this.rack.tiles().get(index));
			}
		}
		assertEquals("BBCD", this.rack.getHand());
	}
	
	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() throws TileNotInGroupException {
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
		assertEquals("ABBCD", this.rack.getHand());
		
		for (int index = 0; index < this.rack.tiles().size(); index++) {
			if (index == this.rack.tiles().size() - 1) {
				this.rack.remove(this.rack.tiles().get(index));
			}
		}
		assertEquals("ABBC", this.rack.getHand());
	}
	
	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() throws TileNotInGroupException {
		String stringLetters = "ABBCDE";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		Tile tile3 = new Tile(stringLetters.charAt(2));
		Tile tile4 = new Tile(stringLetters.charAt(3));
		Tile tile5 = new Tile(stringLetters.charAt(4));
		Tile tile6 = new Tile(stringLetters.charAt(5));
		this.rack.append(tile1);
		this.rack.append(tile2);
		this.rack.append(tile3);
		this.rack.append(tile4);
		this.rack.append(tile5);
		this.rack.append(tile6);
		assertEquals("ABBCDE", this.rack.getHand());
		
		for (int index = 0; index < this.rack.tiles().size(); index++) {
			if ((this.rack.tiles().size() % 2) == 0 && index == (this.rack.tiles().size() / 2) -1) {
				this.rack.remove(this.rack.tiles().get(index));
			} else if (index == this.rack.tiles().size() / 2) {
				this.rack.remove(this.rack.tiles().get(index));
			}
		}
		assertEquals("ABCDE", this.rack.getHand());
	}
	
	@Test
	public void canRemoveMultipleTilesFromTileGroup() throws TileNotInGroupException {
		String stringLetters = "ABBCDE";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		Tile tile3 = new Tile(stringLetters.charAt(2));
		Tile tile4 = new Tile(stringLetters.charAt(3));
		Tile tile5 = new Tile(stringLetters.charAt(4));
		Tile tile6 = new Tile(stringLetters.charAt(5));
		this.rack.append(tile1);
		this.rack.append(tile2);
		this.rack.append(tile3);
		this.rack.append(tile4);
		this.rack.append(tile5);
		this.rack.append(tile6);
		assertEquals("ABBCDE", this.rack.getHand());
		
		this.rack.remove(tile2);
		this.rack.remove(tile4);
		this.rack.remove(tile5);
		assertEquals("ABE", this.rack.getHand());
	}
	
	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() throws TileNotInGroupException {
		String message = null;
		String stringLetters = "ABBCDE";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		Tile tile3 = new Tile(stringLetters.charAt(2));
		Tile tile4 = new Tile(stringLetters.charAt(3));
		Tile tile5 = new Tile(stringLetters.charAt(4));
		Tile tile6 = new Tile(stringLetters.charAt(5));
		this.rack.append(tile1);
		this.rack.append(tile2);
		this.rack.append(tile3);
		this.rack.append(tile4);
		this.rack.append(tile5);
		this.rack.append(tile6);
		assertEquals("ABBCDE", this.rack.getHand());
		
		try {
			this.rack.remove(tile2);
			this.rack.remove(tile2);
		} catch (TileNotInGroupException tnige) {
			message = "cannot remove duplicate tile";
		}
		assertEquals("cannot remove duplicate tile", message);
	}
}