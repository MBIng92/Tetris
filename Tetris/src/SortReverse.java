import java.util.*; //for sort algorithm

//Sort algorithm (from biggest to smallest number)
public class SortReverse implements Comparator<Score> {
	public int compare(Score a, Score b) {
		return b.getScore() - a.getScore();
	}
}
