package controller;

import java.util.ArrayList;

import model.Ball;
import model.Row;
import view.BoardView;
import view.GameView;

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
				checkForDestroy();
				break;
			}
		}
	}

	private void checkForDestroy() {
		Row[][] rows = boardView.getRows();
		ArrayList<Row> ballDestroy = new ArrayList<Row>();
		for (int y = 6; y >= 0; y--) {
			for (int x = 6; x >= 0; x--) {
				if (rows[y][x].getBall() != null && !rows[y][x].getBall().getImage().contains("full")
						&& !rows[y][x].getBall().getImage().contains("half")) {
					int ballValue = rows[y][x].getBall().getValue();
					int ballsy = 0;
					int ballsx = 0;
					for (int by = y; by < 6; by++) {
						if (rows[by][x].getBall() != null) {
							ballsy++;
							// System.out.println("Found a ball: Y:" + by + " X:" + x);
						} else {
							// System.out.println("BREAK");
							break;
						}
					}
					for (int by = y; by > 0; by--) {
						if (rows[by][x].getBall() != null) {
							ballsy++;
							System.out.println("Found a ball: Y:" + by + " X:" + x);
						} else {
							System.out.println("BREAK " + x);
							break;
						}
					}
					// System.out.println("#" + y + " " + x + "Ball y: " + ballsy);

					for (int bx = x; bx < 6; bx++) {
						if (rows[y][bx].getBall() != null) {
							// System.out.println("Found a ball: Y:" + y + " X:" + bx);
							ballsx++;
						} else {
							// System.out.println("BREAK");
							break;
						}
					}
					for (int bx = x; bx > 0; bx--) {
						if (rows[y][bx].getBall() != null) {
							// System.out.println("Found a ball: Y:" + y + " X:" + bx);
							ballsx++;
						} else {
							// System.out.println("BREAK");
							break;
						}
					}
					if (ballsx == rows[y][x].getBall().getValue() || ballsy == rows[y][x].getBall().getValue()) {
						// System.out.println("We can destroy a ball! on Y: " + y + " X: " + x + " With
						// value: "
						// + rows[y][x].getBall().getValue());
						ballDestroy.add(rows[y][x]);
					}

				}
			}
		}
		// System.out.println("Destroying all the balls!");
		if (ballDestroy.size() == 0) {
			return;
		}
		for (int i = 0; i < ballDestroy.size(); i++) {
			if (ballDestroy.get(i).getBall().canBeDestroyed()) {
				try {
					rows[ballDestroy.get(i).getY() - 1][ballDestroy.get(i).getX()].getBall().breakBall();
				} catch (Exception e) {
				}
				try {
					rows[ballDestroy.get(i).getY() + 1][ballDestroy.get(i).getX()].getBall().breakBall();
				} catch (Exception e) {
				}
				try {
					rows[ballDestroy.get(i).getY()][ballDestroy.get(i).getX() - 1].getBall().breakBall();
				} catch (Exception e) {
				}
				try {
					rows[ballDestroy.get(i).getY()][ballDestroy.get(i).getX() + 1].getBall().breakBall();
				} catch (Exception e) {
				}
				boardView.addRow(new Row(null, ballDestroy.get(i).getX(), ballDestroy.get(i).getY(), this));
			}
		}
		rows = boardView.getRows();
		for (int x = 0; x < 6; x++) {
			int nullpoint = -1;
			int goDown = -1;
			for (int y = 6; y > 0; y--) {
				if (rows[y][x].getBall() == null && nullpoint == -1) {
					nullpoint = y;
					// System.out.println("SETTING NULLPOINT AT Y: " + nullpoint);
				} else if (nullpoint != -1 && goDown == -1 && rows[y][x].getBall() != null) {
					goDown = nullpoint - y;
				}
				if (rows[y][x].getBall() != null && nullpoint != -1) {
					System.out.println("MOVING BALL WITH NULLPOINT Y: " + nullpoint + "AND Y: " + y + " FINAL POS "
							+ (y + (nullpoint - y)));
					boardView.addRow(new Row(rows[y][x].getBall(), x, y + goDown, this));
					boardView.addRow(new Row(null, x, y, this));
				}
			}
		}

		// checkForDestroy();
		return;
	}

}
