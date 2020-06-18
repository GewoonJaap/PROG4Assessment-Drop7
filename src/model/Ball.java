package model;

public class Ball {

	private int value;
	private String image;

	public Ball(int value, String image) {
		this.value = value;
		this.image = image;
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

}
