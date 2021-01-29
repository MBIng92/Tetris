import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.IntDict;

import java.lang.Math;

//including informations of the current game
public class Game {
	private PApplet processing;
	private Settings settings;
	private Timer timer;
	private Tetromino tetromino;

	private Score score; // including current score informations

	private ArrayList<Square> squareList = new ArrayList<Square>(); // including fixed squares
	private ArrayList<Tetromino> nextTetrominos = new ArrayList<Tetromino>(); // including the next three tetrominos

	// Current game stats
	private boolean gameRunning = false; // status of the game (running or not)
	private boolean gameOver = false; // if true --> game Over
	private boolean gamePaused = false; // if true --> game Paused

	protected int nextTetrominoAmount = 3;

	private int level; // level of the current game
	private int speed; // current speed in milliseconds (depending on the level)
	private int rowCounter; // after n rows (defined in the settings) --> level up, maximum level defined
							// also in the settings
	private boolean fixingAllowed = false;

	public Game(PApplet processing, Settings settings) {
		this.processing = processing;
		this.settings = settings;
		this.timer = new Timer(processing);
		// create the first tetromino
		this.tetromino = new Tetromino(processing, settings);
		createNextTetrominos();
		this.score = new Score(settings.getName());
		initNewGame();
	}

	public void gameRun() {
		if (gameRunning == true) {
			timer.startTimer();
			// frequency
			if (timer.timeReachedMill(speed)) {
				// set back the correct color back from set effect
				for (int i = 0; i < squareList.size(); i++) {
					if ((squareList.get(i).getColorMode() == true)) {
						squareList.get(i).setColorMode(false);
					}
				}
				// moving down or fix tetromino (after 2x speed time)
				if (tetromino.moveDownAllowed(squareList) == false) {
					if (fixingAllowed == true) {
						fixTetromino();
						timer.reset();
						fixingAllowed = false;
					} else {
						fixingAllowed = true;
						timer.reset();
					}
				} else {
					moveDown();
					timer.reset();
					fixingAllowed = false;
				}
			}
		}
		// set back the correct color back from set effect
		else if (gameOver == true || gamePaused == true) {
			for (int i = 0; i < squareList.size(); i++) {
				if ((squareList.get(i).getColorMode() == true)) {
					squareList.get(i).setColorMode(false);
				}
			}
		}
	}

	// returns state of game Running
	public boolean gameRunning() {
		return gameRunning;
	}

	// returns state of game Over
	public boolean gameOver() {
		return gameOver;
	}

	// returns state of game paused
	public boolean gamePaused() {
		return gamePaused;
	}

	public void setRunning() {
		gameRunning = true;
		gameOver = false;
		gamePaused = false;
	}

	public void setPause() {
		gameRunning = false;
		gameOver = false;
		gamePaused = true;
	}

	public void setGameOver() {
		gameRunning = false;
		gameOver = true;
		gamePaused = false;
	}

	// initialize a new game (get start level, set score, set level, set pause)
	public void initNewGame() {
		// initialize level
		level = settings.getStartLevel();
		rowCounter = 0;
		// reset the score
		this.score.setScore(0, settings.getName(), 0, level);
		// set Level, set speed depending on the level, set rowCounter to 0
		setLevel(level);
		// set the game stats
		setPause();
	}

	// set the new high score (if availlable)
	public void setHighScore() {
		settings.setHighScore();
	}

	// add score to score list, sort it, write it, clear it and read it (clean way)
	public void addScore() {
		settings.addScore(score);
	}

	// reset the game (clear squareList, create new tetromino and create next
	// tetrominos)
	public void resetGame() {
		// clear squareList
		for (int i = squareList.size() - 1; i >= 0; i--) {
			squareList.remove(i);
		}
		// create new Tetromino
		this.tetromino = new Tetromino(processing, settings);
		createNextTetrominos();
	}

	// set Level, set speed depending on the level, set rowCounter to 0
	private void setLevel(int level) {
		// increase just the level when level < defined maximum level
		if (level <= settings.getMaxLevel()) {
			// set level
			this.level = level;
		}
		// set speed depending on the level
		speed = (int) (settings.getStartSpeed() * Math.pow(0.85, (level - 1)));
		settings.setFixingTime(speed);
		// set row counter to 0
		rowCounter = 0;
	}

