package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ball;

public class BallView extends ImageView {

	private Ball ball;
	public BallView(Ball ball) {
		this.ball = ball;
		createView();
	}
	
	private void createView() {
		this.setImage(new Image(ball.getImage()));
		this.maxHeight(64);
		this.maxWidth(64);
		this.prefWidth(64);
	}

}
