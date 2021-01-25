import processing.core.PApplet;

public class Square {
	private PApplet processing;
	private Settings settings;

	private int posX;
	private int posY;
	private int posCoordX;
	private int posCoordY;

	private boolean colorMode = false; // if false: displays color, if true: displays fixingColor
	private int color; // contains color information
	private int fixingColor; // fixing color
	private int transparency;

	public Square(PApplet processing, Settings settings, int posX, int posY, int color) {
		this.processing = processing;
		this.settings = settings;
		this.posX = posX;
		this.posY = posY;
		this.posCoordX = settings.getCoordinateX(posX);
		this.posCoordY = settings.getCoordinateY(posY);
		this.color = color;
		this.fixingColor = settings.getColor(2);
		this.transparency = 255;
	}

	public int getColor() {
		return color;
	}

	public int getFixingColor() {
		return fixingColor;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setFixingColor(int color) {
		this.fixingColor = color;
	}

	public void setColorMode(boolean state) {
		this.colorMode = state;
	}

	public boolean getColorMode() {
		return colorMode;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setTransparency(int i) {
		if (i >= 0 && i <= 255) {
			this.transparency = i;
		}
	}

	public void setPos(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.posCoordX = settings.getCoordinateX(posX);
		this.posCoordY = settings.getCoordinateY(posY);
	}

	public void display() {
		processing.rectMode(processing.CENTER);
		// processing.stroke(color, transparency);
		processing.stroke(settings.getColor(3));
		processing.strokeWeight(1);
		if (colorMode == false) {
			processing.fill(color, transparency);
		} else {
			processing.fill(fixingColor, transparency);
		}
		// processing.square(posCoordX, posCoordY, settings.getSquareDistance()-4);
		processing.square(posCoordX, posCoordY, settings.getSquareDistance() - 2);
	}
}
