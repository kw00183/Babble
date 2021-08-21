package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BabbleGuiController implements Initializable {
	@FXML private Label labelTiles;
	@FXML private ListView<String> listViewTiles;
	@FXML private Label labelYourWord;
	@FXML private TextField textFieldYourWord;
	@FXML private Button buttonReset;
	@FXML private Button buttonPlayWord;
	@FXML private Label labelScore;
	@FXML private TextField textFieldScore;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
