package model;

import java.util.Random;

public class Game {

	private int score;
	private int level;
	private Ball nextBall;
	private int ballLeft;
	private int BallLeft_START = 30;
	private int fieldSizeX = 6; // Use FieldSize -1 If you want a field that has 7 rows in the X axis, use 6
	private int fieldSizeY = 6; // Use FieldSize -1 If you want a field that has 7 rows in the Y axis, use 6
	private int rowSizeX = 64; // px
	private int rowSizeY = 64; // px
	private double fullBallChance = 0.25; // Chance of giving a full ball, one you need to break.
	private Row[][] row;

	public Game() {
		// Setup the game
		score = 0;
		level = 1;
		ballLeft = BallLeft_START;
		setNextBall();
		createRows();
	}

	private void createRows() {
		// Create all the rows
		row = new Row[fieldSizeY + 1][fieldSizeX + 1];
	}

	public void setNextBall() {
		// Create a new ball, and lower the amount of balls left.
		removeBallLeft();
		int ballNr = (int) Math.floor(Math.random() * (1 - 8) + 8);
		Random random = new Random();
		// Get the chance for a full ball.
		if ((random.nextInt((int) (1 / fullBallChance)) == 0)) {
			nextBall = new Ball(ballNr, "/resources/full.png");
			return;
		}
		nextBall = new Ball(ballNr, "/resources/" + ballNr + ".png");
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

	public void setNextBallFixed(Ball ball) {
		this.nextBall = ball;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void addToScore(int score) {
		this.score += score;
	}

	private void removeBallLeft() {
		ballLeft--;
	}

	public int getBallLeft() {
		return ballLeft;
	}

	public void resetBallLeft() {
		ballLeft = BallLeft_START;
		setNextBall();
	}

	public void nextlevel() {
		level++;
	}

	public int getFieldSizeX() {
		return fieldSizeX;
	}

	public int getFieldSizeY() {
		return fieldSizeY;
	}

	public int getRowSizeX() {
		return rowSizeX;
	}

	public int getRowSizeY() {
		return rowSizeY;
	}

	public Row[][] getRows() {
		return row;
	}

	public void setRows(Row[][] row) {
		this.row = row;
	}

}
