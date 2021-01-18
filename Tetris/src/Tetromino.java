import java.util.ArrayList;

import processing.core.PApplet;

public class Tetromino {
	private PApplet processing;
	private Settings settings;

	private int tetrominoNumber; // 1: #### 2: ### 3: U## 4: ### 5: ### 6: ## 7: U##
									// 			# 	   ##    #        #     ##   ##

	protected int startPosX = 3;
	protected int startPosY = 0;
	private int posX; // origin of the tetromino in X (//start position of the field (center of the
						// top left square or "U")
	private int posY; // origin of the tetromino in Y (//start position of the field (center of the
						// top left square or "U")

	private double rotationPointX; // x of the rotation point (relative position to posX)
	private double rotationPointY; // y of the rotation point (relative position to posY)

	private ArrayList<Square> squares = new ArrayList<Square>();

	public Tetromino(PApplet processing, Settings settings) {
		this.processing = processing;
		this.settings = settings;
		setPos(startPosX, startPosY); // start position of the tetrominos
		int i = (int) processing.random(1, 8);
		createTetromino(i);
	}

	// just used to create a background and to create the nextTetrominos list
	public Tetromino(PApplet processing, Settings settings, int i, int posX, int posY) {
		this.processing = processing;
		this.settings = settings;
		setPos(-50, 0); // start position of the tetrominos (outside of the screen prevent collision and
						// to enable rotation)
		if (i < 1 || i > 7) {
			i = 1;
		}
		createTetromino(i);
		setNewPos(posX, posY); // set the position of the tetromino
	}

	// using for overwriting the tetromino after fixing
	public void overWrite(int i) {
		// first clear the squares list
		for (int j = squares.size() - 1; j >= 0; j--) {
			squares.remove(j);
		}
		// position is already created and stays the same
		createTetromino(i);
	}

	public int getStartPosX() {
		return startPosX;
	}

