package nemo;

public class DeepWater extends Depth {
	protected static final String nemoHasBeenDestroyed = "Nemo has been destroyed";
	private Depth previousDepth;

	public DeepWater(Depth prevDepth) {
		this.previousDepth = prevDepth;
	}

	@Override
	public boolean canReleaseCapsule() {
		throw new RuntimeException(nemoHasBeenDestroyed);
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

	public void canGoUp(Nemo nemo){
		nemo.coordinate = nemo.coordinate.updatePosition(nemo.direction.goUp());
		nemo.currentDepth = nemo.currentDepth.ascend();
	}

}
