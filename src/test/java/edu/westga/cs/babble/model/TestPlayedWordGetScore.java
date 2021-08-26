package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for PlayedWord model class getScore method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestPlayedWordGetScore {

	private PlayedWord word;

	@BeforeEach
	public void setUp() throws Exception {
		this.word = new PlayedWord();
	}

	@Test
	public void emptyWordShouldHaveScoreOfZero() {
		assertEquals(0, this.word.getScore());
	}
	
	@Test
	public void scoreAOneTileWord() {
		String stringLetter = "A";
		this.word.append(new Tile(stringLetter.charAt(0)));
		assertEquals(1, this.word.getScore());
	}
	
	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		String stringLetterH = "H";
		this.word.append(new Tile(stringLetterH.charAt(0)));
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		String stringLetterC = "C";
		this.word.append(new Tile(stringLetterC.charAt(0)));
		String stringLetterK = "K";
		this.word.append(new Tile(stringLetterK.charAt(0)));
		assertEquals(13, this.word.getScore());
	}
	
	@Test
	public void scoreAWordContainingDuplicateTiles() {
		String stringLetters = "MA";
		Tile tile1 = new Tile(stringLetters.charAt(0));
		Tile tile2 = new Tile(stringLetters.charAt(1));
		
		try {
			this.word.append(tile1);
			this.word.append(tile2);
			this.word.append(tile1);
			this.word.append(tile2);
		} catch (IllegalArgumentException iae) {
			assertEquals("can not add same tile twice", iae.getMessage());
		}
		assertEquals(2, this.word.tiles().size());
		assertEquals(4, this.word.getScore());
	}
}