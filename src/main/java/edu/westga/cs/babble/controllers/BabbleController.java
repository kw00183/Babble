package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import edu.westga.cs.babble.model.EmptyTileBagException;

import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Controller for the Babble fxml view file
 * 
 * @author Kim Weible
 * @version 2021.08.20
 */
public class BabbleGuiController implements Initializable {
	@FXML
	private Label labelTiles;
	@FXML
	private ListView<Tile> listViewTiles;
	@FXML
	private Label labelYourWord;
	@FXML
	private ListView<Tile> listViewWord;
	@FXML
	private Button buttonReset;
	@FXML
	private Button buttonPlayWord;
	@FXML
	private Label labelScore;
	@FXML
	private TextField textFieldScore;

	private TileRack tileRack;
	private TileBag tileBag;
	private int score;

	public BabbleGuiController() {
		this.listViewTiles = new ListView<Tile>();
		this.listViewWord = new ListView<Tile>();
		this.score = 0;
		this.tileBag = new TileBag();
		this.tileRack = new TileRack();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.buttonReset.setOnAction(e -> this.resetWordTiles());
		this.buttonPlayWord.setOnAction(e -> this.playWord());
		this.setScore();

		this.listViewTiles = this.createTileRack();
		this.setTiles();
		this.clickTiles();
	}

	private void setScore() {
		this.textFieldScore.setText(String.valueOf(this.score));
	}

	private void playWord() {
		String word = "";
		int points = 0;
		boolean validWord = false;
		for (Tile tile : this.listViewWord.getItems()) {
			word += tile.getLetter();
			points += tile.getPointValue();
		}
		WordDictionary dictionary = new WordDictionary();
		validWord = dictionary.isValidWord(word);

		if (validWord) {
			this.score += points;
			int wordLength = word.length();
			this.drawNewTiles(wordLength);
			this.listViewWord.getItems().clear();
		} else {
			showMessageDialog(null, "Not a valid word");
		}
		this.setScore();

		System.out.println(validWord);
	}

	private void setWordTiles(Tile tile) {
		this.listViewWord.getItems().add(tile);
		this.listViewWord
				.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {

					@Override
					public ListCell<Tile> call(ListView<Tile> wordTiles) {
						return new TileCharacterCell();
					}
				});
	}

	private void resetWordTiles() {
		for (int index = 0; index < this.listViewWord.getItems()
				.size(); index++) {
			this.tileRack.append(this.listViewWord.getItems().get(index));
		}
		this.setTiles();
		this.listViewWord.getItems().clear();
	}

	private ListView<Tile> createTileRack() {
		this.drawNewTiles(TileRack.MAX_SIZE);
		return this.listViewTiles;
	}

	private void drawNewTiles(int numberOfTiles) {
		for (int index = 0; index < numberOfTiles; index++) {
			try {
				if (!this.tileBag.isEmpty()) {
					Tile randomTile = this.tileBag.drawTile();
					this.tileRack.append(randomTile);
				}
			} catch (EmptyTileBagException exc) {
				exc.printStackTrace();
			}
		}
	}

	private void setTiles() {
		ObservableList<Tile> tiles = this.tileRack.tiles();
		this.listViewTiles.setItems(tiles);

		this.listViewTiles
				.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
					@Override
					public ListCell<Tile> call(ListView<Tile> tiles) {
						return new TileCharacterCell();
					}
				});
	}

	private void clickTiles() {
		this.listViewTiles.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (BabbleGuiController.this.listViewTiles.getItems()
						.size() > 0) {
					Tile clickedTile = BabbleGuiController.this.listViewTiles
							.getSelectionModel().getSelectedItem();
					try {
						BabbleGuiController.this.tileRack.remove(clickedTile);
						BabbleGuiController.this.setWordTiles(clickedTile);
					} catch (TileNotInGroupException exc) {
						exc.printStackTrace();
					}
				}
			}
		});
	}

	static class TileCharacterCell extends ListCell<Tile> {
		public void updateItem(Tile item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				setText(String.valueOf(item.getLetter()));
			} else {
				setText("");
			}
		}
	}
}
