//class for scores
public class Score {
	private int rank = 0;
	private String name;
	private int score = 0;
	private int level = 0;

	// default constructor
	public Score(String name) {
		this.name = name;
	}

	// constructor with all parameters
	public Score(int rank, String name, int score, int level) {
		setScore(rank, name, score, level);
	}

	public void setScore(int rank, String name, int score, int level) {
		setName(name);
		setScore(score);
		setRank(rank);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public int getRank() {
		return rank;
	}

	public int getLevel() {
		return level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}