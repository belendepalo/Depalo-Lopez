package nemo;

public class Coordinates {
	int x;
	int y;
	int z;

	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Coordinates updatePosition(Coordinates newMovemetCoordinate) {
		x += newMovemetCoordinate.x;
		y += newMovemetCoordinate.y;
		z += newMovemetCoordinate.z;
		return this;
	}
}
