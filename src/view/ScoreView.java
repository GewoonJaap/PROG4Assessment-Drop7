package view;

import controller.ScoreController;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ScoreView extends BorderPane {

	private ScoreController scoreController;
	private TextField score;
	private TextField level;
	private BallView nextBall;
	private HBox hBox;

	public ScoreView(ScoreController scoreController) {
		this.scoreController = scoreController;
		createView();
	}

	private void createView() {
		score = new TextField(scoreController.getScore());
		level = new TextField(scoreController.getLevel());
		nextBall = new BallView(scoreController.getNextBall());
		hBox = new HBox(3.0);
		hBox.getChildren().addAll(score, level, nextBall);
	}

}
