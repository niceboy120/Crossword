package crossword.dictionary;

import crossword.dictionary.CwDB;

import crossword.dictionary.Entry;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteliCwDB extends CwDB implements Serializable {
	
	public InteliCwDB(String filename) {
		super(filename);
	}
	public LinkedList<Entry> findAll(String pattern) {
		LinkedList<Entry> tmp = new LinkedList<Entry>();
		Pattern p = Pattern.compile(pattern);
		Matcher m;
		ListIterator<Entry> i = dict.listIterator();
		while(i.hasNext()) {
			Entry c = i.next();
			m = p.matcher(c.getWord());
			if(m.find()) {
				tmp.add(c);
			}
		}
		if(tmp.size() > 0)
			return tmp;
		return null;
	}
	
	public Entry getRandom() {
		int pos = (int)(Math.random()*getSize());
		return dict.get(pos);
	}
	
	public Entry getRandom(int length) {
		
		LinkedList<Entry> tmp = findAll("^.{"+length+"}$");
		if(tmp != null) {
			int pos = (int)(Math.random()*tmp.size());
			return tmp.get(pos);
		}
		return null;
	}
	
	public Entry getRandom(String pattern) {
		LinkedList<Entry> tmp = findAll(pattern);
		if(tmp != null) {
			int pos = (int)(Math.random()*tmp.size());
			return tmp.get(pos);
		}
		return null;
	}
	
	public void add(String _word,String _clue) {
		if(dict.size() == 0) {
			dict.add(new Entry(_word, _clue));
		} else {
			ListIterator<Entry> it = dict.listIterator();
			int index = 0;
			Entry tmp = null;
			
			while(it.hasNext()) {
				index = it.nextIndex();
				tmp = it.next();
				if(tmp.getWord().compareToIgnoreCase(_word) > 0) {
					break;
				}
			}
			if(index == 0) {
				if(tmp.getWord().compareToIgnoreCase(_word) > 0) {
					dict.addFirst(new Entry(_word,_clue));
				} else {
					dict.add(1,new Entry(_word,_clue));
				}
			} else if(index+1 == dict.size()) {
				if(tmp.getWord().compareToIgnoreCase(_word) > 0) {
					dict.add(index,new Entry(_word,_clue));
				} else {
					dict.addLast(new Entry(_word,_clue));
				}
			} else {
				dict.add(index,new Entry(_word,_clue));
			}
		}
	}
}
