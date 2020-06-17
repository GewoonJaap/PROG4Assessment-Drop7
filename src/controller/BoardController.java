package controller;

import model.Row;
import view.BoardView;
import view.GameView;
import view.RowView;

public class BoardController {

	private BoardView boardView;
	private GameView gameView;

	public BoardController(GameView gameView) {
		this.gameView = gameView;
	}

	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public void addRow(Row row) {
		boardView.addRow(row);
	}

	public void clickedRow(Row newRow) {
		System.out.println("Clicked row at: X: " + newRow.getX() + " Y:" + newRow.getY());
		Row[][] rows = boardView.getRows();
		for (int x = 6; x >= 0; x--) {
			if (rows[newRow.getY()][x].getBall() == null) {
				rows[newRow.getY()][x].setBall(gameView.getGameController().getGame().getNextBall());
				boardView.addRow(rows[newRow.getY()][x]);
				gameView.getGameController().getGame().setNextBall();
				break;
			}
		}
	}

}
