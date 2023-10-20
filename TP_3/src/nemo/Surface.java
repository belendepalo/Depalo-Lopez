package nemo;

public class Surface extends Depth {

	@Override
	public boolean canReleaseCapsule() {
		return true;
	}

	@Override
	public Depth ascend() {
		return this;
	}

	@Override
	public Depth descend() {
		return new ShallowWater();
	}

	@Override
	public Depth nextDepth() {
		return new ShallowWater();
	}

	public void canGoUp(Nemo nemo){
		throw new RuntimeException("Nemo cannot fly");
	}

}
