import processing.core.PApplet;

public class Button {
	private PApplet processing;
	private Settings settings;

	private String text;
	private int buttonSizeX = 300;
	private int buttonSizeY = 120;

	private int positionX;
	private int positionY;

	private int textSize;
	private int correctionFactor;

	private boolean activated;
	private boolean nonActive; // if this is true, then this button do nothing (it is just a text field)

	// Constructor for using the button
	public Button(PApplet processing, Settings settings, String text, int posX, int posY, int buttonSizeX,
			int buttonSizeY, int textSize, int correctionFactor) {
		this.processing = processing;
		this.settings = settings;
		this.text = text;
		positionX = posX;
		positionY = posY;
		this.buttonSizeY = buttonSizeY;
		this.buttonSizeX = buttonSizeX;
		this.textSize = textSize;
		this.correctionFactor = correctionFactor;
		this.activated = false;
		this.nonActive = false;
	}

	// sets the "activated" state to false or true
	public void setState(boolean state) {
		this.activated = state;
	}

	// sets the "nonActive" state to false or true
	public void setNonActive(boolean state) {
		this.nonActive = state;
	}

	public boolean mouseOverButton() {
		if (nonActive == false && processing.mouseX >= positionX - buttonSizeX / 2
				&& processing.mouseX <= positionX + buttonSizeX / 2 && processing.mouseY >= positionY - buttonSizeY / 2
				&& processing.mouseY <= positionY + buttonSizeY / 2) {
			return true;
		} else {
			return false;
		}
	}

	public void display() {
		if (mouseOverButton() == true) {
			displayBright();
		} else {
			if (activated == false) {
				displayDark();
			} else if (activated == true) {
				displayActivated();
			}
		}
	}

	public void displayDark() {
		processing.rectMode(processing.CENTER);
		processing.noStroke();
		processing.textSize(textSize);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(1));
		processing.rect(positionX, positionY, buttonSizeX, buttonSizeY);
		processing.fill(settings.getColor(4));
		processing.text(text, positionX, positionY - correctionFactor);
	}

	public void displayBright() {
		processing.rectMode(processing.CENTER);
		processing.noStroke();
		processing.textSize(textSize);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(3));
		processing.rect(positionX, positionY, buttonSizeX, buttonSizeY);
		processing.fill(settings.getColor(2));
		processing.text(text, positionX, positionY - correctionFactor);
	}

	public void displayActivated() {
		processing.rectMode(processing.CENTER);
		processing.noStroke();
		processing.textSize(textSize);
		processing.textAlign(processing.CENTER, processing.CENTER);
		processing.fill(settings.getColor(7));
		processing.rect(positionX, positionY, buttonSizeX, buttonSizeY);
		processing.fill(settings.getColor(4));
		processing.text(text, positionX, positionY - correctionFactor);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getButtonSizeX() {
		return buttonSizeX;
	}

	public void setButtonSizeX(int sizeX) {
		this.buttonSizeX = sizeX;
	}

	public int getButtonSizeY() {
		return buttonSizeY;
	}

	public void setButtonSizeY(int sizeY) {
		this.buttonSizeY = sizeY;
	}

	public int getButtonPositionX() {
		return positionX;
	}

	public void setButtonPositionX(int posX) {
		this.positionX = posX;
	}

	public int getButtonPositionY() {
		return positionY;
	}

	public void setButtonPositionY(int posY) {
		this.positionY = posY;
	}
}
