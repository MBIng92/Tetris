import java.util.ArrayList;

import processing.core.PApplet;

//including informations of the current game
public class Window {
	private PApplet processing;
	private Settings settings;
	private Game game;
	private String window;
	private Background background;

	// all Buttons
	private Button menuStart;
	private Button menuSettings;
	private Button menuExit;
	private Button menuScores;
	private Button settingsBack;
	private Button settingsGeneral;
	private Button settingsName;
	private Button settingsColor;
	private Button gameStart;
	private Button gameRestart;
	private Button gameMenu;
	private Button gamePause;
	private Button sizeName;
	private Button sizeSize1;
	private Button sizeSize2;
	private Button sizeSize3;
	private Button sizeSize4;
	private Button nameBack;
	private Button scoresBack;
	private Button generalBack;
	private Button colorBack;
	private ArrayList<Button> startLevel;
	private ArrayList<Button> maxLevel;

	// all write buttons
	private WriteButton writeName;

	public Window(PApplet processing, Settings settings, Game game) {
		this.processing = processing;
		this.settings = settings;
		this.game = game;
		this.background = new Background(processing, settings);
		window = "menu";// set window to "menu" at start
		resize(); // creates buttons, set sizes, positions, names etc.
	}

	// set Buttons size, position, names, etc.
	private void resize() {
		int buttonSizeX = settings.getWidth() / 4;
		int buttonSizeY = settings.getHeight() / 8;
		int textSize = settings.getHeight() / 24;
		int correctionFactor = textSize / 8;

		// all Buttons
		menuStart = new Button(processing, settings, "Start", settings.getWidth() / 2, settings.getHeight() / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		menuScores = new Button(processing, settings, "Scores", settings.getWidth() / 2, settings.getHeight() * 2 / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		menuSettings = new Button(processing, settings, "Settings", settings.getWidth() / 2,
				settings.getHeight() * 3 / 5, buttonSizeX, buttonSizeY, textSize, correctionFactor);
		menuExit = new Button(processing, settings, "Exit", settings.getWidth() / 2, settings.getHeight() * 4 / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		settingsBack = new Button(processing, settings, "Menu", settings.getWidth() / 2, settings.getHeight() / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		settingsGeneral = new Button(processing, settings, "General", settings.getWidth() / 2,
				settings.getHeight() * 2 / 5, buttonSizeX, buttonSizeY, textSize, correctionFactor);
		settingsName = new Button(processing, settings, "Name", settings.getWidth() / 2, settings.getHeight() * 3 / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		settingsColor = new Button(processing, settings, "Color", settings.getWidth() / 2, settings.getHeight() * 4 / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		gameStart = new Button(processing, settings, "Start", settings.getWidth() * 6 / 7, settings.getHeight() / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		gameRestart = new Button(processing, settings, "Restart", settings.getWidth() * 6 / 7,
				settings.getHeight() * 2 / 5, buttonSizeX, buttonSizeY, textSize, correctionFactor);
		gameMenu = new Button(processing, settings, "Menu", settings.getWidth() * 6 / 7, settings.getHeight() * 4 / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		gamePause = new Button(processing, settings, "Pause", settings.getWidth() * 6 / 7, settings.getHeight() * 3 / 5,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		nameBack = new Button(processing, settings, "Back", settings.getWidth() / 2, settings.getHeight() / 3,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		scoresBack = new Button(processing, settings, "Back", settings.getWidth() / 2, settings.getHeight() / 6,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		generalBack = new Button(processing, settings, "Back", settings.getWidth() / 2, settings.getHeight() / 6,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		colorBack = new Button(processing, settings, "Back", settings.getWidth() / 2, settings.getHeight() / 6,
				buttonSizeX, buttonSizeY, textSize, correctionFactor);
		// all write buttons
		writeName = new WriteButton(processing, settings, settings.getName(), settings.getWidth() / 2,
				settings.getHeight() * 2 / 3, buttonSizeX * 2, buttonSizeY, textSize, correctionFactor);

		int smallButtonSizeX = settings.getWidth() / (settings.getMaxLevelFix() * 2 / 3);
		int smallButtonSizeY = settings.getHeight() / (settings.getMaxLevelFix() * 2);
		int positionX1 = settings.getWidth() * 1 / 5;
		int positionX2 = settings.getWidth() * 2 / 5;
		int positionX3 = settings.getWidth() * 4 / 5;
		int positionY = settings.getHeight() * 7 / 24;
		int distanceY = smallButtonSizeY * 5 / 4;
		int smallTextSize = settings.getHeight() / (settings.getMaxLevelFix() * 3);
		int smallCorrectionFactor = smallTextSize / 8;

		startLevel = new ArrayList<Button>();
		for (int i = 0; i < settings.maxLevelFix + 1; i++) {
			if (i == 0) {
				startLevel.add(new Button(processing, settings, "Start Level", positionX1, positionY + i * distanceY,
						smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor));
				startLevel.get(i).setNonActive(true);
			} else {
				startLevel
						.add(new Button(processing, settings, String.valueOf(i), positionX1, positionY + i * distanceY,
								smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor));
				if (i == settings.getStartLevel()) {
					startLevel.get(i).setState(true);
				}
			}
		}
		maxLevel = new ArrayList<Button>();
		for (int i = 0; i < settings.maxLevelFix + 1; i++) {
			if (i == 0) {
				maxLevel.add(new Button(processing, settings, "Max Level", positionX2, positionY + i * distanceY,
						smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor));
				maxLevel.get(i).setNonActive(true);
			} else {
				maxLevel.add(new Button(processing, settings, String.valueOf(i), positionX2, positionY + i * distanceY,
						smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor));
				if (i == settings.getMaxLevel()) {
					maxLevel.get(i).setState(true);
				}
			}
		}

		sizeName = new Button(processing, settings, "Size", positionX3, positionY + 0 * distanceY, smallButtonSizeX,
				smallButtonSizeY, smallTextSize, smallCorrectionFactor);
		sizeName.setNonActive(true);
		sizeSize1 = new Button(processing, settings, settings.getScreenSize(1), positionX3, positionY + 1 * distanceY,
				smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor);
		if (settings.getScreenSize(1).equals(settings.getCurrentScreenSize())) {
			sizeSize1.setState(true);
		}
		sizeSize2 = new Button(processing, settings, settings.getScreenSize(2), positionX3, positionY + 2 * distanceY,
				smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor);
		if (settings.getScreenSize(2).equals(settings.getCurrentScreenSize())) {
			sizeSize2.setState(true);
		}
		sizeSize3 = new Button(processing, settings, settings.getScreenSize(3), positionX3, positionY + 3 * distanceY,
				smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor);
		if (settings.getScreenSize(3).equals(settings.getCurrentScreenSize())) {
			sizeSize3.setState(true);
		}
		sizeSize4 = new Button(processing, settings, settings.getScreenSize(4), positionX3, positionY + 4 * distanceY,
				smallButtonSizeX, smallButtonSizeY, smallTextSize, smallCorrectionFactor);
		if (settings.getScreenSize(4).equals(settings.getCurrentScreenSize())) {
			sizeSize4.setState(true);
		}
	}

	// get current window state
	public String getWindow() {
		return window;
	}

	// click left mouse button - all actions
	public void actionMouseLeft() {
		if (window.equals("menu")) {
			// go to game
			if (menuStart.mouseOverButton()) {
				window = "game";
			}
			// go to game
			if (menuScores.mouseOverButton()) {
				window = "scores";
			}
			// go to settings
			else if (menuSettings.mouseOverButton()) {
				window = "settings";
			}
			// exit game
			else if (menuExit.mouseOverButton()) {
				processing.exit();
			}
		} else if (window.equals("settings")) {
			// go to menu
			if (settingsBack.mouseOverButton()) {
				window = "menu";
			}
			// go to general Settings window
			else if (settingsGeneral.mouseOverButton()) {
				window = "generalSettings";
			}
			// go to write name window
			else if (settingsName.mouseOverButton()) {
				window = "writeName";
			}
			// go to the background color window
			else if (settingsColor.mouseOverButton()) {
				window = "backgroundColor";
			}
		} else if (window.equals("game")) {
			// starting the game
			if ((gameStart.mouseOverButton() && processing.mouseButton == processing.LEFT)
					|| (processing.keyPressed && processing.key == 's')) {
				if (game.gameOver() == true) {
					game.resetGame();
					game.initNewGame();
				}
				game.setRunning();
			}
			// restart the game
			else if ((gameRestart.mouseOverButton() && processing.mouseButton == processing.LEFT)
					|| (processing.keyPressed && processing.key == 'r')) {
				if (game.gameOver() == true || game.getScore() == 0) {
					game.resetGame();
					game.initNewGame();
				} else {
					game.addScore();
					game.setHighScore();
					game.resetGame();
					game.initNewGame();
				}
			}
			// pause current game and go to menu
			else if (gameMenu.mouseOverButton() && (processing.mouseButton == processing.LEFT)) {
				if (game.gameOver() == false) {
					game.setPause();
				}
				window = "menu";
			}
			// pause the game
			else if ((gamePause.mouseOverButton() && processing.mouseButton == processing.LEFT)
					|| (processing.keyPressed && processing.key == 'p')) {
				if (game.gameOver() == false) {
					game.setPause();
				}
			}

		} else if (window.equals("generalSettings")) {
			// go to settings
			if (generalBack.mouseOverButton()) {
				window = "settings";
			}
			// set window size 1
			else if (sizeSize1.mouseOverButton()) {
				settings.setSize(1);
				resize();
				game.refresh();
				background.refresh();
			}
			// set window size 2
			else if (sizeSize2.mouseOverButton()) {
				settings.setSize(2);
				resize();
				game.refresh();
				background.refresh();
			}
			// set window size 3
			else if (sizeSize3.mouseOverButton()) {
				settings.setSize(3);
				resize();
				game.refresh();
				background.refresh();
			}
			// set window size 4
			else if (sizeSize4.mouseOverButton()) {
				settings.setSize(4);
				resize();
				game.refresh();
				background.refresh();
			}
			for (int i = 0; i < startLevel.size(); i++) {
				if (startLevel.get(i).mouseOverButton()) {
					settings.setMinLevel(Integer.parseInt(startLevel.get(i).getText()));
					resize();
					if (game.gameOver() == true || game.getScore() == 0) {
						game.resetGame();
						game.initNewGame();
					} else {
						game.addScore();
						game.setHighScore();
						game.resetGame();
						game.initNewGame();
					}
				}
			}
			for (int i = 0; i < maxLevel.size(); i++) {
				if (maxLevel.get(i).mouseOverButton()) {
					settings.setMaxLevel(Integer.parseInt(maxLevel.get(i).getText()));
					resize();
					if (game.gameOver() == true || game.getScore() == 0) {
						game.resetGame();
						game.initNewGame();
					} else {
						game.addScore();
						game.setHighScore();
						game.resetGame();
						game.initNewGame();
					}
				}
			}
		} else if (window.equals("writeName")) {
			// go to settings (just works when name is written/ writeName - State == false)
			if (nameBack.mouseOverButton() && writeName.getTypeNameState() == false) {
				// reset the game after writing the new name
				if (game.gameOver() == true || game.getScore() == 0) {
					game.resetGame();
					game.initNewGame();
				} else {
					game.addScore();
					game.setHighScore();
					game.resetGame();
					game.initNewGame();
				}
				window = "settings";
			}
			// after click: write the users name
			else if (writeName.mouseOverButton()) {
				writeName.setTypeNameState(true);
				writeName.nameReset();
			}
		} else if (window.equals("backgroundColor")) {
			if (colorBack.mouseOverButton()) {
				window = "settings";
			}
		} else if (window.equals("scores")) {
			// go to menu
			if (scoresBack.mouseOverButton()) {
				window = "menu";
			}
		}
	}

	// press left mouse button - all actions
	public void mouseDragged() {
		if (window.equals("backgroundColor")) {
			if (background.mouseOverSliderRed()) {
				background.action();
			} else if (background.mouseOverSliderGreen()) {
				background.action();
			} else if (background.mouseOverSliderBlue()) {
				background.action();
			}
		}
	}

	// type the characters into the writeName Button object
	public void typeName_WriteName(char c) {
		writeName.typeName(c);
	}

	// delete the last character of the button writeName
	public void deleteLastChar_WriteName() {
		writeName.deleteLastChar();
	}

	// set the state "name get typed" (true) or name typed (false)
	public void setTypeNameState_WriteName(boolean state) {
		writeName.setTypeNameState(state);
	}

	// returns the name of the button "writeName" (=WriteButton)
	public String getName_WriteName() {
		return writeName.getText();
	}

	// display the different windows
	public void display() {
		background.display();
		if (window.equals("menu")) {
			menuStart.display();
			menuScores.display();
			menuSettings.display();
			menuExit.display();
			displayNameMenu();
			displayHighScoreMenu();
		} else if (window.equals("settings")) {
			settingsBack.display();
			settingsGeneral.display();
			settingsName.display();
			settingsColor.display();
		} else if (window.equals("game")) {
			gameStart.display();
			gameRestart.display();
			gameMenu.display();
			gamePause.display();
			displayCourt();
			displayBackgroundNextElements();
			displayBackgroundScore();
			displayBackgroundLevel();
			displayBackgroundHighScore();
			game.display();
		} else if (window.equals("backgroundColor")) {
			colorBack.display();
			background.displaySliders();
		} else if (window.equals("generalSettings")) {
			generalBack.display();
			sizeName.display();
			sizeSize1.display();
			sizeSize2.display();
			sizeSize3.display();
			sizeSize4.display();
			for (int i = 0; i < startLevel.size(); i++) {
				startLevel.get(i).display();
			}
			for (int i = 0; i < maxLevel.size(); i++) {
				maxLevel.get(i).display();
			}
		} else if (window.equals("writeName")) {
			nameBack.display();
			writeName.display();
		} else if (window.equals("scores")) {
			scoresBack.display();
			settings.displayScoresList();
		}
	}

	private void displayCourt() {
		processing.rectMode(processing.CENTER);
		processing.stroke(settings.getColor(5));
		processing.strokeWeight(1);
		processing.fill(settings.getColor(1));
		for (int i = 0; i < settings.getFieldSizeX(); i++) {
			for (int j = 0; j < settings.getFieldSizeY(); j++) {
				processing.square(settings.getCoordinateX(i), settings.getCoordinateY(j), settings.getSquareDistance());
			}
		}
		processing.stroke(settings.getColor(4));
		processing.strokeWeight((float) 1.5);
		processing.line(settings.getCoordinateX(0) - settings.getSquareDistance() / 2,
				settings.getCoordinateY(0) - settings.getSquareDistance() / 2,
				settings.getCoordinateX(0) - settings.getSquareDistance() / 2,
				settings.getCoordinateY(settings.getFieldSizeY() - 1) + settings.getSquareDistance() / 2);
		processing.line(settings.getCoordinateX(settings.getFieldSizeX() - 1) + settings.getSquareDistance() / 2,
				settings.getCoordinateY(0) - settings.getSquareDistance() / 2,
				settings.getCoordinateX(settings.getFieldSizeX() - 1) + settings.getSquareDistance() / 2,
				settings.getCoordinateY(settings.getFieldSizeY() - 1) + settings.getSquareDistance() / 2);
		processing.line(settings.getCoordinateX(0) - settings.getSquareDistance() / 2,
				settings.getCoordinateY(settings.getFieldSizeY() - 1) + settings.getSquareDistance() / 2,
				settings.getCoordinateX(settings.getFieldSizeX() - 1) + settings.getSquareDistance() / 2,
				settings.getCoordinateY(settings.getFieldSizeY() - 1) + settings.getSquareDistance() / 2);
	}

	private void displayBackgroundNextElements() {
		processing.rectMode(processing.CORNERS);
		processing.noStroke();
		processing.fill(settings.getColor(1));
		float posX1 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 1);
		float posY1 = settings.getFieldPosY() + (settings.getSquareDistance() * ((float) -0.5));
		float posX2 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 6);
		float posY2 = settings.getSquareDistance() * 11;
		processing.rect(posX1, posY1, posX2, posY2);

		processing.textSize(settings.getHeight() / 24);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(4));
		float x = (posX2 - posX1) / 2 + posX1;
		float y = posY1 + settings.getSquareDistance() * (float) 0.7;
		processing.text("Next", x, y);
	}

	private void displayNameMenu() {
		processing.rectMode(processing.CENTER);
		processing.noStroke();
		processing.fill(settings.getColor(1));
		float posX = settings.getWidth() * 5 / 6;
		float posY = settings.getHeight() * 1 / 3;
		float sizeX = settings.getWidth() / 4;
		float sizeY = settings.getHeight() / 9;
		processing.rect(posX, posY, sizeX, sizeY);

		processing.textSize(settings.getHeight() / 24);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(4));
		float x = posX;
		float y = posY - sizeY * 3 / 10;
		processing.text("Name", x, y);
		y = posY + sizeY * 2 / 9;
		processing.textSize(settings.getHeight() / 28);
		processing.text(settings.getName(), x, y);
	}

	private void displayHighScoreMenu() {
		processing.rectMode(processing.CENTER);
		processing.noStroke();
		processing.fill(settings.getColor(1));
		float posX = settings.getWidth() * 5 / 6;
		float posY = settings.getHeight() * 2 / 3;
		float sizeX = settings.getWidth() / 4;
		float sizeY = settings.getHeight() / 9;
		processing.rect(posX, posY, sizeX, sizeY);

		processing.textSize(settings.getHeight() / 24);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(4));
		float x = posX;
		float y = posY - sizeY * 3 / 10;
		processing.text("Highscore", x, y);
		// converting scores from XXXXXXXXX to XXX.XXX.XXX
		processing.textSize(settings.getHeight() / 28);
		String s = String.valueOf(settings.getHighScore());
		StringBuffer sScore = new StringBuffer(s);
		int counter = 0;
		for (int j = sScore.length(); j > 0; j--) {
			if (counter == 3) {
				sScore.insert(j, ".");
				counter = 0;
			}
			counter++;
		}
		y = posY + sizeY * 2 / 9;
		processing.textSize(settings.getHeight() / 28);
		processing.text(sScore.toString(), x, y);
	}

	private void displayBackgroundScore() {
		processing.rectMode(processing.CORNERS);
		processing.noStroke();
		processing.fill(settings.getColor(1));
		float posX1 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 1);
		float posY1 = settings.getSquareDistance() * 12;
		float posX2 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 6);
		float posY2 = settings.getSquareDistance() * 15;
		processing.rect(posX1, posY1, posX2, posY2);

		processing.textSize(settings.getHeight() / 24);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(4));
		float x = (posX2 - posX1) / 2 + posX1;
		float y = posY1 + settings.getSquareDistance() * (float) 0.7;
		processing.text("Score", x, y);
		// converting scores from XXXXXXXXX to XXX.XXX.XXX
		processing.textSize(settings.getHeight() / 28);
		String s = String.valueOf(game.getScore());
		StringBuffer sScore = new StringBuffer(s);
		int counter = 0;
		for (int j = sScore.length(); j > 0; j--) {
			if (counter == 3) {
				sScore.insert(j, ".");
				counter = 0;
			}
			counter++;
		}
		y = posY1 + settings.getSquareDistance() * (float) 2;
		processing.text(sScore.toString(), x, y);
	}

	private void displayBackgroundLevel() {
		processing.rectMode(processing.CORNERS);
		processing.noStroke();
		processing.fill(settings.getColor(1));
		float posX1 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + (float) 6.5);
		float posY1 = settings.getSquareDistance() * 12;
		float posX2 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 9);
		float posY2 = settings.getSquareDistance() * 15;
		processing.rect(posX1, posY1, posX2, posY2);

		processing.textSize(settings.getHeight() / 24);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(4));
		float x = (posX2 - posX1) / 2 + posX1;
		float y = posY1 + settings.getSquareDistance() * (float) 0.7;
		processing.text("Level", x, y);
		// display current level
		processing.textSize(settings.getHeight() / 28);
		y = posY1 + settings.getSquareDistance() * (float) 2;
		processing.text(game.getLevel(), x, y);
	}

	private void displayBackgroundHighScore() {
		processing.rectMode(processing.CORNERS);
		processing.noStroke();
		processing.fill(settings.getColor(1));
		float posX1 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 1);
		float posY1 = settings.getSquareDistance() * 16;
		float posX2 = settings.getFieldPosX() + settings.getSquareDistance() * (settings.getFieldSizeX() + 6);
		float posY2 = settings.getSquareDistance() * 19;
		processing.rect(posX1, posY1, posX2, posY2);

		processing.textSize(settings.getHeight() / 24);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(4));
		float x = (posX2 - posX1) / 2 + posX1;
		float y = posY1 + settings.getSquareDistance() * (float) 0.7;
		processing.text("Highscore", x, y);
		// converting scores from XXXXXXXXX to XXX.XXX.XXX
		processing.textSize(settings.getHeight() / 28);
		String s = String.valueOf(settings.getHighScore());
		StringBuffer sScore = new StringBuffer(s);
		int counter = 0;
		for (int j = sScore.length(); j > 0; j--) {
			if (counter == 3) {
				sScore.insert(j, ".");
				counter = 0;
			}
			counter++;
		}
		y = posY1 + settings.getSquareDistance() * (float) 2;
		processing.text(sScore.toString(), x, y);
	}
}
