package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for TileRack model class getNumberOfTilesNeeded method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestTileRackGetNumberOfTilesNeeded {

	private TileRack rack;
	private TileBag bag;

	@BeforeEach
	public void setUp() throws Exception {
		this.rack = new TileRack();
		this.bag = new TileBag();
	}

	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		assertEquals(7, this.rack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() throws EmptyTileBagException {
		Tile randomTile = this.bag.drawTile();
		this.rack.append(randomTile);
		assertEquals(6, this.rack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() throws EmptyTileBagException {
		Tile randomTile1 = this.bag.drawTile();
		this.rack.append(randomTile1);
		Tile randomTile2 = this.bag.drawTile();
		this.rack.append(randomTile2);
		Tile randomTile3 = this.bag.drawTile();
		this.rack.append(randomTile3);
		assertEquals(4, this.rack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void fullRackNeedsZeroTiles() throws EmptyTileBagException {
		for (int index = 0; index < TileRack.MAX_SIZE; index++) {
			if (!this.bag.isEmpty()) {
				Tile randomTile = this.bag.drawTile();
				this.rack.append(randomTile);
			}
		}
		assertEquals(0, this.rack.getNumberOfTilesNeeded());
	}
}