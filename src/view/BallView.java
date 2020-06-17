package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ball;

public class BallView extends ImageView {

	private Ball ball;
	private int HEIGHT = 64;
	private int WIDTH = 64;

	public BallView(Ball ball) {
		this.ball = ball;
		createView();
	}

	private void createView() {
		this.setImage(new Image(ball.getImage(), WIDTH, HEIGHT, false, false));
	}

}
