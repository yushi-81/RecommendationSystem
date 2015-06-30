package LCRS;

public class Score {
	  public String itemName;
	  public double score;

	  public Score() {
	    super();
	  }

	  public Score(String itemName, double score) {
	    super();
	    this.itemName = itemName;
	    this.score = score;
	  }

	  public String toString() {
	    return itemName + ": " + score;
	  }
}
