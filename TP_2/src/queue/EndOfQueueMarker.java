package queue;

public class EndOfQueueMarker extends QueueElementBase {
	static String EmptyQueue = "Queue is empty";
	
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Object head() {
		throw new Error(EmptyQueue);
	}

	@Override
	public Object take() {
		throw new Error(EmptyQueue);
	}
	
    @Override
    public QueueElementBase add(Object newItem) {
        return new QueueItem(newItem, this);
    }

    @Override
    public int size() {
        return 0;
    }

}