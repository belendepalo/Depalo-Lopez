package nemo;

public class RotateLeft extends Commands {

	public RotateLeft() {
		super('l');
	}

	@Override
	public boolean canHandle(char commandChar) {
		return 'l' == commandChar;
	}

	@Override
	public void executeCommand(Nemo nemo) {
		nemo.direction = nemo.direction.turnLeft();

	}

}
