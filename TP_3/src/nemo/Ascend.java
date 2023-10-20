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
		nemo.getCurrentDepth().ascendAndUpdateCoordinates(nemo);
	}

}
