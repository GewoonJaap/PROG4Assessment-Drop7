package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ball;

public class BallView extends ImageView {

	private Ball ball;
	public BallView(Ball ball) {
		this.ball = ball;
	}
	
	private void createView() {
		this.setImage(new Image(ball.getImage()));
	}

}
