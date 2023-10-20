package nemo;

public class Descend extends Commands {

	public Descend() {
		super('d');
	}

	@Override
	public boolean canHandle(char commandChar) {
		return 'd' == commandChar;
	}

	@Override
	public void executeCommand(Nemo nemo) {
		nemo.coordinate = nemo.coordinate.updatePosition(nemo.direction.goDown());
		nemo.currentDepth = nemo.currentDepth.descend();
	}

}
