import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import processing.core.PApplet;

public class GameData {
	private PApplet processing;

	// General game stats
	private Settings settings; // including general settings like Window Size
	private Game game; // including informations of the current game
	private Window window; // including window informations for the menu navigation

	// Constructor
	public GameData(PApplet processing) {
		this.processing = processing;
		settings = new Settings(processing);
		game = new Game(processing, settings);
		window = new Window(processing, settings, game);
	}

	// gameRun function
	public void gameRun() {
		game.gameRun();
	}

	// returns state gameRunning (true or false)
	public boolean gameRunning() {
		return game.gameRunning();
	}

	// change the stats of "game"
	public void setRunning() {
		game.setRunning();
	}

	// change the stats of "game"
	public void setPause() {
		game.setPause();
	}

	// resets the game
	public void resetGame() {
		game.resetGame();
		game.initNewGame();
	}

	/* Functions for settings */
	// get screen width
	public int getWidth() {
		return settings.getWidth();
	}

	// get screen height
	public int getHeight() {
		return settings.getHeight();
	}

	// set window size and write it into size.txt to save the data for the next
	// program start
	public void setSize(int sizeNumber) {
		settings.setSize(sizeNumber);
	}

	// setting the name in the object "name" (into settings) and writing it in the
	// name.txt file
	public void setName(String name) {
		settings.setName(name);
	}

	/* Functions for window */
	// returns window location (e.g. menu, settings, write name, ...)
	public String getWindow() {
		return window.getWindow();
	}

	// press left mouse button - all actions
	public void actionMouseLeft() {
		window.actionMouseLeft();
	}

	// press left mouse button - all actions
	public void mouseDragged() {
		window.mouseDragged();
	}

	// type the characters into the writeName Button object
	public void typeName_WriteName(char c) {
		window.typeName_WriteName(c);
	}

	// delete the last character of the button writeName
	public void deleteLastChar_WriteName() {
		window.deleteLastChar_WriteName();
	}

	// set the state "name get typed" (true) or name typed (false)
	public void setTypeNameState_WriteName(boolean state) {
		window.setTypeNameState_WriteName(state);
	}

	// returns the name of the button "writeName" (=WriteButton)
	public String getName_WriteName() {
		return window.getName_WriteName();
	}

	// display the windows
	public void display() {
		window.display();
	}

	/* Functions for Game */
	public void moveDown() {
		game.moveDown();
	}

	public void moveLeft() {
		game.moveLeft();
	}

	public void moveRight() {
		game.moveRight();
	}

	public void rotateLeft() {
		game.rotateLeft();
	}

	public void rotateRight() {
		game.rotateRight();
	}
}
