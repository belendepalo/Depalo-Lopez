package nemo;

public class ReleaseCapsule extends Commands {

	public ReleaseCapsule() {
		super('m');
	}

	@Override
	public boolean canHandle(char commandChar) {
		return 'm' == commandChar;
	}

	@Override
	public void executeCommand(Nemo nemo) {
		nemo.currentDepth.canReleaseCapsule();
	}

}
