package model;

import controller.BoardController;

public class Row {

	private Ball ball;
	private int x;
	private int y;
	private BoardController boardController;

	public Row(Ball ball, int x, int y, BoardController boardController) {
		//Create Row startup
		this.ball = ball;
		this.x = x;
		this.y = y;

		if (y < 0 || y > boardController.getGame().getFieldSizeY() || x < 0
				|| x > boardController.getGame().getFieldSizeX()) {
			System.out.println("INVALID X/Y AXIS: X:" + x + " Y:" + y);
			return;
		}
		this.boardController = boardController;
	}

	public Ball getBall() {
		return ball;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

}
