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
	
	public void addRow(Row row) {
		boardView.addRow(row);
	}


	public void clickedRow(Row newRow) {
		System.out.println("Clicked row at: X: " + newRow.getX() + " Y:" + newRow.getY());
	}

}
