package nemo;

public class MoveForward extends Commands {

	public MoveForward() {
		super('f');
	}

	@Override
	public boolean canHandle(char commandChar) {
		return 'f' == commandChar;
	}

	@Override
	public void executeCommand(Nemo nemo) {
		nemo.coordinate = nemo.coordinate.updatePosition(nemo.direction.goForward());

	}

}
