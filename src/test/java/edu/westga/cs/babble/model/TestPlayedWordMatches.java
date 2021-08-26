package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for PlayedWord model class matches method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestPlayedWordMatches {

	private PlayedWord word;

	@BeforeEach
	public void setUp() throws Exception {
		this.word = new PlayedWord();
	}

	@Test
	public void hasTilesForAMultipleLetterWord() {
		String stringLetterH = "H";
		this.word.append(new Tile(stringLetterH.charAt(0)));
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		String stringLetterC = "C";
		this.word.append(new Tile(stringLetterC.charAt(0)));
		String stringLetterK = "K";
		this.word.append(new Tile(stringLetterK.charAt(0)));
		assertTrue(this.word.matches("HACK"));
	}
	
	@Test
	public void hasTilesForASingleLetterWord() {
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		assertTrue(this.word.matches("A"));
	}
	
	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		String stringLetterH = "H";
		this.word.append(new Tile(stringLetterH.charAt(0)));
		String stringLetterK = "K";
		this.word.append(new Tile(stringLetterK.charAt(0)));
		String stringLetterC = "C";
		this.word.append(new Tile(stringLetterC.charAt(0)));
		assertFalse(this.word.matches("HACK"));
	}
	
	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		String stringLetterH = "H";
		this.word.append(new Tile(stringLetterH.charAt(0)));
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		String stringLetterC = "C";
		this.word.append(new Tile(stringLetterC.charAt(0)));
		assertFalse(this.word.matches("HACK"));
	}
	
	@Test
	public void canMatchWordWithDuplicateLetters() {
		String stringLetterM1 = "M";
		this.word.append(new Tile(stringLetterM1.charAt(0)));
		String stringLetterA1 = "A";
		this.word.append(new Tile(stringLetterA1.charAt(0)));
		String stringLetterM2 = "M";
		this.word.append(new Tile(stringLetterM2.charAt(0)));
		String stringLetterA2 = "A";
		this.word.append(new Tile(stringLetterA2.charAt(0)));
		assertTrue(this.word.matches("MAMA"));
	}
	
	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		String stringLetterH = "H";
		this.word.append(new Tile(stringLetterH.charAt(0)));
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		String stringLetterC = "C";
		this.word.append(new Tile(stringLetterC.charAt(0)));
		String stringLetterK = "K";
		this.word.append(new Tile(stringLetterK.charAt(0)));
		assertFalse(this.word.matches(""));
	}
	
	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		assertFalse(this.word.matches(""));
	}
	
	@Test
	public void shouldNotAllowNull() {
		String message = null;
		try {
			this.word.matches(null);
		} catch (IllegalArgumentException iae) {
			message = "cannot accept null text";
		}
		assertEquals("cannot accept null text", message);
	}
}