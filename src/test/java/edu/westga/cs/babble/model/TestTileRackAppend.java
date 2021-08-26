package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for TileRack model class append method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileRackAppend {

	private TileRack rack;
	private TileBag bag;

	@BeforeEach
	public void setUp() throws Exception {
		this.rack = new TileRack();
		this.bag = new TileBag();
	}

	@Test
	public void shouldNotAppendToFullRack() throws EmptyTileBagException {
		String message = null;
		for (int index = 0; index < TileRack.MAX_SIZE + 1; index++) {
			try {
				if (!this.bag.isEmpty()) {
					Tile randomTile = this.bag.drawTile();
					this.rack.append(randomTile);
				}
			} catch (TileRackFullException trfe) {
				message = "cannot append to full rack";
			}
		}
		assertEquals("cannot append to full rack", message);
	}
}