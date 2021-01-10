import processing.core.PApplet;

public class Slider {
	private PApplet processing;
	private Settings settings;

	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private int value;
	private int color;
	private int minValue;
	private int maxValue;
	private Button button;
	private int buttonSizeX;
	private int buttonSizeY;
	private int textSize;
	private int correctionFactor;
	private float positionButtonY;

	public Slider(PApplet processing, Settings settings, int value, int posX, int posY, int sizeX, int sizeY, int color,
			int minValue, int maxValue, int buttonSizeX, int buttonSizeY, int textSize, int correctionFactor) {
		this.processing = processing;
		this.settings = settings;
		this.value = value;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.color = color;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.buttonSizeX = buttonSizeX;
		this.buttonSizeY = buttonSizeY;
		this.textSize = textSize;
		this.correctionFactor = correctionFactor;
		positionButtonY = ((float) posY + (float) sizeY / 2)
				- (((float) value - (float) minValue) / ((float) maxValue - (float) minValue)) * (float) sizeY;
		button = new Button(processing, settings, String.valueOf(value), posX, (int) positionButtonY, buttonSizeX,
				buttonSizeY, textSize, correctionFactor);
	}

	public boolean mouseOverButton() {
		return button.mouseOverButton();
	}

	public void moveSlider() {
		positionButtonY = processing.mouseY;
		float value = (-positionButtonY + posY + sizeY / 2) / sizeY * (maxValue - minValue) + minValue;
		if (value > 255) {
			this.value = 255;
		} else if (value < 0) {
			this.value = 0;
		} else {
			this.value = (int) value;
		}
		button.setButtonPositionY((int) positionButtonY);
	}

	public int getValue() {
		return value;
	}

	public void display() {
		// display the Slider
		processing.rectMode(processing.CENTER);
		processing.stroke(settings.getColor(3));
		processing.fill(color);
		processing.rect(posX, posY, sizeX, sizeY);
		// display the slider button
		button.display();
	}
}
