import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;

import processing.core.PApplet;
import processing.data.IntList;

//includes general settings
public class Settings {
	private PApplet processing;

	// users name
	private Name name; // including name
	// score list
	private ScoreList scoreList; // including all scores
	// screen size
	private ScreenSize screenSize;
	// Game settings
	private int fixingTime = 0; // time, the fixing animation get displayed in milliseconds
	protected int maxLevelFix = 16; // maximum reachable level
	private int maxLevel; // set maximum reachable level
	private int startLevel = 1; // start Level
	private int startSpeed = 800; // start value of the timer in milliseconds
	private int rowsToLevelUp = 10; // if 10 or more rows are deleted --> increase the game level
	private Score highScore; // includes high score informations
	// position informations of the court
	private int squareDistance; // distance between the squares (define size of the court)!
	private int fieldPosX; // start position of the field (center of the top left square)
	private int fieldPosY; // start position of the field (center of the top left square)
	protected int fieldSizeX = 10; // court width in X (amount of squares)
	protected int fieldSizeY = 20; // court height in Y (amount of squares)
	// colors
	private IntList colors;
	private float transparency = 240;
	private int fillColorBright;
	private int textColorBright;
	private int fillColorDark;
	private int textColorDark;
	private int fillColorMiddle;
	private int textColorMiddle;
	private int fillColorActivated;
	private int color1;
	private int color2;
	private int color3;
	private int color4;
	private int color5;
	private int color6;
	private int color7;

	public Settings(PApplet processing) {
		this.processing = processing;
		this.maxLevel = maxLevelFix;
		colors = new IntList();
		fillColorBright = processing.color(150, 150, 150, transparency); // color #1
		colors.append(fillColorBright);
		textColorBright = processing.color(230, 230, 230, transparency); // color #2
		colors.append(textColorBright);
		fillColorDark = processing.color(60, 60, 60, transparency); // color #3
		colors.append(fillColorDark);
		textColorDark = processing.color(20, 20, 20, transparency); // color #4
		colors.append(textColorDark);
		fillColorMiddle = processing.color(200, 200, 200, transparency); // color #5
		colors.append(fillColorMiddle);
		textColorMiddle = processing.color(70, 70, 70, transparency); // color #6
		colors.append(textColorMiddle);
		fillColorActivated = processing.color(45, 177, 76, transparency); // color #7
		colors.append(fillColorActivated);
		color1 = processing.color(0, 255, 200); // color #8 for object 1: ####
		colors.append(color1);
		color2 = processing.color(200, 0, 255); // color #9 for object 2: ###
												// 						   #
		colors.append(color2);
		color3 = processing.color(255, 200, 0); // color #10 for object 3: ##
												// 						   ##
		colors.append(color3);
		color4 = processing.color(255, 128, 0); // color #11 for object 4: ###
												// 						   #
		colors.append(color4);
		color5 = processing.color(0, 100, 255); // color #12 for object 5: ###
												// 							 #
		colors.append(color5);
		color6 = processing.color(255, 0, 0); // color #13 for object 6: ##
												// 						  ##
		colors.append(color6);
		color7 = processing.color(0, 190, 0); // color #14 for object 7: ##
												// 					    ##
		colors.append(color7);

		screenSize = new ScreenSize(processing);
		setSquareDistance();
		scoreList = new ScoreList(processing, getColor(1), getColor(4));
		setHighScore();
		readMinMaxLevel();
		name = new Name();
	}

	// sets the square distance and other court parameters depending on the screen
	// size and the amount of the squares in x and y!
	// squareDistance have to be an even number!
	public void setSquareDistance() {
		double y = getHeight() / (fieldSizeY + 1);
		Math.round(y); // round y
		// check whether y is even or odd (if odd --> increase the size by 1)
		if (y % 2 != 0) {
			this.squareDistance = (int) y + 1;
		} else {
			this.squareDistance = (int) y;
		}
		int sideDistance = (getHeight() - (fieldSizeY * squareDistance)) / 2;
		// check whether sideDistance is even or odd (if odd --> decrease by 1)
		if (sideDistance % 2 != 0) {
			sideDistance = sideDistance - 1;
		}
		// set field position X
		this.fieldPosX = squareDistance / 2 + sideDistance * 2;
		// check whether fieldPosX is even or odd (if odd --> decrease by 1)
		if (this.fieldPosX % 2 != 0) {
			this.fieldPosX = fieldPosX - 1;
		}
		// set field position Y
		this.fieldPosY = squareDistance / 2 + sideDistance;
		// check whether fieldPosY is even or odd (if odd --> decrease by 1)
		if (this.fieldPosY % 2 != 0) {
			this.fieldPosY = fieldPosY - 1;
		}
	}

	// sets the fixing time
	public void setFixingTime(int t) {
		fixingTime = t;
	}

	// returns the fixing time
	public int getFixingTime() {
		return fixingTime;
	}

	// get the coordinate of position X (0...9)
	public int getCoordinateX(int i) {
		return fieldPosX + i * squareDistance;
	}

	// get the coordinate of position Y (0...19)
	public int getCoordinateY(int i) {
		return fieldPosY + i * squareDistance;
	}

	// returns field size in X
	public int getFieldSizeX() {
		return fieldSizeX;
	}

	// returns field size in Y
	public int getFieldSizeY() {
		return fieldSizeY;
	}

	// returns square distance (middle - middle)
	public int getSquareDistance() {
		return squareDistance;
	}

