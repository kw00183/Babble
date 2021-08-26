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

	private Dummy dummy;

	@BeforeEach
	public void setUp() throws Exception {
		this.dummy = new Dummy();
	}

	@Test
	public void shouldNotAllowNull() throws TileNotInGroupException {
		String message = null;
		try {
			this.dummy.remove(null);
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
		assertEquals(0, this.dummy.tiles().size());
		
		try {
			this.dummy.remove(tile1);
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
		this.dummy.append(tile1);
		this.dummy.append(tile2);
		this.dummy.append(tile4);
		this.dummy.append(tile5);
		try {
			this.dummy.remove(tile3);
		} catch (TileNotInGroupException tnige) {
			message = "cannot remove tile not in group";
		}
		assertEquals("cannot remove tile not in group", message);
		assertEquals(4, this.dummy.tiles().size());
	}
	
	@Test
	public void canRemoveOnlyTileInTileGroup() throws TileNotInGroupException {
		String stringLetters = "ABBCD";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		this.dummy.append(tile1);
		assertEquals(1, this.dummy.tiles().size());
		this.dummy.remove(tile1);
		assertEquals(0, this.dummy.tiles().size());
	}
	
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() throws TileNotInGroupException {
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
		assertEquals("ABBCD", this.dummy.getHand());
		
		for (int index = 0; index < this.dummy.tiles().size(); index++) {
			if (index == 0) {
				this.dummy.remove(this.dummy.tiles().get(index));
			}
		}
		assertEquals("BBCD", this.dummy.getHand());
	}
	
	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() throws TileNotInGroupException {
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
		assertEquals("ABBCD", this.dummy.getHand());
		
		for (int index = 0; index < this.dummy.tiles().size(); index++) {
			if (index == this.dummy.tiles().size() - 1) {
				this.dummy.remove(this.dummy.tiles().get(index));
			}
		}
		assertEquals("ABBC", this.dummy.getHand());
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
		this.dummy.append(tile1);
		this.dummy.append(tile2);
		this.dummy.append(tile3);
		this.dummy.append(tile4);
		this.dummy.append(tile5);
		this.dummy.append(tile6);
		assertEquals("ABBCDE", this.dummy.getHand());
		
		for (int index = 0; index < this.dummy.tiles().size(); index++) {
			if ((this.dummy.tiles().size() % 2) == 0 && index == (this.dummy.tiles().size() / 2) -1) {
				this.dummy.remove(this.dummy.tiles().get(index));
			} else if (index == this.dummy.tiles().size() / 2) {
				this.dummy.remove(this.dummy.tiles().get(index));
			}
		}
		assertEquals("ABCDE", this.dummy.getHand());
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
		this.dummy.append(tile1);
		this.dummy.append(tile2);
		this.dummy.append(tile3);
		this.dummy.append(tile4);
		this.dummy.append(tile5);
		this.dummy.append(tile6);
		assertEquals("ABBCDE", this.dummy.getHand());
		
		this.dummy.remove(tile2);
		this.dummy.remove(tile4);
		this.dummy.remove(tile5);
		assertEquals("ABE", this.dummy.getHand());
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
		this.dummy.append(tile1);
		this.dummy.append(tile2);
		this.dummy.append(tile3);
		this.dummy.append(tile4);
		this.dummy.append(tile5);
		this.dummy.append(tile6);
		assertEquals("ABBCDE", this.dummy.getHand());
		
		try {
			this.dummy.remove(tile2);
			this.dummy.remove(tile2);
		} catch (TileNotInGroupException tnige) {
			message = "cannot remove duplicate tile";
		}
		assertEquals("cannot remove duplicate tile", message);
	}
}