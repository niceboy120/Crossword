package browser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import interfaces.Writer;

import crossword.Crossword;


public class CwWriter implements Writer {

	public CwWriter(String _dir) {
		dir = _dir;
	}
	
	@Override
	public void write(Crossword cw) throws FileNotFoundException, IOException {
		String filePath = dir;
		
		if(!filePath.endsWith("/"))
			filePath += "/";
		filePath += new Long(cw.getUniqID()).toString()+".crossword";

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
		try {
			out.writeObject(cw);
		} finally {
			out.close();
		}
	}

	private String dir;
}
