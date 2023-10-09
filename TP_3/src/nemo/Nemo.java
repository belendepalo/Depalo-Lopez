package nemo;

public class Nemo {

	private Orientation currentDirection;
	private boolean destroyed = false;
	private boolean capsuleReleased = false;
	private int xPosition;
	private int yPosition;
	private int depth;

	public Nemo() {
		this.xPosition = 0;
		this.yPosition = 0;
		this.depth = 0;
		this.currentDirection = Orientation.NORTH;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public int getDepth() {
		return depth;
	}

	public Orientation getCurrentDirection() {
		return currentDirection;
	}

	public Boolean capsuleLaunched() {
		return capsuleReleased;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void executeCommands(String commands) {
		for (Character command : commands.toCharArray()) {
			executeCommand(command);
		}
	}

	public void executeCommand(Character command) {
	    if (destroyed) {
	        throw new RuntimeException("Nemo has been destroyed.");
	    }
		switch (command) {
		case 'd':
			descend();
			break;
		case 'u':
			ascend();
			break;
		case 'f':
			moveForward();
			break;
		case 'l':
			rotateLeft();
			break;
		case 'r':
			rotateRight();
			break;
		case 'm':
			releaseCapsule();
			break;
		default:
			throw new RuntimeException("Invalid command: " + command);
		}
	}

	private void descend() {
		this.depth -= 1;
	}

	private void ascend() {
		if (this.depth < 0) {
			this.depth += 1;
		}
	}

	private void moveForward() {
		switch (currentDirection) {
		case NORTH:
			yPosition += 1;
			break;
		case SOUTH:
			yPosition -= 1;
			break;
		case EAST:
			xPosition += 1;
			break;
		case WEST:
			xPosition -= 1;
			break;
		}
	}

	private void rotateLeft() {
		switch (currentDirection) {
		case NORTH:
			currentDirection = Orientation.WEST;
			break;
		case EAST:
			currentDirection = Orientation.NORTH;
			break;
		case SOUTH:
			currentDirection = Orientation.EAST;
			break;
		case WEST:
			currentDirection = Orientation.SOUTH;
			break;
		}
	}

	private void rotateRight() {
		switch (currentDirection) {
		case NORTH:
			currentDirection = Orientation.EAST;
			break;
		case EAST:
			currentDirection = Orientation.SOUTH;
			break;
		case SOUTH:
			currentDirection = Orientation.WEST;
			break;
		case WEST:
			currentDirection = Orientation.NORTH;
			break;
		}
	}

	private void releaseCapsule() {
		capsuleReleased = true;
	    if (depth < -1) {
	        destroyed = true;
	        return;
	    }
	}
}
