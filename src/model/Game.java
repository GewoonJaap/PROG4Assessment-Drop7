package model;

import view.BallView;

public class Game {
	
	private int score;
	private int level;
	private Ball nextBall;
	

	public Game() {
		score = 0;
		level = 1;
		setNextBall();
	}
	
	private void setNextBall() {
		int ballNr = (int) Math.floor(Math.random() * (1 -7 ) + 7);
		nextBall = new Ball(ballNr, "/resources/ball" + ballNr + ".gif");
	}
	
	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public Ball getNextBall() {
		return nextBall;
	}
	
	public void setNextBall(Ball ball) {
		this.nextBall = ball;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

}
