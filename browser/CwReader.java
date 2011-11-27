package browser;

import interfaces.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.ListIterator;

import crossword.Crossword;

public class CwReader implements Reader {
	
	public CwReader(String _dir) {
		dir = _dir;
		cws = readCwFilesFromDir();
	}
	
	private LinkedList<Long> readCwFilesFromDir() {
		LinkedList<Long> CwFiles = new LinkedList<Long>();
		File _dir = new File(dir);
		String[] files = _dir.list();
		for(String file : files) {
			if(file.matches("[0-9]*.crossword")) {
				String tmp = file.split(".crossword")[0];
				CwFiles.add( (long) (Double.parseDouble(tmp)) );
			}
		}
		return CwFiles;
	}
	
	public Crossword getCw(long _ID) throws FileNotFoundException, IOException, ClassNotFoundException {
		String filePath = dir;
		Crossword cw;
		
		if(!filePath.endsWith("/"))
			filePath += "/";
		filePath += new Long(_ID).toString()+".crossword";
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
		try {
			cw = (Crossword) in.readObject();
		} finally {
			in.close();
		}
		return cw;
	}
	
	public static Crossword getCw(String _filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		Crossword cw = null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(_filePath));
		try {
			cw = (Crossword) in.readObject();
		} finally {
			in.close();
		}
		return cw;
	}
	
	@Override
	public LinkedList<Crossword> getAllCws() throws FileNotFoundException, IOException, ClassNotFoundException {
		LinkedList<Crossword> cw = new LinkedList<Crossword>();
		ListIterator<Long> it = cws.listIterator();
		while(it.hasNext()) {
			cw.add(getCw(it.next()));
		}
		return cw;
		
	}

	private String dir;
	private LinkedList<Long> cws;
	
}
