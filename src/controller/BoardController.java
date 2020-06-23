package controller;

import java.util.ArrayList;

import model.Ball;
import model.Game;
import model.Row;
import view.BoardView;
import view.GameView;

public class BoardController {

	private BoardView boardView;
	private GameView gameView;
	private int fieldSizeX;
	private int fieldSizeY;
	private Game game;

	public BoardController(GameView gameView) {
		// Start the controller, set references
		this.gameView = gameView;
		this.game = gameView.getGameController().getGame();
		fieldSizeX = game.getFieldSizeX();
		fieldSizeY = game.getFieldSizeY();
	}

	public void setBoardView(BoardView boardView) {
		// Create reference to boardview
		this.boardView = boardView;
	}

	public void addRow(Row row) {
		// Get rows, modify, update and refrsh the view
		Row[][] rows = game.getRows();
		rows[row.getY()][row.getX()] = row;
		game.setRows(rows);
		boardView.addRow();
	}

	//
	// CLICKED ROW, PLACE BALL
	//
	public void clickedRow(Row newRow) {
		// System.out.println("Clicked row at: X: " + newRow.getX() + " Y:" +
		// newRow.getY());
		Row[][] rows = game.getRows();

		//
		// GET EMPTY ROW ON Y AXIS
		//
		for (int y = fieldSizeY; y >= 0; y--) {
			if (rows[y][newRow.getX()].getBall() == null) {
				rows[y][newRow.getX()].setBall(gameView.getGameController().getGame().getNextBall());
				addRow(rows[y][newRow.getX()]);
				gameView.getGameController().getGame().setNextBall();
				gameView.getScoreController().updateScoreView();
				break;
			}
		}

		//
		// OUT OF BALLS, NEXT ROUND
		//
		if (gameView.getScoreController().getBallLeft() <= -1) {
			if (gameView.getGameController().getGame().getNextBall() != null) {
				gameView.getScoreController().updateScoreView();
				// Check if gameover because there is a ball at the top
				for (int x = 0; x <= fieldSizeX; x++) {
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
		gameView.getGameController().resetTimer();
	}

	//
	// MOVE BALLSUP ONCE A NEW LEVEL STARTS
	//
	private void moveBallsup() {
		Row[][] rows = game.getRows();
		for (int x = 0; x <= fieldSizeX; x++) {
			for (int y = 0; y <= fieldSizeY; y++) {
				if (rows[y][x].getBall() != null) {
					addRow(new Row(rows[y][x].getBall(), x, y - 1, this));
				}
			}

		}
		// Add new balls to the button
		rows = game.getRows();
		for (int x = 0; x <= fieldSizeX; x++) {
			int ballNr = (int) Math.floor(Math.random() * (1 - 7) + 7);
			addRow(new Row(new Ball(ballNr, "/resources/full.png"), x, fieldSizeY, this));
		}
		gameView.getGameController().getGame().resetBallLeft();
		gameView.getGameController().getGame().nextlevel();
		gameView.getScoreController().updateScoreView();
	}

	private void checkForDestroy() {
		Row[][] rows = game.getRows();
		ArrayList<Row> ballDestroy = new ArrayList<Row>();
		for (int y = fieldSizeY; y >= 0; y--) {
			for (int x = fieldSizeX; x >= 0; x--) {
				if (rows[y][x].getBall() != null && !rows[y][x].getBall().getImage().contains("full")
						&& !rows[y][x].getBall().getImage().contains("half")) {
					int ballValue = rows[y][x].getBall().getValue();
					int ballsy = 0;
					int ballsx = 0;
//					 CHECK BALLS IN Y DOWN AND Y UP
//					 DOWN
					for (int by = rows[y][x].getY(); by <= fieldSizeY; by++) {
						System.out.println("CHECKING DOWN Y: " + by);
						if (rows[by][x].getBall() != null) {
							ballsy++;
						} else {
							break;
						}
					}
					// UP
					for (int by = rows[y][x].getY() - 1; by >= 0; by--) {
						if (rows[by][x].getBall() != null) {
							ballsy++;
						} else {
							break;
						}
					}

					// X Axis Right
					for (int bx = rows[y][x].getX(); bx <= fieldSizeX; bx++) {
						if (rows[y][bx].getBall() != null) {
							ballsx++;
						} else {
							break;
						}
					}
					// X Axis Left
					for (int bx = rows[y][x].getX() - 1; bx >= 0; bx--) {
						if (rows[y][bx].getBall() != null) {
							ballsx++;
						} else {
							break;
						}
					}

					if (ballsx == ballValue || ballsy == ballValue) {
						// Check if ball matches rule to destroy it
						ballDestroy.add(rows[y][x]);
					}

				}
			}
		}

		//
		// CHECK FOR GAME OVER IF THERE IS NO PLACE FOR A NEW BALL
		//
		if (ballDestroy.size() == 0) {
			for (int x = 0; x <= fieldSizeX; x++) {
				if (rows[0][x].getBall() == null) {
					moveBallsDown();
					return;
				}
			}
			gameView.getGameController().showGameOver();
			return;
		}

		//
		// BREAK BALLS AROUND
		//
		for (int i = 0; i < ballDestroy.size(); i++) {
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
			// Remove ball to destroy from the field.
			addRow(new Row(null, ballDestroy.get(i).getX(), ballDestroy.get(i).getY(), this));
			updateScore(ballDestroy.get(i).getBall().getValue());

		}

		//
		// Let balls fall down after destroy
		//

		moveBallsDown();
		//
		// RESET ballDestroy AGAINST STACK OVERFLOW
		//
		ballDestroy = null;

		//
		// CHECK IF THERE IS MORE TO DESTROY
		//
		checkForDestroy();

		//
		// EXIT THIS FUNCTION
		//
		return;
	}

	private void moveBallsDown() {
		Row[][] rows = game.getRows();
		for (int x = 0; x <= fieldSizeX; x++) {
			int nullpoint = -1;
			int goDown = -1;
			for (int y = fieldSizeY; y >= 0; y--) {
				if (rows[y][x].getBall() == null && nullpoint == -1) {
					nullpoint = y;
					// Setting the "nullpoint", a point where there is no ball on the field.
				}
				if (nullpoint != -1 && goDown == -1 && rows[y][x].getBall() != null) {
					goDown = nullpoint - y;
					// Calculate the amount of fields a ball should go down
				}
				if (rows[y][x].getBall() != null && nullpoint != -1) {
					System.out.println("MOVING BALL WITH NULLPOINT Y: " + nullpoint + "AND Y: " + y + " FINAL POS "
							+ (y + (nullpoint - y)));
					addRow(new Row(rows[y][x].getBall(), x, y + goDown, this));
					addRow(new Row(null, x, y, this));
					// Move ball down, and empty old row.
				}
			}
		}
	}

	private void updateScore(int points) {

		//
		// UPDATE SCORE ONCE A BALL GETS REMOVED
		//
		gameView.getGameController().getGame().addToScore(points);
		boolean emptyfield = true;
		// Check for empty field
		for (int x = 0; x <= fieldSizeX; x++) {
			Row[][] rows = game.getRows();
			if (rows[fieldSizeY][x].getBall() != null) {
				emptyfield = false;
				break;
			}
		}

		//
		// CHECK IF FIELD IS EMPTY TO ADD 100 POINTS
		//
		if (emptyfield) {
			gameView.getGameController().getGame().addToScore(100);
			gameView.getScoreController().updateScoreView();
		}
	}

	public Game getGame() {
		return game;
	}
}
