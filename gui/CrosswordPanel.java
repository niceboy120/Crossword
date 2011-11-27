package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import crossword.Crossword;
import crossword.dictionary.CwEntry;
import crossword.dictionary.Entry;

public class CrosswordPanel extends JPanel {
	public CrosswordPanel() {
		super();
		setLayout(new GridLayout(1,2));
		clues = new JLabel();
		words = new WordsPanel();
		add(words);
		add(clues);
	}
	
	public void solve() {
		words.solve();
		solveClues();
	}
	
	public void setCrossword(Crossword _cw) {
		cw = _cw;
		words.setBoard(cw.getBoard());
		showClues();
	}
	
	private void showClues() {
		String htmlClues = "";
		Iterator<CwEntry> it = cw.getROEntryIter();
		it.next(); // pominięcie hasła będącego rozwiązaniem
		int num = 1;
		while(it.hasNext()) {
			htmlClues += num+") "+it.next().getClue()+"<br><br>";
			num++;
		}
		String solveString = "<br><br>Rozwiązanie: .................................................................................................";
		htmlClues = "<html>"+htmlClues+solveString+"</html>";
		clues.setText(htmlClues);
	}
	
	private void solveClues() {
		String htmlClues = "";
		Iterator<CwEntry> it = cw.getROEntryIter();
		Entry solution = it.next();
		int num = 1;
		while(it.hasNext()) {
			htmlClues += num+") "+it.next().getClue()+"<br><br>";
			num++;
		}
		String solveString = "<br><br>Rozwiązanie: "+solution.getWord().toUpperCase()+"<br>"+solution.getClue();
		htmlClues = "<html>"+htmlClues+solveString+"</html>";
		clues.setText(htmlClues);
	}
	
	Crossword cw = null;
	private WordsPanel words;
	private JLabel clues;
}
