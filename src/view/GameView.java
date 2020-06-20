package view;

import controller.BoardController;
import controller.GameController;
import controller.ScoreController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameView extends BorderPane {

	private ScoreController scoreController;
	private ScoreView scoreView;
	private BoardController boardController;
	private BoardView boardView;
	private GameController gameController;
	private double WINDOW_HEIGHT = 720;
	private double WINDOW_WIDTH = 720;

	public GameView(GameController gameController) {
		this.gameController = gameController;
		drawView();
	}

	private void drawView() {
		//this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		getStylesheets().add(this.getClass().getResource("/resources/core.css").toExternalForm());
		this.getStyleClass().add("background");
		drawBackground();
		this.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		VBox vBox = new VBox(25.0);
		vBox.setMinSize(WINDOW_WIDTH / 5, WINDOW_HEIGHT / 5);

		scoreController = new ScoreController(this);
		scoreView = new ScoreView(scoreController);

		boardController = new BoardController(this);
		BoardView boardView = new BoardView(boardController);
		int sizeX = (gameController.getGame().getFieldSizeX() + 1 * gameController.getGame().getRowSizeX());
		int sizeY = (gameController.getGame().getFieldSizeY() + 1 * gameController.getGame().getRowSizeY());
		boardView.setMaxSize(sizeX, sizeY);
		vBox.getChildren().addAll(scoreView, boardView);
		vBox.setAlignment(Pos.CENTER);
		this.setCenter(vBox);

	}
	
	private void drawBackground() {
        ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

        KeyValue keyValue1 = new KeyValue(baseColor, Color.rgb(255, 127, 80));
        KeyValue keyValue2 = new KeyValue(baseColor, Color.rgb(106, 90, 205));
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(2500), keyValue2);
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);

        baseColor.addListener((obs, oldColor, newColor) -> {
            this.setStyle(String.format("-gradient-base: #%02x%02x%02x; ", 
                    (int)(newColor.getRed()*255),
                    (int)(newColor.getGreen()*255),
                    (int)(newColor.getBlue()*255)));
        });

        timeline.setAutoReverse(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
	}

	public void showGameOver() {
		this.getChildren().clear();
		Label gameOver = new Label("GAME OVER");
		gameOver.getStyleClass().add("basicText");
		Label score = new Label("SCORE: " + Integer.toString(gameController.getGame().getScore()));
		score.getStyleClass().add("basicText");
		Label level = new Label("LEVEL: " + Integer.toString(gameController.getGame().getLevel()));
		level.getStyleClass().add("basicText");
		Button restart = new Button("Restart game");
		restart.setOnMouseClicked(e -> scoreController.getGameView().getGameController().loadGame());
		restart.getStyleClass().add("gameoverBTN");
		VBox vbox = new VBox(10.0);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(gameOver, score, level, restart);
		this.setCenter(vbox);
	}

	public GameController getGameController() {
		return gameController;
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public ScoreController getScoreController() {
		return scoreController;
	}

}
