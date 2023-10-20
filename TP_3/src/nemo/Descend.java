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
		nemo.coordinate = nemo.coordinate.updatePosition(nemo.getDirection().goDown());
		nemo.currentDepth = nemo.getCurrentDepth().descend();
	}

}
