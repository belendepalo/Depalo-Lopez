package nemo;

import java.util.Arrays;
import java.util.List;

public class Nemo {

	public Depth currentDepth = new Surface();
	public CardinalDirection direction = new North();
	public Coordinates coordinate = new Coordinates(0, 0, 0);
	private static List<Commands> commands = Arrays.asList(new MoveForward(), new RotateLeft(), new RotateRight(),
			new Descend(), new Ascend(), new ReleaseCapsule());

	public Nemo() {
	}

	public void executeCommands(String commandString) {
		commandString.chars().mapToObj(c -> (char) c).forEach(this::executeCommand);
	}

	private void executeCommand(char commandChar) {
		commands.stream().filter(command -> command.canHandle(commandChar))
				.forEach(command -> command.executeCommand(this));
	}
}
