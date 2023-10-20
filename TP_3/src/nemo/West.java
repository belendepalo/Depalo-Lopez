package nemo;

public class West extends CardinalDirection {

	public Coordinates goForward() {
		return new Coordinates(-1, 0, 0);
	}

	@Override
	public CardinalDirection turnRight() {
		return new North();
	}

	@Override
	public CardinalDirection turnLeft() {
		return new South();
	}
}
