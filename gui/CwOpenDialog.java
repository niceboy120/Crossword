package gui;
import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


class CwOpenDialog extends JFileChooser {
	
	CwOpenDialog(Component _parent, String _startPath) {
		super(_startPath);
		parent = _parent;
		applyFileFilter();
	}
	
	private void applyFileFilter() {
		FileFilter filter = new FileNameExtensionFilter("Pliki krzyżówek (*.crossword)", "crossword");
		addChoosableFileFilter(filter);		
	}
	
	public int open() {
		return showOpenDialog(parent);
	}
	
	public void setParent(Component _parent) {
		parent = _parent;
	}
	
	private Component parent;
	private static final long serialVersionUID = -3844122744658949422L;
}