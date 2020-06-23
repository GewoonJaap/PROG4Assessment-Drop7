package view;

import controller.ScoreController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ScoreView extends BorderPane {

	private ScoreController scoreController;
	private Label score;
	private Label level;
	private Label ballLeft;
	private Label TimeLeft;
	private Label cheatmode;
	private BallView nextBall;
	private VBox vBox;
	private double vBox_SPACING = 3.0;

	public ScoreView(ScoreController scoreController) {
		this.scoreController = scoreController;
		scoreController.setScoreView(this);
		createView();
	}

	public void createView() {
		this.getChildren().clear();
		getStylesheets().add(this.getClass().getResource("/resources/core.css").toExternalForm());
		score = new Label("Score: " + Integer.toString(scoreController.getScore()));
		score.getStyleClass().add("basicText");
		level = new Label("Level: " + Integer.toString(scoreController.getLevel()));
		level.getStyleClass().add("basicText");
		ballLeft = new Label("Balls left: " + Integer.toString(scoreController.getBallLeft()));
		ballLeft.getStyleClass().add("basicText");
		int looptime = scoreController.getGameView().getGameController().getLoopTime();
		int currentLoopTime = scoreController.getGameView().getGameController().getCurrentLoopTime();
		TimeLeft = new Label("TimeLeft: " + Integer.toString(looptime - currentLoopTime));
		TimeLeft.getStyleClass().add("basicText");
		cheatmode = new Label("Cheat mode enabled: " + Boolean.toString(scoreController.getCheatModeActivated())
				+ " Press C to toggle");
		cheatmode.getStyleClass().add("basicText");
		if (scoreController.getNextBall() != null) {
			nextBall = new BallView(scoreController.getNextBall());
		}
		vBox = new VBox(vBox_SPACING);
		if (scoreController.getNextBall() == null) {
			vBox.getChildren().addAll(score, level, ballLeft, TimeLeft, cheatmode);
		} else {
			vBox.getChildren().addAll(score, level, ballLeft, TimeLeft, cheatmode, nextBall);
		}
		vBox.setAlignment(Pos.CENTER);
		this.setCenter(vBox);

	}

	public void UpdateTimeLeft() {
		int looptime = scoreController.getGameView().getGameController().getLoopTime();
		int currentLoopTime = scoreController.getGameView().getGameController().getCurrentLoopTime();
		TimeLeft.setText("TimeLeft: " + Integer.toString(looptime - currentLoopTime));
	}

}
