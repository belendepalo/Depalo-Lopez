package nemo;

public class RotateRight extends Commands {

	public RotateRight() {
		super('r');
	}

	@Override
	public boolean canHandle(char commandChar) {
		return 'r' == commandChar;
	}

	@Override
	public void executeCommand(Nemo nemo) {
		nemo.direction = nemo.direction.turnRight();

	}

}
