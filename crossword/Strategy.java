package crossword;

import crossword.board.Board;
import crossword.dictionary.CwEntry;

public abstract class Strategy {
	public abstract CwEntry findEntry(Crossword cw) throws TooLongEntryRequestedException;
	public abstract void updateBoard(Board b, CwEntry e);
}
