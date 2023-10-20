package nemo;

public class ShallowWater extends Depth {

	private Depth previousDepth;

	public ShallowWater() {
		this.previousDepth = new Surface();
	}

	@Override
	public boolean canReleaseCapsule() {
		return true;
	}

	@Override
	public Depth ascend() {
		return this.previousDepth;
	}

	@Override
	public Depth descend() {
		return new DeepWater(this);
	}

	@Override
	public Depth nextDepth() {
		return new DeepWater(this);
	}
	public void ascendAndUpdateCoordinates(Nemo nemo){
		nemo.coordinate = nemo.coordinate.updatePosition(nemo.direction.goUp());
		nemo.currentDepth = nemo.currentDepth.ascend();
	}

}
