package crossword.dictionary;

import java.util.LinkedList;
import java.util.ListIterator;
import java.io.*;

import crossword.dictionary.Entry;


public class CwDB implements Serializable{
	
	public CwDB(String filename) {
		boolean exists = (new File(filename)).exists();
		if(exists) {
			dict = new LinkedList<Entry>();
			createDB(filename);
		} else {
			throw new Error("Plik z baza nie istnieje.");
		}
	}
	
	public void add(String _word,String _clue) {
		dict.add(new Entry(_word,_clue));
	}
	
	public Entry get(String word) {
		ListIterator<Entry> i = dict.listIterator();
		while(i.hasNext()) {
			Entry c = i.next();
			if(c.getWord().equals(word)) {
				return c;
			}
		}
		return null;
	}
	
	public void remove(String word) {
		ListIterator<Entry> i = dict.listIterator();
		while(i.hasNext()) {
			Entry c = i.next();
			if(c.getWord().equals(word)) {
				i.remove();
			}
		}
	}
	
	public void saveDB(String filename) {}
	
	public int getSize() {
		return dict.size();
	}
	
	protected void createDB(String filename) {
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String c;
			String w;
			while((w = br.readLine()) != null) {
				if ( (c = br.readLine()) != null ) {
					add(w,c);
				}
			}
			fr.close();
		}
		catch(Exception e) {System.out.println("createDB error: "+e.getMessage()); }
	}
	
	protected LinkedList<Entry> dict;
}
