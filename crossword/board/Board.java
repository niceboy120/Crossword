package crossword.board;

import java.io.Serializable;
import java.util.LinkedList;

public class Board implements Serializable{
	
	public Board(int _x, int _y) throws Error {
		if(_x <= 0 || _y <= 0)
			throw new Error("Podano nieprawidłowy wymiar planszy: "+_x+", "+_y);
		board = new BoardCell[_x][_y];
		for(int i = 0; i < _x; i++)
			for(int j = 0;j <  _y; j++)
				board[i][j] = new BoardCell(i,j);
	}
	
	public int getWidth() {
		return board.length;
	}
	
	public int getHeight() {
		return board[0].length;
	}
	
	public BoardCell getCell(int x,int y) throws Error {
		if(x>=getWidth() || y>=getHeight()) 
			throw new Error("Komórka o współrzędnych: "+x+", "+y+" nie istnieje");
		return board[x][y];
	}
	
	public void setCell(int x, int y, BoardCell c) throws Error {
		if(x>getWidth() || y>getHeight()) 
			throw new Error("Komórka o współrzędnych: "+x+", "+y+" nie istnieje");
		board[x][y] = c;
	}
	
	public LinkedList<BoardCell> getStartCells() {
		LinkedList<BoardCell> startCells = new LinkedList<BoardCell>();
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++)
				if(board[i][j].isHorizStart() || board[i][j].isVertStart())
					startCells.add(board[i][j]);
		return startCells;
	}
	
	public LinkedList<BoardCell> getHorizStartCells() {
		LinkedList<BoardCell> startCells = new LinkedList<BoardCell>();
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++)
				if(board[i][j].isHorizStart())
					startCells.add(board[i][j]);
		return startCells;
	}
	
	public LinkedList<BoardCell> getVertStartCells() {
		LinkedList<BoardCell> startCells = new LinkedList<BoardCell>();
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++)
				if(board[i][j].isVertStart())
					startCells.add(board[i][j]);
		return startCells;
	}
	
	public String createPattern(int fromX, int fromY, int toX, int toY) {
		if(fromX == toX) {
			String pattern = new String();
			//int l = toY - fromY;
			pattern = "^";
			for(int i = fromY; i < toY; i++) {
				if(board[fromX][i].getContent() != null)
					pattern += board[fromX][i].getContent();
				else
					pattern += ".";
			}
			pattern += "$";
			return pattern;	
		} else if(fromY == toY) {
			String pattern = new String();
			//int l = toX - fromX;
			pattern = "^";
			for(int i = fromX; i < toX; i++) {
				if(board[i][fromY].getContent() != null)
					pattern += board[i][fromY].getContent();
				else
					pattern += ".";
			}
			pattern += "$";
			return pattern;
		}
		
		return null;
	}
	
	private BoardCell[][] board;
}
