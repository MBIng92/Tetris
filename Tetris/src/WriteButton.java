import processing.core.PApplet;

public class WriteButton {
	private PApplet processing;
	private Settings settings;

	private String text;
	private int buttonSizeX = 300;
	private int buttonSizeY = 120;

	private boolean typeNameState = false;

	private int positionX;
	private int positionY;

	private int textSize;
	private int correctionFactor;

	// Constructor for using the button
	public WriteButton(PApplet processing, Settings settings, String text, int posX, int posY, int buttonSizeX,
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
	}

	public String getText() {
		return text;
	}

	public void nameReset() {
		StringBuilder builder = new StringBuilder(text);
		while (builder.length() > 0) {
			builder.deleteCharAt(0);
			text = builder.toString();
		}
	}

	public void deleteLastChar() {
		StringBuilder builder = new StringBuilder(text);
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
			text = builder.toString();
		}
	}

	public void typeName(char c) {
		if (c != processing.CODED) {
			text = text + c;
		}
	}

	public boolean mouseOverButton() {
		if (processing.mouseX >= positionX - buttonSizeX / 2 && processing.mouseX <= positionX + buttonSizeX / 2
				&& processing.mouseY >= positionY - buttonSizeY / 2
				&& processing.mouseY <= positionY + buttonSizeY / 2) {
			return true;
		} else {
			return false;
		}
	}

	public void setTypeNameState(boolean state) {
		this.typeNameState = state;
		if (state == false && text.length() == 0) {
			text = "Unknown";
		}
	}

	public boolean getTypeNameState() {
		return typeNameState;
	}

	public void display() {
		if (typeNameState == true) {
			displayBright();
		} else {
			displayDark();
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

}
