package nemo;

public class DeepWater extends Depth {

	private Depth previousDepth;

	public DeepWater(Depth prevDepth) {
		this.previousDepth = prevDepth;
	}

	@Override
	public boolean canReleaseCapsule() {
		throw new RuntimeException("Nemo has been destroyed");
	}

	@Override
	public Depth ascend() {
		return this.previousDepth;
	}

	@Override
	public Depth descend() {
		return new DepthEndMarker(this);
	}

	@Override
	public Depth nextDepth() {
		return new DepthEndMarker(this);
	}

}
