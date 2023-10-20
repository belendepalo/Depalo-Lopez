package nemo;

public abstract class Depth {

	public abstract boolean canReleaseCapsule();

	public abstract Depth ascend();

	public abstract Depth descend();

	public abstract Depth nextDepth();

	public abstract void canGoUp(Nemo nemo);
}
