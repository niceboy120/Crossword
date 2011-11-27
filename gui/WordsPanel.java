package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import crossword.board.Board;

public class WordsPanel extends JPanel {

	private static final long serialVersionUID = -7673622687824741671L;
	public WordsPanel() {
		super();
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int num = 1;
		if(b != null) {
			for(int i=0;i<b.getHeight();i++) {
				if(b.getCell(1,i).getContent() != null) {
					g.drawString(Integer.toString(num++), offsetX-20, offsetY+i*h+(h/2)+3);
				}
				for(int j=0;j<b.getWidth();j++) {
					if ( b.getCell(j,i).getContent() != null ) {
						g.setColor(Color.BLACK);
						g.drawRect(offsetX+j*w, offsetY+i*h, w, h);
						if(solved)
							g.drawString(b.getCell(j,i).getContent(), offsetX+j*w+(w/2)-3, offsetY+i*h+(h/2)+3);
					}
				}
			}
		}
	}
	
	public void setBoard(Board _b) {
		solved=false;
		b = _b;
		repaint();
	}
	
	public void solve() {
		solved = true;
		repaint();
	}
	
	private boolean solved = false;
	private Board b = null;
	private int w = 35;
	private int h = 35;
	private int offsetX = 50;
	private int offsetY = 20;
}
