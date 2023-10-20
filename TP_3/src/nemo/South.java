package nemo;

public class South extends CardinalDirection {

	public Coordinates goForward() {
		return new Coordinates(0, -1, 0);
	}

	@Override
	public CardinalDirection turnRight() {
		return new West();
	}

	@Override
	public CardinalDirection turnLeft() {
		return new East();
	}
}
