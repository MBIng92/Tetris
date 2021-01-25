import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

//class to display a background
public class Background {
	private PApplet processing;
	private Settings settings;

	private int color;
	int r;
	int g;
	int b;
	private Slider sliderRed;
	private Slider sliderGreen;
	private Slider sliderBlue;

	public Background(PApplet processing, Settings settings) {
		this.processing = processing;
		this.settings = settings;
		refresh();
	}

	public void display() {
		processing.background(color);
	}

	public void displaySliders() {
		sliderRed.display();
		sliderGreen.display();
		sliderBlue.display();
	}

	public void action() {
		if (mouseOverSliderRed()) {
			sliderRed.moveSlider();
			createRGBFile();
			writeRGBFile(sliderRed.getValue(), this.g, this.b);
			refresh();
		}

		else if (mouseOverSliderGreen()) {
			sliderGreen.moveSlider();
			createRGBFile();
			writeRGBFile(this.r, sliderGreen.getValue(), this.b);
			refresh();
		}

		else if (mouseOverSliderBlue()) {
			sliderBlue.moveSlider();
			createRGBFile();
			writeRGBFile(this.r, this.g, sliderBlue.getValue());
			refresh();
		}
	}

	public boolean mouseOverSliderRed() {
		return sliderRed.mouseOverButton();
	}

	public boolean mouseOverSliderGreen() {
		return sliderGreen.mouseOverButton();
	}

	public boolean mouseOverSliderBlue() {
		return sliderBlue.mouseOverButton();
	}

	public void refresh() {
		createRGBFile();
		readRGBFile();
		sliderRed = new Slider(processing, settings, r, settings.getWidth() * 2 / 6, settings.getHeight() * 2 / 3, 30,
				255, processing.color(255, 0, 0), 0, 255, 50, 30, 20, 3);
		sliderGreen = new Slider(processing, settings, g, settings.getWidth() * 3 / 6, settings.getHeight() * 2 / 3, 30,
				255, processing.color(0, 255, 0), 0, 255, 50, 30, 20, 3);
		sliderBlue = new Slider(processing, settings, b, settings.getWidth() * 4 / 6, settings.getHeight() * 2 / 3, 30,
				255, processing.color(0, 0, 255), 0, 255, 50, 30, 20, 3);
	}

	// reading rgb.txt file
	private void readRGBFile() {
		try {
			File file = new File("data/rgb.txt");
			Scanner scanner = new Scanner(file);
			int r = 0;
			int g = 0;
			int b = 0;
			int n = 0;
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (n == 0) {
					r = Integer.parseInt(data);
				} else if (n == 1) {
					g = Integer.parseInt(data);
				} else {
					b = Integer.parseInt(data);
				}
				n++;
			}
			if (r < 0 || r > 255) {
				r = 255;
			}
			if (g < 0 || g > 255) {
				g = 255;
			}
			if (b < 0 || b > 255) {
				b = 255;
			}
			this.color = processing.color(r, g, b);
			this.r = r;
			this.g = g;
			this.b = b;
			writeRGBFile(r, g, b);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// create rgb.txt file
	private void createRGBFile() {
		try {
			File dataFile = new File("data");
			dataFile.mkdir();
			File file = new File("data/rgb.txt");
			if (file.createNewFile()) {
				writeRGBFile(255, 255, 255);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// write rgb.txt file
	private void writeRGBFile(int r, int g, int b) {
		try {
			FileWriter writer = new FileWriter("data/rgb.txt");
			if (r < 0 || r > 255) {
				r = 255;
			}
			if (g < 0 || g > 255) {
				g = 255;
			}
			if (b < 0 || b > 255) {
				b = 255;
			}
			this.color = processing.color(r, g, b);
			this.r = r;
			this.g = g;
			this.b = b;
			writer.write(r + "\n" + g + "\n" + b);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
