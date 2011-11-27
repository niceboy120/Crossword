package crossword.dictionary;

import java.io.Serializable;

public class CwEntry extends Entry implements Serializable{

	public CwEntry(Entry e,int _x, int _y, Direction _d) {
		super(e.getWord(),e.getClue());
		x = _x;
		y = _y;
		d = _d;
	}
	
	public CwEntry(String _word, String _clue, int _x, int _y, Direction _d) {
		super(_word, _clue);
		x = _x;
		y = _y;
		d = _d;
	}
	
	public enum Direction {
		HORIZ, VERT;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDir() {
		return d;
	}
	
	private int x;
	private int y;
	private Direction d;
}
