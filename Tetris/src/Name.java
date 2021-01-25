import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Name {

	String name;

	// constructor
	public Name() {
		readName();
	}

	// get name
	public String getName() {
		return this.name;
	}

	// set name
	public void setName(String name) {
		this.name = new String(name);
	}

	// read name
	public void readName() {
		createNameFile();
		readNameFile();
	}

	// write name
	public void writeName(String name) {
		createNameFile();
		writeNameFile(name);
		this.name = new String(name);
	}

	// reading name.txt file
	private void readNameFile() {
		try {
			File file = new File("data/name.txt");
			Scanner scanner = new Scanner(file);
			if (scanner.hasNextLine() == false) {
				this.name = new String("Unknown");
				writeNameFile("Unknown");
			}
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				this.name = new String(data);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// create name.txt file
	private void createNameFile() {
		try {
			File dataFile = new File("data");
			dataFile.mkdir();
			File file = new File("data/name.txt");
			if (file.createNewFile()) {
				writeNameFile("Unknown");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// write name.txt file
	private void writeNameFile(String name) {
		try {
			FileWriter writer = new FileWriter("data/name.txt");
			writer.write(name);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
