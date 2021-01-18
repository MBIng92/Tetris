import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import processing.core.PApplet;

//including all scores
public class ScoreList {
	private PApplet processing;

	private ArrayList<Score> scoreList; // list including up to an amount of "maxScores" best scores
	private int maxScores = 20;
	private int fillColor;
	private int textColor;

	// constructor
	public ScoreList(PApplet processing, int fillColor, int textColor) {
		this.processing = processing;
		this.fillColor = fillColor;
		this.textColor = textColor;
		scoreList = new ArrayList<Score>();
		readScoresList();
		sortScoresList();
		writeScoresFile();
	}

	// display the score list in the List "scores" under settings
	public void displayScoresList(int windowWidth, int windowHeight) {
		int fieldSizeX = windowWidth * 9 / 10;
		int fieldSizeY = windowHeight * 2 / 3;
		int fieldPosX = windowWidth / 2;
		int fieldPosY = windowHeight * 3 / 5;
		int textSize = windowHeight / 32;
		int textPosX = fieldPosX - fieldSizeX / 2 + textSize;
		int textDistanceXRankName = textSize * 3;
		int textDistanceXNameScore = textSize * 28;
		int textDistanceXScoreLevel = textSize * 32;
		int textPosY = fieldPosY - fieldSizeY / 2 + textSize;
		int textDistanceY = textSize * 20 / 19;

		processing.noStroke();
		processing.rectMode(processing.CENTER);
		processing.fill(fillColor);
		processing.rect(fieldPosX, fieldPosY, fieldSizeX, fieldSizeY);
		for (int i = 0; i < scoreList.size(); i++) {
			processing.textSize(textSize);
			processing.textAlign(processing.LEFT, processing.CENTER);
			processing.fill(textColor);
			processing.text(scoreList.get(i).getRank() + ".", textPosX, textPosY + i * textDistanceY); // score rank
			processing.text(scoreList.get(i).getName(), textPosX + textDistanceXRankName, textPosY + i * textDistanceY); // score
																															// name
			processing.text("LVL: " + scoreList.get(i).getLevel(), textPosX + textDistanceXScoreLevel,
					textPosY + i * textDistanceY); // score level
			// converting scores from XXXXXXXXX to XXX.XXX.XXX
			String s = String.valueOf(scoreList.get(i).getScore());
			StringBuffer sScore = new StringBuffer(s);
			int counter = 0;
			for (int j = sScore.length(); j > 0; j--) {
				if (counter == 3) {
					sScore.insert(j, ".");
					counter = 0;
				}
				counter++;
			}
			processing.textAlign(processing.RIGHT, processing.CENTER);
			processing.text(sScore.toString(), textPosX + textDistanceXNameScore, textPosY + i * textDistanceY); // Score
																													// amount
		}
	}

	// add score in scoreList, sort the list and write in scores.txt
	public void addScore(Score score) {
		createScoresFile();
		scoreList.add(score);
		sortScoresList();
		writeScoresFile();
		cleanScoreList();
		readScoresFile();
	}

	private void cleanScoreList() {
		for (int i = scoreList.size() - 1; i >= 0; i--) {
			scoreList.remove(i);
		}
	}

	// gets the high score from the scoreList
	public Score getHighScore() {
		if (scoreList.size() > 0) {
			return scoreList.get(0);
		} else {
			Score highScore = new Score(0, "Unknown", 0, 0);
			return highScore;
		}
	}

	// read scoreList
	private void readScoresList() {
		createScoresFile();
		readScoresFile();
	}

	// reading scores.txt file and give data in the ArrayList "scoreList"
	private void readScoresFile() {
		try {
			File file = new File("data/scores.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] split = data.split("\t"); // splitted Linestring "data" (readed line)
				scoreList.add(new Score(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]),
						Integer.parseInt(split[3])));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// sort score list from biggest score to smallest score, set rank and cut to max
	// "maxScores" positions
	private void sortScoresList() {
		Collections.sort(scoreList, new SortReverse());
		// set rank
		for (int i = 0; i < scoreList.size(); i++) {
			scoreList.get(i).setRank(i + 1);
		}
		// cut to max "maxScores" positions
		for (int i = scoreList.size() - 1; i >= maxScores; i--) {
			scoreList.remove(i);
		}
	}

	// write scoreList in score.txt
	private void writeScoresFile() {
		try {
			FileWriter writer = new FileWriter("data/scores.txt");
			for (int i = 0; i < scoreList.size(); i++) {
				writer.write(scoreList.get(i).getRank() + "\t" + scoreList.get(i).getName() + "\t"
						+ scoreList.get(i).getScore() + "\t" + scoreList.get(i).getLevel() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// create scores.txt file
	private void createScoresFile() {
		try {
			File dataFile = new File("data");
			dataFile.mkdir();
			File file = new File("data/scores.txt");
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
