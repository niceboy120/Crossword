package crossword.board;

import java.io.Serializable;

public class BoardCell implements Serializable{
	
	public BoardCell(int _x, int _y) {
		content = null;
		options = new boolean[2][3];
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++)
				options[i][j] = true;
		x = _x;
		y = _y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setContent(String _content) {
		content = _content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void disableHorizStart() {
		options[0][0] = false;
	}
	
	public void disableHorizInner() {
		options[0][1] = false;
	}
	
	public void disableHorizEnd() {
		options[0][2] = false;
	}
	
	public void enableHorizStart() {
		options[0][0] = true;
	}
	
	public void enableHorizInner() {
		options[0][1] = true;
	}
	
	public void enableHorizEnd() {
		options[0][2] = true;
	}
	
	public void disableVertStart() {
		options[1][0] = false;
	}
	
	public void disableVertInner() {
		options[1][1] = false;
	}
	
	public void disableVertEnd() {
		options[1][2] = false;
	}

	public void enableVertStart() {
		options[1][0] = true;
	}
	
	public void enableVertInner() {
		options[1][1] = true;
	}
	
	public void enableVertEnd() {
		options[1][2] = true;
	}

	public boolean isHorizStart() { 
		return options[0][0];
	}
	
	public boolean isHorizInner() {
		return options[0][1];
	}
	
	public boolean isHorizEnd() {
		return options[0][2];
	}
	
	public boolean isVertStart() {
		return options[1][0];
	}
	
	public boolean isVertInner() {
		return options[1][1];
	}
	
	public boolean isVertEnd() {
		return options[1][2];
	}
	
	private int x;
	private int y;
	private String content;
	private boolean[][] options;
}
