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
		// System.out.println("Clicked row at: X: " + newRow.getX() + " Y:" +
		// newRow.getY());
		Row[][] rows = boardView.getRows();
		if (gameView.getScoreController().getBallLeft() == 0) {
			if (gameView.getGameController().getGame().getNextBall() != null) {
				gameView.getGameController().getGame().setNextBallFixed(null);
				gameView.getScoreController().updateScoreView();
			}
			System.out.println("OUT OF BALLS");
			return;
		}
		for (int y = 6; y >= 0; y--) {
			if (rows[y][newRow.getX()].getBall() == null) {
				rows[y][newRow.getX()].setBall(gameView.getGameController().getGame().getNextBall());
				boardView.addRow(rows[y][newRow.getX()]);
				gameView.getGameController().getGame().setNextBall();
				gameView.getScoreController().updateScoreView();
				break;
			}
		}
	}

}
