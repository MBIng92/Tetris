import java.io.FileNotFoundException;

import processing.core.PApplet;

public class Tetris extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Tetris");
	}

	GameData gameData;

	// setting window size
	public void settings() {
		gameData = new GameData(this);
		size(gameData.getWidth(), gameData.getHeight());
	}

	public void setup() {
		frameRate(140); // try to create a high framerate of about 140
	}

	public void draw() {
		gameData.display();
		gameData.gameRun();
	}

	// mouse operations
	public void mouseClicked() {
		if (mouseButton == LEFT) {
			gameData.actionMouseLeft();
		}
	}

	public void mouseDragged() {
		if (mouseButton == LEFT) {
			gameData.mouseDragged();
		}
	}

	// key operations
	public void keyReleased() {
		if (gameData.getWindow().equals("writeName")) {
			if (keyCode == ENTER) {
				gameData.setTypeNameState_WriteName(false);
				gameData.setName(gameData.getName_WriteName());
			}
			if (keyCode == BACKSPACE) {
				gameData.deleteLastChar_WriteName();
			}
			if (key != ENTER && key != BACKSPACE) {
				gameData.typeName_WriteName(key);
			}
		}
	}

	public void keyPressed() {
		if (gameData.getWindow().equals("game")) {
			if (key == CODED && gameData.gameRunning()) {
				if (keyCode == UP) {
					gameData.rotateRight();
				}
				if (keyCode == DOWN) {
					gameData.moveDown();
				}
				if (keyCode == LEFT) {
					gameData.moveLeft();
				}
				if (keyCode == RIGHT) {
					gameData.moveRight();
				}
			}
			if (key == ' ' && gameData.gameRunning()) {
				gameData.rotateLeft();
			} else {
				gameData.actionMouseLeft();
			}
		}
	}
}
