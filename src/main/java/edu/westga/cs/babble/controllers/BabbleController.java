package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.util.converter.NumberStringConverter;
import edu.westga.cs.babble.model.EmptyTileBagException;

import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.PlayedWord;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Controller for the Babble fxml view file
 * 
 * @author Kim Weible
 * @version 2021.08.20
 */
public class BabbleController implements Initializable {
	@FXML
	private Label labelTiles;
	@FXML
	private ListView<Tile> listViewRack;
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

	private PlayedWord word;
	private TileRack rack;
	private TileBag bag;
	private int score;

	/**
	 * Constructor used to initialize list view and class objects for GUI
	 */
	public BabbleController() {
		this.listViewRack = new ListView<Tile>();
		this.listViewWord = new ListView<Tile>();
		this.score = 0;
		this.bag = new TileBag();
		this.rack = new TileRack();
		this.word = new PlayedWord();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.buttonReset.setOnAction(e -> this.resetRackTiles());
		this.buttonPlayWord.setOnAction(e -> this.playWord());
		this.setScore();

		this.listViewRack = this.createTileRack();
		this.setRackTiles();
		this.clickRackTiles();
		this.clickWordTiles();
	}

	private void setScore() {
		this.textFieldScore.textProperty().bindBidirectional(new SimpleIntegerProperty(this.score), new NumberStringConverter());
	}

	private void playWord() {
		boolean validWord = false;

		WordDictionary dictionary = new WordDictionary();
		validWord = dictionary.isValidWord(this.word.getHand());

		if (validWord) {
			this.drawNewTiles(this.rack.getNumberOfTilesNeeded());
			this.score += this.word.getScore();
			this.word.clear();
		} else {
			showMessageDialog(null, "Not a valid word");
		}
		this.setScore();
	}

	private void setWordTiles() {
		this.listViewWord.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {

			@Override
			public ListCell<Tile> call(ListView<Tile> wordTiles) {
				return new TileCharacterCell();
			}
		});
	}

	private void resetRackTiles() {
		for (int index = 0; index < this.listViewWord.getItems()
				.size(); index++) {
			this.listViewRack.getItems().add(this.listViewWord.getItems().get(index));
		}
		this.setRackTiles();
		this.word.clear();
	}

	private ListView<Tile> createTileRack() {
		this.drawNewTiles(TileRack.MAX_SIZE);
		return this.listViewRack;
	}

	private void drawNewTiles(int numberOfTiles) {
		for (int index = 0; index < numberOfTiles; index++) {
			try {
				if (!this.bag.isEmpty()) {
					Tile randomTile = this.bag.drawTile();
					this.rack.append(randomTile);
				}
			} catch (EmptyTileBagException exc) {
				exc.printStackTrace();
			}
		}
		//add observe rack to rack list
		this.listViewRack.setItems(this.rack.tiles());
	}

	private void setRackTiles() {
		this.listViewRack.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new TileCharacterCell();
			}
		});
	}

	private void clickRackTiles() {
		this.listViewRack.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (BabbleController.this.listViewRack.getItems()
						.size() > 0) {
					Tile clickedRackTile = BabbleController.this.listViewRack
							.getSelectionModel().getSelectedItem();
					
					//add to word observable
					BabbleController.this.word.append(clickedRackTile);
					
					//remove from rack observable
					try {
						BabbleController.this.rack.remove(clickedRackTile);
					} catch (TileNotInGroupException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//update listviews
					BabbleController.this.updateListViews();
				}
			}
		});
	}
	
	private void clickWordTiles() {
		this.listViewWord.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (BabbleController.this.listViewWord.getItems()
						.size() > 0) {
					Tile clickedWordTile = BabbleController.this.listViewWord
							.getSelectionModel().getSelectedItem();
					
					//remove from word observable
					try {
						BabbleController.this.word.remove(clickedWordTile);
					} catch (TileNotInGroupException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//add to rack observable
					BabbleController.this.rack.append(clickedWordTile);
					
					//update listviews
					BabbleController.this.updateListViews();
				}
			}
		});
	}
	
	private void updateListViews() {
		BabbleController.this.listViewRack.setItems(BabbleController.this.rack.tiles());
		BabbleController.this.listViewWord.setItems(BabbleController.this.word.tiles());
		
		BabbleController.this.setWordTiles();
		BabbleController.this.setRackTiles();
	}

	static class TileCharacterCell extends ListCell<Tile> {
		public void updateItem(Tile item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				setText(String.valueOf(item.getLetter()));
				setAccessibleText(String.valueOf(item.getLetter()));
			} else {
				setText("");
			}
		}
	}
}
