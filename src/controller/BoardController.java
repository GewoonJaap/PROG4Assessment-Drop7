package controller;

import model.Row;
import view.BoardView;
import view.RowView;

public class BoardController {
	
	private BoardView boardView;

	public BoardController() {
	}
	
	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}
	
	public void addRow(Row row, int x, int y) {
		boardView.addRow(row, x, y);
	}

}
