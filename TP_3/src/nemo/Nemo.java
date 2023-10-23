package nemo;

import java.util.Arrays;
import java.util.List;

public class Nemo {

	protected Depth currentDepth = new Surface();
	protected CardinalDirection direction = new North();
	public Coordinates coordinate = new Coordinates(0, 0, 0);
	private static List<Commands> commands = Arrays.asList(new MoveForward(), new RotateLeft(), new RotateRight(),
			new Descend(), new Ascend(), new ReleaseCapsule());

	public Depth getCurrentDepth() {
		return currentDepth;
	}

	public CardinalDirection getDirection() {
		return direction;
	}

	public void executeCommands(String commandString) {
		commandString.chars().mapToObj(charValue -> (char) charValue).forEach(this::executeCommand);
	}

	private void executeCommand(char commandChar) {
		commands.stream().filter(command -> command.canHandle(commandChar))
				.forEach(command -> command.executeCommand(this));
	}

}
