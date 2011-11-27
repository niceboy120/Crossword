package browser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import crossword.Crossword;
import crossword.SimpleStrategy;
import crossword.TooLongEntryRequestedException;
import crossword.dictionary.InteliCwDB;

public class CwBrowser {

	public CwBrowser(String _dir) {
		cws = new LinkedList<Crossword>();
		dir = _dir;
	}
	
	public CwBrowser() {
		cws = new LinkedList<Crossword>();
	}
	
	public Crossword load(long _id) throws FileNotFoundException, IOException, ClassNotFoundException {
		Crossword cw =  new CwReader(dir).getCw(_id);
		cws.add(cw);
		return cw;
	}
	
	public static Crossword load(String _filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		Crossword cw = CwReader.getCw(_filePath);
		return cw;
	}
	
	public void loadAll() throws FileNotFoundException, IOException, ClassNotFoundException {
		cws = new CwReader(dir).getAllCws();
	}
	
	public void save(Crossword cw) throws FileNotFoundException, IOException {
		CwWriter w = new CwWriter(dir);
		w.write(cw);
	}
	
	public void listAllCrosswords() {
		ListIterator<Crossword> it = cws.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next().getUniqID());
		}
	}
	
	public Crossword generateNewSimpleCrossword(String dbFile,int _width,int _height) throws TooLongEntryRequestedException {
		Crossword cw = new Crossword(_width,_height);
		cw.setCwDB(new InteliCwDB(dbFile));
		cw.generate(new SimpleStrategy());
		cws.add(cw);
		return cw;
	}
	
	public Crossword getRandomCrossword() {
		int n = (int) (Math.random()*cws.size());
		return cws.get(n);
	}
	
	public String getDir() {
		return dir;
	}
	
	public void setDir(String _dir) {
		dir = _dir;
	}
	
	private LinkedList<Crossword> cws;
	private String dir;
}
