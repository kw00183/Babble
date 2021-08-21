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
import edu.westga.cs.babble.model.TileGroup;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;

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
	private TextField textFieldYourWord;
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

	public BabbleGuiController() {
		this.listViewTiles = new ListView<Tile>();
		this.tileBag = new TileBag();
		this.tileRack = new TileRack();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.buttonReset.setOnAction(e -> this.resetYourWord());
		this.textFieldScore.setText("0");

		try {
			this.listViewTiles = this.getRandomTiles();
			this.setTiles();
			this.clickTiles();
		} catch (EmptyTileBagException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void resetYourWord() {
		this.textFieldYourWord.setText("");
	}

	private ListView<Tile> getRandomTiles() throws EmptyTileBagException {
		for (int index = 0; index < TileRack.MAX_SIZE; index++) {
			Tile randomTile = this.tileBag.drawTile();
			this.tileRack.append(randomTile);
		}
		return this.listViewTiles;
	}
	
	private void setTiles() {
		ObservableList<Tile> tiles = this.tileRack.tiles();
		this.listViewTiles.setItems(tiles);

		this.listViewTiles.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new TileCharacterCell();
			}
		});
	}
	
	private void clickTiles() {
		this.listViewTiles.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				Tile clickedTile = BabbleGuiController.this.listViewTiles.getSelectionModel().getSelectedItem();
				try {
					BabbleGuiController.this.tileRack.remove(clickedTile);
					
					BabbleGuiController.this.setTiles();
					
					ObservableList<Tile> tiles = BabbleGuiController.this.tileRack.tiles(); 
					System.out.println(tiles);
				} catch (TileNotInGroupException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