	// delete full rows and calculate the score depending on the current level
	private void deleteRows() {
		// searching for full rows and count them
		int delRows = 0;
		if (squareList.size() > 0) {
			int minLine = squareList.get(0).getPosY(); // smallest line number
			int maxLine = squareList.get(0).getPosY(); // highest line number
			IntDict lines = new IntDict(); // create an IntDict with line numbers and the amount of squares in every
											// line
			for (int i = 0; i < squareList.size(); i++) {
				// check whether posY already exists in the IntDict --> if not --> create a new
				// entry
				if (lines.hasKey(String.valueOf(squareList.get(i).getPosY())) == false) {
					if (squareList.get(i).getPosY() > maxLine) {
						maxLine = squareList.get(i).getPosY();
					}
					if (squareList.get(i).getPosY() < minLine) {
						minLine = squareList.get(i).getPosY();
					}
					lines.set(String.valueOf(squareList.get(i).getPosY()), 1); // creates the first entry for this line
																				// and sets the counter to 1
				} else {
					lines.increment(String.valueOf(squareList.get(i).getPosY())); // increases the amount of squares in
																					// this row by one
				}
			}
			// iterate from min line to max line and delete
			for (int i = minLine; i <= maxLine; i++) {
				// if line exists
				if (lines.hasKey(String.valueOf(i)) == true) {
					// if line is full --> iterate through the squareList and delete the rows and
					// move up the overlying squares
					if (lines.get(String.valueOf(i)) == settings.getFieldSizeX()) {
						delRows++; // increase the amount of deleted rows (maximum 4)
						// iterate through the squareList and delete all squares with i = posY of the
						// square
						for (int j = squareList.size() - 1; j >= 0; j--) {
							if (squareList.get(j).getPosY() == i) {
								squareList.remove(j);
							}
						}
						// iterate through the squareList again and move down (+1) all squares with posY
						// < i
						for (int j = 0; j < squareList.size(); j++) {
							if (squareList.get(j).getPosY() < i) {
								squareList.get(j).setPos(squareList.get(j).getPosX(), squareList.get(j).getPosY() + 1);
							}
						}
					}
				}
			}
		}
		// increase the score and write it in the score object
		int n = 100;
		int nLevel = n * this.level;
		int add = nLevel * (delRows - 1);
		for (int i = 2; i <= delRows; i++) {
			nLevel = nLevel * 2;
		}
		score.setScore(0, settings.getName(), score.getScore() + nLevel + add, level); // set the new score
		rowCounter = rowCounter + delRows;
		if (rowCounter >= settings.getRowsToLevelUp()) {
			setLevel(level + 1);
		}
	}

	// function for fixing the tetromino
	private void fixTetromino() {
		// give the squares of the current tetromino in the squareList
		tetromino.fixTetromino(squareList);

		// for the setting effect (short color change)
		for (int i = squareList.size() - 1; i >= squareList.size() - 4; i--) {
			squareList.get(i).setColorMode(true);
		}

		// delete the rows if necessary and move down the upper squares
		deleteRows();

		// test whether the next tetromino fits, if not - game Over! else set the next
		// tetromino and calculate the next tetrominos
		if (nextTetrominos.get(0).testCollision(squareList) == true) {
			setGameOver();
			addScore();
			setHighScore();
		} else {
			this.tetromino.overWrite(nextTetrominos.get(0).getTetrominoNumber());
			this.tetromino.setNewPos(this.tetromino.getStartPosX(), this.tetromino.getStartPosY());
			for (int i = 0; i < nextTetrominos.size() - 1; i++) {
				nextTetrominos.get(i).overWrite(nextTetrominos.get(i + 1).getTetrominoNumber());
			}
			nextTetrominos.get(nextTetrominos.size() - 1).overWrite((int) processing.random(1, 8));
		}
	}

	private void createNextTetrominos() {
		// clear nextTetromino list
		for (int i = nextTetrominos.size() - 1; i >= 0; i--) {
			nextTetrominos.remove(i);
		}
		// create new nextTetrominos list
		for (int i = 0; i < nextTetrominoAmount; i++) {
			nextTetrominos.add(new Tetromino(processing, settings, (int) processing.random(1, 8),
					settings.getFieldSizeX() + 2, (i * 3) + 2));
		}
	}

	public int getScore() {
		return score.getScore();
	}

	public int getLevel() {
		return level;
	}

	// function to refresh the tetromino size after changing window size
	public void refresh() {
		tetromino.refresh();
		for (int i = 0; i < squareList.size(); i++) {
			squareList.get(i).setPos(squareList.get(i).getPosX(), squareList.get(i).getPosY());
		}
		for (int i = 0; i < nextTetrominos.size(); i++) {
			nextTetrominos.get(i).refresh();
		}
	}

	public void moveDown() {
		tetromino.moveDown(squareList);
	}

	public void moveLeft() {
		tetromino.moveLeft(squareList);
	}

	public void moveRight() {
		tetromino.moveRight(squareList);
	}

	public void rotateLeft() {
		tetromino.rotateLeft(squareList);
	}

	public void rotateRight() {
		tetromino.rotateRight(squareList);
	}

	public void display() {
		tetromino.display();
		// displaying the suqareList
		for (int i = 0; i < squareList.size(); i++) {
			if (squareList.get(i).getPosY() >= 0) {
				squareList.get(i).display();
			}
		}

		// displaying next elements
		for (int i = 0; i < nextTetrominos.size(); i++) {
			nextTetrominos.get(i).display();
		}
		if (gamePaused) {
			processing.textSize(settings.getHeight() / 10);
			processing.textAlign(processing.CENTER, processing.CENTER);
			processing.fill(settings.getColor(4));
			float x = settings.getCoordinateX(settings.getFieldSizeX() / 2) - settings.getSquareDistance() / 2;
			float y = settings.getCoordinateY(settings.getFieldSizeY() / 2) - settings.getSquareDistance() / 2;
			processing.text("Pause", x, y);
		}
		if (gameOver) {
			processing.textSize(settings.getHeight() / 10);
			processing.textAlign(processing.CENTER, processing.CENTER);
			processing.fill(settings.getColor(4));
			float x = settings.getCoordinateX(settings.getFieldSizeX() / 2) - settings.getSquareDistance() / 2;
			float y = settings.getCoordinateY(settings.getFieldSizeY() / 2) - settings.getSquareDistance() / 2;
			processing.text("Game\nOver", x, y);
		}
	}

}
