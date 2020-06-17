package model;

import controller.BoardController;

public class Row {

	private Ball ball;
	private int x;
	private int y;
	private BoardController boardController;

	public Row(Ball ball, int x, int y, BoardController boardController) {
		// TODO Auto-generated constructor stub
		this.ball = ball;
		this.x = x;
		this.y = y;
		this.boardController = boardController;
	}

	public Ball getBall() {
		// TODO Auto-generated method stub
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
