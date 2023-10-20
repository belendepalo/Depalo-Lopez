package nemo;

public class Ascend extends Commands {

	public Ascend() {
		super('u');
	}

	@Override
	public boolean canHandle(char commandChar) {
		return 'u' == commandChar;
	}

	@Override
	public void executeCommand(Nemo nemo) {
		nemo.coordinate = nemo.coordinate.updatePosition(nemo.direction.goUp());
		nemo.currentDepth = nemo.currentDepth.ascend();
	}

}
