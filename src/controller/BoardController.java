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
		this.gameView = gameView;
		this.game = gameView.getGameController().getGame();
		fieldSizeX = game.getFieldSizeX();
		fieldSizeY = game.getFieldSizeY();
	}

	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public void addRow(Row row) {
		boardView.addRow(row);
	}

	//
	// CLICKED ROW, PLACE BALL
	//
	public void clickedRow(Row newRow) {
		// System.out.println("Clicked row at: X: " + newRow.getX() + " Y:" +
		// newRow.getY());
		Row[][] rows = boardView.getRows();

		//
		// GET EMPTY ROW ON Y AXIS
		//
		for (int y = fieldSizeY; y >= 0; y--) {
			if (rows[y][newRow.getX()].getBall() == null) {
				rows[y][newRow.getX()].setBall(gameView.getGameController().getGame().getNextBall());
				boardView.addRow(rows[y][newRow.getX()]);
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
				// Check if game over because there is a ball at the top
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
	}

	//
	// MOVE BALLSUP ONCE A NEW LEVEL STARTS
	//
	private void moveBallsup() {
		Row[][] rows = boardView.getRows();
		for (int x = 0; x <= fieldSizeX; x++) {
			for (int y = 0; y <= fieldSizeY; y++) {
				if (rows[y][x].getBall() != null) {
					boardView.addRow(new Row(rows[y][x].getBall(), x, y - 1, this));
				}
			}

		}
		rows = boardView.getRows();
		for (int x = 0; x <= fieldSizeX; x++) {
			int ballNr = (int) Math.floor(Math.random() * (1 - 7) + 7);
			boardView.addRow(new Row(new Ball(ballNr, "/resources/full.png"), x, fieldSizeY, this));
		}
		gameView.getGameController().getGame().resetBallLeft();
		gameView.getGameController().getGame().nextlevel();
		gameView.getScoreController().updateScoreView();
	}

	private void checkForDestroy() {
		Row[][] rows = boardView.getRows();
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
					for (int by = rows[y][x].getY(); by < fieldSizeY; by++) {
						System.out.println("CHECKING DOWN Y: " + by);
						if (rows[by][x].getBall() != null) {
							ballsy++;
//							 System.out.println("Found a ball: Y:" + by + " X:" + x);
						} else {
//							 System.out.println("BREAK");
							break;
						}
					}
//					System.out.println("BALLS y" + ballsy);
					// UP
					for (int by = rows[y][x].getY(); by > 0; by--) {
//						System.out.println("CHECKING Y UP: " + by);
						if (rows[by][x].getBall() != null) {
							ballsy++;
//							System.out.println("Found a ball: Y:" + by + " X:" + x);
						} else {
//							System.out.println("BREAK");
							break;
						}
					}

//					System.out.println("BALLS y" + ballsy);

					for (int bx = rows[y][x].getX(); bx <= fieldSizeX; bx++) {
						if (rows[y][bx].getBall() != null) {
//							 System.out.println("Found a ball: Y:" + y + " X:" + bx);
							ballsx++;
						} else {
//							 System.out.println("BREAK");
							break;
						}
					}
//					System.out.println("BALLS x" + ballsx);
					for (int bx = rows[y][x].getX() - 1; bx >= 0; bx--) {
						if (rows[y][bx].getBall() != null) {
//							 System.out.println("Found a ball: Y:" + y + " X:" + bx);
							ballsx++;
						} else {
//							 System.out.println("BREAK");
							break;
						}
					}
					System.out.println("BALLS x" + ballsx);
					if (ballsx == ballValue || ballsy == ballValue) {
//						 System.out.println("We can destroy a ball! on Y: " + y + " X: " + x + " With
//						 value: "
//						 + rows[y][x].getBall().getValue());
						ballDestroy.add(rows[y][x]);
					}

				}
			}
		}
		// System.out.println("Destroying all the balls!");

		//
		// CHECK FOR GAME OVER IF THERE IS NO PLACE FOR A NEW BALL
		//
		if (ballDestroy.size() == 0) {
			for (int x = 0; x <= fieldSizeX; x++) {
				if (rows[0][x].getBall() == null)
					return;
			}
			gameView.getGameController().showGameOver();
			return;
		}

		//
		// DESTROY BALL AROUND
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
			boardView.addRow(new Row(null, ballDestroy.get(i).getX(), ballDestroy.get(i).getY(), this));
			updateScore(ballDestroy.get(i).getBall().getValue());

		}
		rows = boardView.getRows();

		//
		// MOVE BALL DOWN
		//
		for (int x = 0; x < fieldSizeX; x++) {
			int nullpoint = -1;
			int goDown = -1;
			for (int y = fieldSizeY; y > 0; y--) {
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

	private void updateScore(int points) {

		//
		// UPDATE SCORE ONCE A BALL GETS REMOVE
		//
		gameView.getGameController().getGame().addToScore(points);
		boolean emptyfield = true;
		for (int x = 0; x <= fieldSizeX; x++) {
			Row[][] rows = boardView.getRows();
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
