package nemo;

public class East extends CardinalDirection {

	@Override
	public Coordinates goForward() {
		return new Coordinates(1, 0, 0);
	}

	@Override
	public CardinalDirection turnRight() {
		return new South();
	}

	@Override
	public CardinalDirection turnLeft() {
		return new North();
	}

}
