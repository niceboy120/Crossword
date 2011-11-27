package crossword;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import crossword.board.Board;
import crossword.dictionary.CwEntry;
import crossword.dictionary.InteliCwDB;

public class Crossword implements Serializable{

	public Crossword(int _x, int _y) {
		entries = new LinkedList<CwEntry>();
		b = new Board(_x, _y);
		ID = System.currentTimeMillis();
	}
	
	public Iterator<CwEntry> getROEntryIter() {
		return entries.iterator();
	}
	
	public Board getBoard() {
		return b;
	}
	
	public InteliCwDB getCwDB() {
		return cwdb;
	}
	
	public void setCwDB(InteliCwDB _cwdb) {
		 cwdb = _cwdb;
	}
	public boolean contains(String word) {
		ListIterator<CwEntry> it = entries.listIterator();
		while(it.hasNext()) {
			if(it.next().getWord().equals(word))
				return true;
		}
		return false;
	}
	
	public final void addCwEntry(CwEntry cwe, Strategy s) {
		entries.add(cwe);
		s.updateBoard(b,cwe);
	}
	
	public final void generate(Strategy s) throws TooLongEntryRequestedException {
		CwEntry e = null;
		while((e = s.findEntry(this)) != null) {
			addCwEntry(e,s);
		}
	}
	
	public void printAllEntries() {
		for(int i = 0; i < b.getHeight(); i++) {
			System.out.print(i+1+")  ");
			for(int j = 0; j < b.getWidth(); j++)
				if(b.getCell(j, i).getContent() != null)
					System.out.print(" "+b.getCell(j, i).getContent()+" ");
			System.out.println();
		}
	}
	
	public void printAllClues() {
		ListIterator<CwEntry> it = entries.listIterator();
		it.next();
		while(it.hasNext()) {
			CwEntry e = it.next();
			System.out.println(e.getY()+1+") "+e.getClue());
		}
	}
	
	public long getUniqID() {
		return ID;
	}
	
	private LinkedList<CwEntry> entries;
	private Board b;
	private InteliCwDB cwdb;
	private final long ID;
}