	public int getStartPosY() {
		return startPosY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getTetrominoNumber() {
		return tetrominoNumber;
	}

	public int getColor() {
		return squares.get(0).getColor();
	}

	public void setColor(int color) {
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).setColor(color);
		}
	}

	public int getFixingColor() {
		return squares.get(0).getFixingColor();
	}

	public void setFixingColor(int color) {
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).setFixingColor(color);
		}
	}

	public void setColorMode(boolean state) {
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).setColorMode(state);
		}
	}

	public boolean getColorMode() {
		return squares.get(0).getColorMode();
	}

	// function to fix the squares of a tetromino in a squares list
	public void fixTetromino(ArrayList<Square> squareList) {
		for (int i = 0; i < squares.size(); i++) {
			squareList.add(squares.get(i));
		}
	}

	// function to refresh the tetromino size after changing window size
	public void refresh() {
		setPos(posX, posY);
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).setPos(squares.get(i).getPosX(), squares.get(i).getPosY());
		}
	}

	// sets a new position of the tetromino
	public void setNewPos(int posX, int posY) {
		int oldX = this.posX;
		int oldY = this.posY;
		setPos(posX, posY);
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).setPos(squares.get(i).getPosX() + (posX - oldX), squares.get(i).getPosY() + (posY - oldY));
		}
	}

	// set x and y of a position (for initializing of the tetromino)
	private void setPos(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public void display() {
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getPosY() >= 0) {
				squares.get(i).display();
			}
		}
	}

	public void setTransparency(int transparency) {
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).setTransparency(transparency);
		}
	}

	private void createTetromino(int i) {
		this.tetrominoNumber = i;
		// 1: #### 2: ### 3: U## 4: ### 5: ### 6: ## 7: U##
		// # ## # # ## ##
		switch (this.tetrominoNumber) {
		case 1:
			squares.add(new Square(processing, settings, posX, posY, settings.getColor(8)));
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(8)));
			squares.add(new Square(processing, settings, posX + 2, posY, settings.getColor(8)));
			squares.add(new Square(processing, settings, posX + 3, posY, settings.getColor(8)));
			this.rotationPointX = 1.5;
			this.rotationPointY = -0.5;
			break;
		case 2:
			squares.add(new Square(processing, settings, posX, posY, settings.getColor(9)));
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(9)));
			squares.add(new Square(processing, settings, posX + 1, posY + 1, settings.getColor(9)));
			squares.add(new Square(processing, settings, posX + 2, posY, settings.getColor(9)));
			this.rotationPointX = 1;
			this.rotationPointY = 0;
			break;
		case 3:
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(10)));
			squares.add(new Square(processing, settings, posX + 2, posY, settings.getColor(10)));
			squares.add(new Square(processing, settings, posX + 1, posY + 1, settings.getColor(10)));
			squares.add(new Square(processing, settings, posX + 2, posY + 1, settings.getColor(10)));
			this.rotationPointX = 1.5;
			this.rotationPointY = 0.5;
			break;
		case 4:
			squares.add(new Square(processing, settings, posX, posY, settings.getColor(11)));
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(11)));
			squares.add(new Square(processing, settings, posX + 2, posY, settings.getColor(11)));
			squares.add(new Square(processing, settings, posX, posY + 1, settings.getColor(11)));
			this.rotationPointX = 1;
			this.rotationPointY = 0;
			break;
		case 5:
			squares.add(new Square(processing, settings, posX, posY, settings.getColor(12)));
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(12)));
			squares.add(new Square(processing, settings, posX + 2, posY, settings.getColor(12)));
			squares.add(new Square(processing, settings, posX + 2, posY + 1, settings.getColor(12)));
			this.rotationPointX = 1;
			this.rotationPointY = 0;
			break;
		case 6:
			squares.add(new Square(processing, settings, posX, posY, settings.getColor(13)));
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(13)));
			squares.add(new Square(processing, settings, posX + 1, posY + 1, settings.getColor(13)));
			squares.add(new Square(processing, settings, posX + 2, posY + 1, settings.getColor(13)));
			this.rotationPointX = 1;
			this.rotationPointY = 0;
			break;
		case 7:
			squares.add(new Square(processing, settings, posX + 1, posY, settings.getColor(14)));
			squares.add(new Square(processing, settings, posX + 2, posY, settings.getColor(14)));
			squares.add(new Square(processing, settings, posX, posY + 1, settings.getColor(14)));
			squares.add(new Square(processing, settings, posX + 1, posY + 1, settings.getColor(14)));
			this.rotationPointX = 1;
			this.rotationPointY = 0;
			break;
		}
	}

	// test collision of a tetromino with the current squareList
	public boolean testCollision(ArrayList<Square> squareList) {
		boolean collision = false;
		int relPosX = this.posX - this.startPosX; // relative position between the tetromino and the possible future
													// start position in x
		int relPosY = this.posY - this.startPosY; // relative position between the tetromino and the possible future
													// start position in y
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < squareList.size(); j++) {
				if ((squares.get(i).getPosY() - relPosY) == squareList.get(j).getPosY()
						&& (squares.get(i).getPosX() - relPosX) == squareList.get(j).getPosX()) {
					collision = true;
					break;
				}
			}
		}
		return collision;
	}

	// moving down collision test (separatet, because used in the game running loop)
	public boolean moveDownAllowed(ArrayList<Square> squareList) {
		boolean moveDownAllowed = true;
		// checking collision with other squares
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < squareList.size(); j++) {
				if (squares.get(i).getPosY() + 1 == squareList.get(j).getPosY()
						&& squares.get(i).getPosX() == squareList.get(j).getPosX()) {
					moveDownAllowed = false;
					break;
				}
			}
		}
		// checking collision with the borders
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getPosY() + 1 >= settings.getFieldSizeY()) {
				moveDownAllowed = false;
				break;
			}
		}
		return moveDownAllowed;
	}

	// moving the tetromino down with collision test
	public void moveDown(ArrayList<Square> squareList) {
		boolean moveAllowed = moveDownAllowed(squareList);
		// if no collision - move down
		if (moveAllowed == true) {
			setPos(posX, posY + 1);
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).setPos(squares.get(i).getPosX(), squares.get(i).getPosY() + 1);
			}
		}
	}

	// moving the tetromino left with collision test
	public void moveLeft(ArrayList<Square> squareList) {
		boolean moveAllowed = true;
		// checking collision with other squares
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < squareList.size(); j++) {
				if (squares.get(i).getPosX() - 1 == squareList.get(j).getPosX()
						&& squares.get(i).getPosY() == squareList.get(j).getPosY()) {
					moveAllowed = false;
					break;
				}
			}
		}
		// checking collision with the borders
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getPosX() - 1 < 0) {
				moveAllowed = false;
				break;
			}
		}
		// if no collision - move left
		if (moveAllowed == true) {
			setPos(posX - 1, posY);
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).setPos(squares.get(i).getPosX() - 1, squares.get(i).getPosY());
			}
		}
	}

	// moving the tetromino right with collision test
	public void moveRight(ArrayList<Square> squareList) {
		boolean moveAllowed = true;
		// checking collision with other squares
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < squareList.size(); j++) {
				if (squares.get(i).getPosX() + 1 == squareList.get(j).getPosX()
						&& squares.get(i).getPosY() == squareList.get(j).getPosY()) {
					moveAllowed = false;
					break;
				}
			}
		}
		// checking collision with the borders
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getPosX() + 1 >= settings.getFieldSizeX()) {
				moveAllowed = false;
				break;
			}
		}
		// if no collision - move Right
		if (moveAllowed == true) {
			setPos(posX + 1, posY);
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).setPos(squares.get(i).getPosX() + 1, squares.get(i).getPosY());
			}
		}
	}

	// rotate the tetromino right with collision test
	public void rotateLeft(ArrayList<Square> squareList) {
		rotation("left", squareList);
	}

	// rotate the tetromino left with collision test
	public void rotateRight(ArrayList<Square> squareList) {
		rotation("right", squareList);
	}

	// returns vector of 2x2 matrix * 2x1 vector
	private double[] matrixMult(double[][] matrix, double[] vector) {
		double[] res = new double[2];
		res[0] = matrix[0][0] * vector[0] + matrix[0][1] * vector[1];
		res[1] = matrix[1][0] * vector[0] + matrix[1][1] * vector[1];
		return res;
	}

	// rotating the tetromino left or right
	private void rotation(String direction, ArrayList<Square> squareList) {
		double[][] matrix = new double[2][2];
		// rotation matrices (rotation by 90° clockwise or counterclockwise)
		if (direction.equals("right")) {
			matrix[0][0] = 0;
			matrix[0][1] = -1;
			matrix[1][0] = 1;
			matrix[1][1] = 0;
		} else if (direction.equals("left")) {
			matrix[0][0] = 0;
			matrix[0][1] = 1;
			matrix[1][0] = -1;
			matrix[1][1] = 0;
		} else {
			matrix[0][0] = 0;
			matrix[0][1] = -1;
			matrix[1][0] = 1;
			matrix[1][1] = 0;
		}
		int[][] pos = new int[squares.size()][2]; // saved positions for collision testing
		double[] rotationPoint = { posX + rotationPointX, posY + rotationPointY }; // absolute rotation point
		for (int i = 0; i < squares.size(); i++) {
			double[] posSquare = { squares.get(i).getPosX(), squares.get(i).getPosY() }; // absulute position of the
																							// square
			double[] relPosSquare = { posSquare[0] - rotationPoint[0], posSquare[1] - rotationPoint[1] }; // relative
																											// position
																											// of the
																											// square in
																											// relation
																											// to the
																											// rotation
																											// point
			double[] rotSquare = matrixMult(matrix, relPosSquare); // rotated squares (relative position)
			posSquare[0] = rotSquare[0] + rotationPoint[0];
			posSquare[1] = rotSquare[1] + rotationPoint[1];
			// not all positions of the rotation points are round numbers, rounding errors
			// can cause inaccuracies in the result --> round to a full number and convert
			// to int
			double x = Math.round(posSquare[0]);
			double y = Math.round(posSquare[1]);
			pos[i][0] = (int) x;
			pos[i][1] = (int) y;
		}
		// test collistion borders and other bricks -> if no collision --> write new
		// coordinates, else no rotation!
		boolean moveAllowed = true;
		// checking collision with other squares
		for (int i = 0; i < pos.length; i++) {
			for (int j = 0; j < squareList.size(); j++) {
				if (pos[i][0] == squareList.get(j).getPosX() && pos[i][1] == squareList.get(j).getPosY()) {
					moveAllowed = false;
					break;
				}
			}
		}
		// checking collision with the borders
		for (int i = 0; i < pos.length; i++) {
			if (pos[i][0] >= settings.getFieldSizeX() || pos[i][0] < 0 || pos[i][1] >= settings.getFieldSizeY()) {
				moveAllowed = false;
				break;
			}
		}
		// if no collision - move Right
		if (moveAllowed == true) {
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).setPos(pos[i][0], pos[i][1]);
			}
		}
	}

}