	// returns the field position of X start position of the field (center of the
	// top left square)
	public int getFieldPosX() {
		return fieldPosX;
	}

	// returns the field position of Y start position of the field (center of the
	// top left square)
	public int getFieldPosY() {
		return fieldPosY;
	}

	// add score in scoreList, sort the list and write in scores.txt
	public void addScore(Score score) {
		scoreList.addScore(score);
	}

	// returns start speed in milliseconds
	public int getStartSpeed() {
		return startSpeed;
	}

	// returns rows to level up
	public int getRowsToLevelUp() {
		return rowsToLevelUp;
	}

	// returns current start level
	public int getStartLevel() {
		return startLevel;
	}

	// returns current max level
	public int getMaxLevel() {
		return maxLevel;
	}

	// returns the fixed values of the max level
	public int getMaxLevelFix() {
		return maxLevelFix;
	}

	// returns the numbers color
	public int getColor(int number) {
		if (number <= colors.size() || number <= 0) {
			return colors.get(number - 1);
		} else {
			System.out.println("Color does not exist");
			return colors.get(0); // if number does not exist - return "fillColorBright"
		}
	}

	// sets the high score
	public void setHighScore() {
		highScore = scoreList.getHighScore();
	}

	public int getHighScore() {
		return highScore.getScore();
	}

	// display the score list in the List "scores" under settings
	public void displayScoresList() {
		scoreList.displayScoresList(getWidth(), getHeight());
	}

	// setting the name in the object "name" and writing it in the name.txt file
	public void setName(String name) {
		this.name.writeName(name);
	}

	// get screen Size (1...4) as String
	public String getScreenSize(int i) {
		return screenSize.getScreenSize(i);
	}

	// get current screen size as string
	public String getCurrentScreenSize() {
		return screenSize.getCurrentScreenSize();
	}

	// get users name
	public String getName() {
		return name.getName();
	}

	// get screen height
	public int getHeight() {
		return screenSize.getHeight();
	}

	// get screen width
	public int getWidth() {
		return screenSize.getWidth();
	}

	// Screen size operations
	public void setSize(int sizeNumber) {
		screenSize.setSize(sizeNumber);
		setSquareDistance();
	}

	// creates the minMaxLevel.txt file, writes startLevel and max level in this
	// file and sets int startLevel
	public void setMinLevel(int startLevel) {
		if (startLevel > maxLevelFix || startLevel <= 0) {
			startLevel = 1;
		}
		if (maxLevel > maxLevelFix || maxLevel <= 0) {
			maxLevel = maxLevelFix;
		}
		if (startLevel > maxLevel) {
			maxLevel = startLevel; // sets maxLevel to start level, if startLevel is bigger that the maxLevel
		}
		createMinMaxLevelFile();
		writeMinMaxLevelFile(startLevel, maxLevel);
	}

	// creates the minMaxLevel.txt file, writes startLevel and max level in this
	// file and sets int maxLevel
	public void setMaxLevel(int maxLevel) {
		if (startLevel > maxLevelFix || startLevel <= 0) {
			startLevel = 1;
		}
		if (maxLevel > maxLevelFix || maxLevel <= 0) {
			maxLevel = maxLevelFix;
		}
		if (maxLevel < startLevel) {
			startLevel = maxLevel; // set startLevel to max level, if startLevel is bigger that the maxLevel
		}
		createMinMaxLevelFile();
		writeMinMaxLevelFile(startLevel, maxLevel);
	}

	// creates the minMaxLevel.txt file, reads startLevel and max level from this
	// file and sets int startLevel and int maxLevel
	public void readMinMaxLevel() {
		createMinMaxLevelFile();
		readMinMaxLevelFile();
	}

	// reading minMaxLevel.txt file
	private void readMinMaxLevelFile() {
		try {
			File file = new File("data/minMaxLevel.txt");
			Scanner scanner = new Scanner(file);
			int startLevel = 0;
			int maxLevel = 0;
			int n = 0;
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (n == 0) {
					startLevel = Integer.parseInt(data);
				} else {
					maxLevel = Integer.parseInt(data);
				}
				n++;
			}
			if (startLevel > maxLevelFix || startLevel <= 0) {
				startLevel = 1;
			}
			if (maxLevel > maxLevelFix || maxLevel <= 0) {
				maxLevel = maxLevelFix;
			}
			if (maxLevel < startLevel) {
				startLevel = 1; // set startLevel and maxLevel to default
				maxLevel = maxLevelFix; // set startLevel and maxLevel to default
			}
			this.startLevel = startLevel;
			this.maxLevel = maxLevel;
			writeMinMaxLevelFile(startLevel, maxLevel);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// create minMaxLevel.txt file
	private void createMinMaxLevelFile() {
		try {
			File dataFile = new File("data");
			dataFile.mkdir();
			File file = new File("data/minMaxLevel.txt");
			if (file.createNewFile()) {
				writeMinMaxLevelFile(1, maxLevelFix);
				this.startLevel = 1;
				this.maxLevel = maxLevelFix;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// write minMaxLevel.txt file
	private void writeMinMaxLevelFile(int startLevel, int maxLevel) {
		try {
			FileWriter writer = new FileWriter("data/minMaxLevel.txt");
			this.startLevel = startLevel;
			this.maxLevel = maxLevel;
			writer.write(startLevel + "\n" + maxLevel);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
