package model;

public class Ball {

	private int value;
	private String image;
	private boolean canDestroyed;

	public Ball(int value, String image) {
		this.value = value;
		this.image = image;
		if (image.contains("half") || image.contains("full")) {
			canDestroyed = false;
		} else {
			canDestroyed = true;
		}
	}

	public String getImage() {
		return image;
	}

	public int getValue() {
		return value;
	}

	public void updateImage(String image) {
		this.image = image;
	}

	public boolean canBeDestroyed() {
		return canDestroyed;
	}

	public void breakBall() {
		if (image.contains("full")) {
			image = "/resources/half.png";
		} else if (image.contains("half")) {
			image = "/resources/" + value + ".png";
		}
	}

}
