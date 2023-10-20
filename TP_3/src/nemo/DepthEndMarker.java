package nemo;

public class DepthEndMarker extends Depth {

	private Depth previousDepth;

	public DepthEndMarker(Depth prevDepth) {
		this.previousDepth = prevDepth;
	}

	@Override
	public boolean canReleaseCapsule() {
		return false;
	}

	@Override
	public Depth ascend() {
		Depth lastDeepWater = this.previousDepth;
		this.previousDepth = this.previousDepth.nextDepth();
		return lastDeepWater;
	}

	@Override
	public Depth descend() {
		DeepWater newDeepWater = new DeepWater(this.previousDepth);
		this.previousDepth = newDeepWater;
		return newDeepWater;
	}

	@Override
	public Depth nextDepth() {
		return this;
	}

	@Override
	public void canGoUp(Nemo nemo) {}

}
