package model;

import java.util.Random;

public class Game {

	private int score;
	private int level;
	private Ball nextBall;
	private int ballLeft;
	private int BallLeft_START = 30;
	private int fieldSizeX = 6; // Use FieldSize -1
	private int fieldSizeY = 6; // Use FieldSize -1
	private int rowSizeX = 64;
	private int rowSizeY = 64;
	private double fullBallChance = 0.25;
	private Row[][] row;

	public Game() {
		score = 0;
		level = 1;
		ballLeft = BallLeft_START;
		setNextBall();
		createRows();
	}
	
	private void createRows() {
		row = new Row[fieldSizeY + 1][fieldSizeX + 1];
	}

	public void setNextBall() {
		removeBallLeft();
		int ballNr = (int) Math.floor(Math.random() * (1 - 8) + 8);
		Random random = new Random();
		// 25% chance for a full ball
		if ((random.nextInt((int) (1/fullBallChance)) == 0)) {
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
	
	public Row[][] getRows(){
		return row;
	}
	
	public void setRows(Row[][] row) {
		this.row = row;
	}

}
