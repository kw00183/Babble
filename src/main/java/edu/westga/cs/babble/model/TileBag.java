package edu.westga.cs.babble.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Container for Tiles, allows drawing random Tiles. When created, it
 * self-populates with a Scrabble-like set of tiles.
 * 
 * @author lewisb
 * @version 2021.08.20
 */
public class TileBag {

	private ArrayList<Tile> tiles;
	private Random rand;

	/**
	 * Creates a new, populated TileBag
	 */
	public TileBag() {
		this.rand = new Random();
		this.tiles = new ArrayList<Tile>();
		this.populateWithScrabbleTiles();
	}

	/**
	 * Brute-force fills the bag with a set of tiles with the Scrabble points
	 * and letter distribution (ignoring the blank tiles)
	 * 1-pt tiles: E, A, I, O, N, R, T, L, S, U
	 * 2-pt tiles: D, G
	 * 3 pt tiles: B, C, M, P
	 * 4 pt tiles: F, H, V, W, Y
	 * 5-pt tiles: K
	 * 8-pt tiles: J, X
	 * 10-pt tiles: Q, Z
	 */
	private void populateWithScrabbleTiles() {
		this.get1PointTiles();
		this.get2PointTiles();
		this.get3PointTiles();
		this.get4PointTiles();

		this.tiles.add(new Tile('K'));

		this.tiles.add(new Tile('J'));
		this.tiles.add(new Tile('X'));

		this.tiles.add(new Tile('Q'));
		this.tiles.add(new Tile('Z'));
	}
	
	private void get1PointTiles() {
		for (int index = 0; index < 12; index++) {
			this.tiles.add(new Tile('E'));
		}
		for (int index = 0; index < 9; index++) {
			this.tiles.add(new Tile('A'));
		}
		for (int index = 0; index < 9; index++) {
			this.tiles.add(new Tile('I'));
		}
		for (int index = 0; index < 8; index++) {
			this.tiles.add(new Tile('O'));
		}
		for (int index = 0; index < 6; index++) {
			this.tiles.add(new Tile('N'));
		}
		for (int index = 0; index < 6; index++) {
			this.tiles.add(new Tile('R'));
		}
		for (int index = 0; index < 6; index++) {
			this.tiles.add(new Tile('T'));
		}
		for (int index = 0; index < 4; index++) {
			this.tiles.add(new Tile('L'));
		}
		for (int index = 0; index < 4; index++) {
			this.tiles.add(new Tile('S'));
		}
		for (int index = 0; index < 4; index++) {
			this.tiles.add(new Tile('U'));
		}
	}
	
	private void get2PointTiles() {
		for (int index = 0; index < 4; index++) {
			this.tiles.add(new Tile('D'));
		}
		for (int index = 0; index < 3; index++) {
			this.tiles.add(new Tile('G'));
		}
	}
	
	private void get3PointTiles() {
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('B'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('C'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('M'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('P'));
		}
	}
	
	private void get4PointTiles() {
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('F'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('H'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('V'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('W'));
		}
		for (int index = 0; index < 2; index++) {
			this.tiles.add(new Tile('Y'));
		}
	}

	/**
	 * Answers the existential question of "is this bag empty?"
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.tiles.isEmpty();
	}

	/**
	 * Draws and removes a random tile from the bag
	 * 
	 * @return the removed tile
	 * @throws EmptyTileBagException
	 *             if the bag is empty
	 */
	public Tile drawTile() throws EmptyTileBagException {
		if (this.isEmpty()) {
			throw new EmptyTileBagException();
		}

		int size = this.tiles.size();
		int index = this.rand.nextInt(size);
		Tile patsy = this.tiles.remove(index);
		return patsy;
	}
}
