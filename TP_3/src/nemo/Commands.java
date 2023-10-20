package nemo;

public abstract class Commands {

	public char commandChar;

	public Commands(char command) {
		this.commandChar = command;
	}

	public abstract boolean canHandle(char commandChar);

	public abstract void executeCommand(Nemo submarine);

}
