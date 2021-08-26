package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for PlayedWord model class clear method
 * 
 * @author Kim Weible
 * @version 2021.08.25
 */
public class TestPlayedWordClear {

	private PlayedWord word;

	@BeforeEach
	public void setUp() throws Exception {
		this.word = new PlayedWord();
	}

	@Test
	public void shouldClearEmptyWord() {
		assertEquals(0, this.word.tiles().size());
		assertEquals("", this.word.getHand());
		this.word.clear();
		assertEquals("", this.word.getHand());
	}
	
	@Test
	public void shouldClearWordWithOneTile() {
		assertEquals(0, this.word.tiles().size());
		assertEquals("", this.word.getHand());
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		assertEquals(1, this.word.tiles().size());
		assertEquals("A", this.word.getHand());
		this.word.clear();
		assertEquals(0, this.word.tiles().size());
		assertEquals("", this.word.getHand());
	}
	
	@Test
	public void shouldClearWordWithManyTiles() {
		assertEquals(0, this.word.tiles().size());
		assertEquals("", this.word.getHand());
		String stringLetterH = "H";
		this.word.append(new Tile(stringLetterH.charAt(0)));
		String stringLetterA = "A";
		this.word.append(new Tile(stringLetterA.charAt(0)));
		String stringLetterC = "C";
		this.word.append(new Tile(stringLetterC.charAt(0)));
		String stringLetterK = "K";
		this.word.append(new Tile(stringLetterK.charAt(0)));
		assertEquals(4, this.word.tiles().size());
		assertEquals("HACK", this.word.getHand());
		this.word.clear();
		assertEquals(0, this.word.tiles().size());
		assertEquals("", this.word.getHand());
	}
}