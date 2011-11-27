package crossword.dictionary;

import java.io.Serializable;

public class Entry implements Serializable{
	
	public Entry(String _word,String _clue) {
		word = _word;
		clue = _clue;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getClue() {
		return clue;
	}
	
	private String word;
	private String clue;
}
