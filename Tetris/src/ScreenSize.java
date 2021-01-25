import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

public class ScreenSize {
	private PApplet processing;

	protected int sizeDefault = 3; // default size number
	private int sizeNumber; // size numbers:
	protected int[][] size = { { 800, 580 }, { 1000, 720 }, { 1200, 860 }, { 1400, 1000 } };

	private int sizeX; // window size in x (width)
	private int sizeY; // window size in y (height)

	public ScreenSize(PApplet processing) {
		this.processing = processing;
		readSize();
	}

	// returns screen sizes from 1...4 as String
	public String getScreenSize(int i) {
		if (i >= 1 && i <= 4) {
			return (size[i - 1][0] + " x " + size[i - 1][1]).toString();
		} else {
			return "";
		}
	}

	// returns current screen Size as String (same format like "getScreenSize"
	// function to compare
	public String getCurrentScreenSize() {
		return (sizeX + " x " + sizeY).toString();
	}

	// sets the new size of the window and writes it in the size.txt file
	public void setSize(int sizeNumber) {
		createSizeFile();
		writeSizeFile(sizeNumber);
		readSizeFile();
		processing.getSurface().setResizable(true);
		processing.getSurface().setSize(sizeX, sizeY);
		processing.getSurface().setResizable(false);
	}

	// get screen height
	public int getHeight() {
		return sizeY;
	}

	// get screen width
	public int getWidth() {
		return sizeX;
	}

	// read size
	private void readSize() {
		createSizeFile();
		readSizeFile();
	}

	// reading size.txt file and in the sizeX and sizeY settings of this class
	private void readSizeFile() {
		try {
			File file = new File("data/size.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				try {
					sizeNumber = Integer.parseInt(data);
				} catch (Exception e) {
					writeSizeFile(sizeDefault);
					sizeNumber = sizeDefault;
				}
			}
			switch (sizeNumber) {
			case 1:
				this.sizeX = size[sizeNumber - 1][0];
				this.sizeY = size[sizeNumber - 1][1];
				break;
			case 2:
				this.sizeX = size[sizeNumber - 1][0];
				this.sizeY = size[sizeNumber - 1][1];
				break;
			case 3:
				this.sizeX = size[sizeNumber - 1][0];
				this.sizeY = size[sizeNumber - 1][1];
				break;
			case 4:
				this.sizeX = size[sizeNumber - 1][0];
				this.sizeY = size[sizeNumber - 1][1];
				break;
			default:
				this.sizeX = size[sizeDefault - 1][0];
				this.sizeY = size[sizeDefault - 1][1];
				writeSizeFile(sizeDefault);
				break;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// write sizenNumber of the window in size.txt
	private void writeSizeFile(int sizeNumber) {
		try {
			FileWriter writer = new FileWriter("data/size.txt");
			writer.write(String.valueOf(sizeNumber));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// create size.txt file and give default stats
	private void createSizeFile() {
		try {
			File dataFile = new File("data");
			dataFile.mkdir();
			File file = new File("data/size.txt");
			if (file.createNewFile()) {
				writeSizeFile(sizeDefault);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
