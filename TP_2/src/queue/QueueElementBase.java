package queue;

public abstract class QueueElementBase {

	public abstract Object head();
	public abstract Object take();
    public abstract QueueElementBase add(Object newItem);
    public abstract int size();
}
