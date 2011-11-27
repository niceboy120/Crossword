package crossword;

import crossword.board.Board;
import crossword.dictionary.CwEntry;
import crossword.dictionary.Entry;

public class SimpleStrategy extends Strategy {

	@Override
	public CwEntry findEntry(Crossword cw) throws TooLongEntryRequestedException {
		
		if(cw.getBoard().getVertStartCells().size() > 0) {
			Entry _e = cw.getCwDB().getRandom(cw.getBoard().getHeight());
			if(_e == null) {
				throw new TooLongEntryRequestedException(cw.getBoard().getHeight());
			}
			CwEntry e = new CwEntry(_e, 0, 0, CwEntry.Direction.VERT);
			cw.addCwEntry(e, this);
			for(int i = 0; i < cw.getBoard().getWidth(); i++)
				for(int j = 0; j < cw.getBoard().getHeight(); j++) {
					cw.getBoard().getCell(i, j).disableVertStart();
					cw.getBoard().getCell(i, j).disableHorizStart();
				}
			for(int i = 0; i < e.getWord().length(); i++)
				cw.getBoard().getCell(0, i).enableHorizStart();
		}
		
		while(cw.getBoard().getHorizStartCells().size() > 0) {
			int fromY = cw.getBoard().getHorizStartCells().getFirst().getY();
			int fromX = cw.getBoard().getHorizStartCells().getFirst().getX();
			String s = cw.getBoard().getCell(fromX, fromY).getContent();
			int maxLength = cw.getBoard().getWidth()-1;
			int counter = 0;
			Entry _e = null;
			do {
				_e = cw.getCwDB().getRandom("^"+s+"."+"{0,"+Integer.toString(maxLength)+"}$");
				if(_e == null)
					break;
				counter++;
			} while((cw.contains(_e.getWord()) && counter < 10));
			cw.getBoard().getCell(fromX,fromY).disableHorizStart();
			if(_e != null && !cw.contains(_e.getWord()))
				return new CwEntry(_e,fromX,fromY,CwEntry.Direction.HORIZ);
		}
		
		return null;
	}

	@Override
	public void updateBoard(Board b, CwEntry e) {
		switch(e.getDir()) {
			case VERT:
				for(int i = 0; i < e.getWord().length(); i++){
					b.getCell(e.getX(), e.getY() + i).setContent(e.getWord().substring(i,i+1));
				}
			break;
			case HORIZ:
				for(int i = 1; i < e.getWord().length(); i++){
					b.getCell(e.getX() + i, e.getY()).setContent(e.getWord().substring(i,i+1));
				}
			break;
		}
	}
	
	/*private Entry findNextEntry(Crossword cw,int fromX, int fromY) {
		
		Random gen = new Random(System.currentTimeMillis());
		int l; 
		do {
			l = gen.nextInt(cw.getBoard().getWidth()) + 1;
		} while(l < 2);
		
		String p = cw.getBoard().createPattern(fromX, fromY, l, fromY);
		
		Entry e = cw.getCwDB().getRandom(p);
		return e;
	}*/
}
