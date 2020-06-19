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
		for (int y = 6; y >= 0; y--) {
			if (rows[y][newRow.getX()].getBall() == null) {
				rows[y][newRow.getX()].setBall(gameView.getGameController().getGame().getNextBall());
				boardView.addRow(rows[y][newRow.getX()]);
				gameView.getGameController().getGame().setNextBall();
				gameView.getScoreController().updateScoreView();
				break;
			}
		}
		if (gameView.getScoreController().getBallLeft() <= -1) {
			if (gameView.getGameController().getGame().getNextBall() != null) {
				gameView.getGameController().getGame().setNextBallFixed(null);
				gameView.getScoreController().updateScoreView();
				// Check if game over because there is a ball at the top
				for (int x = 0; x <= 6; x++) {
					if (rows[0][x].getBall() != null) {
						gameView.getGameController().showGameOver();
						return;
					}
				}
			}
			System.out.println("OUT OF BALLS");
			moveBallsup();
			return;
		}
		checkForDestroy();
	}

	private void moveBallsup() {
		Row[][] rows = boardView.getRows();
		for (int x = 0; x <= 6; x++) {
			for (int y = 0; y <= 6; y++) {
				if (rows[y][x].getBall() != null) {
					boardView.addRow(new Row(rows[y][x].getBall(), x, y - 1, this));
				}
			}

		}
		rows = boardView.getRows();
		for (int x = 0; x <= 6; x++) {
			int ballNr = (int) Math.floor(Math.random() * (1 - 7) + 7);
			boardView.addRow(new Row(new Ball(ballNr, "/resources/full.png"), x, 6, this));
		}
		gameView.getGameController().getGame().resetBallLeft();
		gameView.getGameController().getGame().nextlevel();
		gameView.getScoreController().updateScoreView();
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
					// CHECK BALLS IN Y DOWN AND Y UP
					// DOWN
					for (int by = rows[y][x].getY(); by < 6; by++) {
						System.out.println("CHECKING DOWN Y: " + by);
						if (rows[by][x].getBall() != null) {
							ballsy++;
							// System.out.println("Found a ball: Y:" + by + " X:" + x);
						} else {
							// System.out.println("BREAK");
							break;
						}
					}
					System.out.println("BALLS y" + ballsy);
					// UP
					for (int by = rows[y][x].getY(); by > 0; by--) {
						System.out.println("CHECKING Y UP: " + by);
						if (rows[by][x].getBall() != null) {
							ballsy++;
							//System.out.println("Found a ball: Y:" + by + " X:" + x);
						} else {
							//System.out.println("BREAK");
							break;
						}
					}
					
					System.out.println("BALLS y" + ballsy);

					for (int bx = rows[y][x].getX(); bx <= 6; bx++) {
						if (rows[y][bx].getBall() != null) {
							 //System.out.println("Found a ball: Y:" + y + " X:" + bx);
							ballsx++;
						} else {
							// System.out.println("BREAK");
							break;
						}
					}
					System.out.println("BALLS x" + ballsx);
					for (int bx = rows[y][x].getX() -1; bx >= 0; bx--) {
						if (rows[y][bx].getBall() != null) {
							// System.out.println("Found a ball: Y:" + y + " X:" + bx);
							ballsx++;
						} else {
							// System.out.println("BREAK");
							break;
						}
					}
					System.out.println("BALLS x" + ballsx);
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
				updateScore();
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
				}
				if (nullpoint != -1 && goDown == -1 && rows[y][x].getBall() != null) {
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

		checkForDestroy();
		return;
	}

	private void updateScore() {
		gameView.getGameController().getGame().addToScore(10);
		boolean emptyfield = true;
		for (int x = 0; x <= 6; x++) {
			Row[][] rows = boardView.getRows();
			if (rows[6][x].getBall() != null) {
				emptyfield = false;
				break;
			}
		}
		if (emptyfield) {
			gameView.getGameController().getGame().addToScore(100);
			gameView.getScoreController().updateScoreView();
		}
	}
}
