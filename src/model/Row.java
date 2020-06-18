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
		
		if(y < 0 || y > 6 || x< 0 || x > 6) {
			System.out.println("INVALID X/Y AXIS: X:" + x + " Y:" + y);
			return;
		}
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
