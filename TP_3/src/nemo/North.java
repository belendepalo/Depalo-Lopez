package nemo;

public class North extends CardinalDirection {

	@Override
	public Coordinates goForward() {
		return new Coordinates(0, 1, 0);
	}

	@Override
	public CardinalDirection turnRight() {
		return new East();
	}

	@Override
	public CardinalDirection turnLeft() {
		return new West();
	}
}
