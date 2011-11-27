package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import crossword.Crossword;

public interface Writer {
	public void write(Crossword cw) throws FileNotFoundException, IOException;
}
