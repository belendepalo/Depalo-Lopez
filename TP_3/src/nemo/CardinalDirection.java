package nemo;

public abstract class CardinalDirection {

	public abstract CardinalDirection turnRight();

	public abstract CardinalDirection turnLeft();

	public abstract Coordinates goForward();

	public Coordinates goUp() {
		return new Coordinates(0, 0, 1);
	}

	public Coordinates goDown() {
		return new Coordinates(0, 0, -1);
	}

}
